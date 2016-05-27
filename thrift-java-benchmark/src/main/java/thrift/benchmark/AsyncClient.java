package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TNonblockingSocket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Created by thuy on 21/05/16.
 */
public class AsyncClient {

    private static final java.util.logging.Logger logger = Logger.getLogger(NonBlockingClient.class.getName());
    private FileHandler fh;
    private TNonblockingSocket transportSocket;
    private TAsyncClientManager asyncClientManager;
    private MovieService.AsyncClient asyncClient;

    public ExecutorService executor;

    private int count;
    private static int counter = 0;

    private static long start;
    private static long end;

    //Class template supporting async wait and timeout
//    private static abstract class WaitableCallback<T>
//            implements AsyncMethodCallback<T> {
//
//        private CountDownLatch latch = new CountDownLatch(1);
//
//        //Synchronization Interface
//        public void reset() { latch = new CountDownLatch(1); }
//        public void complete() { latch.countDown(); }
//        public boolean wait(int i) {
//            boolean b = false;
//            try { b = latch.await(i, TimeUnit.MILLISECONDS); }
//            catch(Exception e) { System.out.println("[Client] await error"); }
//            return b;
//        }
//
//        //AsyncMethodCallback<T> interface
//        @Override
//        public void onError(Exception ex) {
//            if (ex instanceof TimeoutException) {
//                System.out.println("[Client] Async call timed out");
//            } else {
//                System.out.println("[Client] Async call error");
//            }
//            complete();
//        }
//    }


    private class GetMoviesMethodCallback implements AsyncMethodCallback<Movies> {

        @Override
        public void onComplete(Movies getMovies_call) {
//            info("onComplete{0}", getMovies_call);

            try {
                Movies re = getMovies_call;
                counter++;
                info("received result #{}", counter);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "error", e);
            }
        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "callback error", e);
        }
    }

    public AsyncClient(int port, int count) throws IOException {
        this.count = count;
        transportSocket = new TNonblockingSocket("localhost", port);
        asyncClientManager = new TAsyncClientManager();
        asyncClient = new MovieService.AsyncClient(new TBinaryProtocol.Factory(), asyncClientManager, transportSocket);

        executor = Executors.newSingleThreadExecutor();

        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Thrift_Client_ASYNC_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);

    }


    public void invoke() throws TException {
        info("Number of requests: {0}", count);
        start = System.nanoTime();

        for (int i=0; i<count; i++) {
//            //get_last_sale() async callback handler
//            WaitableCallback<Movies> callback =
//                    new WaitableCallback<Movies>() {
//
//                        @Override
//                        public void onComplete(Movies res) {
//                            try {
//                                counter++;
//                                info("received result #{0}", counter);
//                            } catch (Exception e) {
//                                logger.log(Level.SEVERE, "EXCEPTION", e);
//                            } finally {
//                                complete();
//                            }
//                        }
//                    };

            Runnable task = new Runnable() {
                public void run() { // Where the "do the call" code sits
                    try {
                        asyncClient.getMovies(new GetMoviesMethodCallback());
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }
            };
            info("Task {0} submitted", i);
            executor.submit(task); // This scheduled the runnable to be run

        }
    }


    public void shutdown() {
        info("Shutting down...");
        asyncClientManager.stop();
        transportSocket.close();
    }

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private static void logTransmissionTime(long value) {
        info("Transmission time: {0} nanoseconds ===== {1} milliseconds ", value, value / (double) 1000000);

    }

    public static void main(String[] args) {
        int port = 9092; // use with async server
        Scanner scanner = new Scanner(System.in);
        int count = 1000;

        try {
            AsyncClient client = new AsyncClient(port, count);

            info("TESTING");
            client.invoke();
            while (counter<count) {
                info("... progress current counter: {0} == {1}%", counter, (counter*100)/(float)count);
            }
            if (counter==count-1) {
                info("All tasks completed!");
                end = System.nanoTime();
                logTransmissionTime(end - start);
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "IOException", e);
        } catch (TException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "TException", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Unknown general Exception", e);
        }

    }
}
