package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
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
    private static MovieService.AsyncClient asyncClient;
    public ExecutorService executor;
    private static AtomicInteger counter = new AtomicInteger(0);

    //Class template supporting async wait and timeout
    private static abstract class WaitableCallback<T>
            implements AsyncMethodCallback<T> {

        private CountDownLatch latch = new CountDownLatch(1);

        //Synchronization Interface
        public void reset() {
            latch = new CountDownLatch(1);
        }

        public void complete() {
//            System.out.println("Callback received result #" + counter.get());

            latch.countDown();
        }

        public boolean wait(int i) {
            boolean b = false;
            try {
                b = latch.await(i, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                System.out.println("[Client] await error");
            }
            return b;
        }

        //AsyncMethodCallback<T> interface
        @Override
        public void onError(Exception ex) {
            if (ex instanceof TimeoutException) {
                System.out.println("[Client] Async call timed out");
            } else {
                System.out.println("[Client] Async call error");
                ex.printStackTrace();
            }
            complete();
        }
    }


    private class GetMoviesMethodCallback implements AsyncMethodCallback<Movies> {

        @Override
        public void onComplete(Movies getMovies_call) {
//            info("onComplete{0}", getMovies_call);

            try {
                Movies re = getMovies_call;
                info("!!!!!!!!callback oncomplete invoked...");
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

    public AsyncClient(int port) throws IOException {
        transportSocket = new TNonblockingSocket("127.0.0.1", port);
        asyncClientManager = new TAsyncClientManager();
        asyncClient = new MovieService.AsyncClient(new TBinaryProtocol.Factory(), asyncClientManager, transportSocket);

        executor = Executors.newFixedThreadPool(200);

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
//        logger.setUseParentHandlers(false);
        logger.addHandler(fh);
    }


    public void invoke(int count) throws TException {
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            Runnable task = new Runnable() {
                public void run() { // Where the "do the call" code sits

                    try {
//                        asyncClient.getMovies(new GetMoviesMethodCallback());
                        WaitableCallback<Movies> callback =
                                new WaitableCallback<Movies>() {

                                    @Override
                                    public void onComplete(Movies res) {
                                        try {
                                            // consume result from callback
                                            info("onComplete: received result for task: " + counter.incrementAndGet());
                                        } catch (Exception e) {
                                            logger.log(Level.SEVERE, "EXCEPTION", e);
                                        } finally {
                                            complete();
                                        }
                                    }
                                };
                        // make async call
//                        callback.reset();
                        asyncClient.getMovies(callback);
//                        info("Client getMovies() executes async");
//                        callback.wait(500);
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }

            };
            executor.submit(task);
            System.out.println("Task submitted #" + i);
        }
        long end = System.nanoTime();
        long submissionTime = end - start;
        logTransmissionTime(count, submissionTime);

        // wait for all futures to complete before sending new sets of request
    }

    public void shutdown() {
        info("Shutting down...");
        asyncClientManager.stop();
        transportSocket.close();
    }

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private static void logTransmissionTime(int count, long value) {
        info("Request submission time for {0} calls: {1} ", count, value);

    }

    public static void main(String[] args) {
        int port = 9090; // use with non-blocking server
        AsyncClient client = null;
        try {
            client = new AsyncClient(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to continue.. q to quit...");
        while (scanner.nextLine() != "q") {
            System.out.println("Number of calls:");
            int count = scanner.nextInt();

            try {

                info("TESTING");
//                client.invoke(count);
                client.invoke(count);


            } catch (TException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "TException", e);
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Unknown general Exception", e);
            }
        }

    }
}
