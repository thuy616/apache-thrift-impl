package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.async.TAsyncMethodCall;
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
import java.util.concurrent.atomic.AtomicBoolean;
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

    public AsyncClient(int port) throws IOException {
        transportSocket = new TNonblockingSocket("127.0.0.1", port);
        asyncClientManager = new TAsyncClientManager();
        asyncClient = new MovieService.AsyncClient(new TBinaryProtocol.Factory(), asyncClientManager, transportSocket);

        executor = Executors.newFixedThreadPool(1000);

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

    private static abstract class FutureLessCallback<T> implements AsyncMethodCallback<T> {
        @Override
        public void onError(Exception e) {
            logger.log(Level.SEVERE, "RPC FAILED!", e);
        }
    }

    private long basicCall(MovieService.AsyncClient client, int count) throws Exception {
        long totalElapsed = 0;
        for (int i=0; i<count; i++) {
            final int currentCount = i;
            final CountDownLatch latch = new CountDownLatch(1);
            final AtomicBoolean returned = new AtomicBoolean(false);
            long start = System.nanoTime();
            client.getMovies(new FutureLessCallback<Movies>() {

                @Override
                public void onComplete(Movies movies) {
                    returned.set(true);
                    latch.countDown();
                }

                @Override
                public void onError(Exception e) {
                    try {
                        logger.log(Level.SEVERE, "RPC FAILED!", e);
                    } finally {
                        latch.countDown();
                    }
                }
            });
            long end = System.nanoTime();
            totalElapsed += end - start;
            boolean calledBack = latch.await(100, TimeUnit.SECONDS);
            info("if called back on count: {0}: {1}", currentCount, calledBack);
            info("if result is returned on count {0}: {1}", currentCount, returned.get());
        }
        return totalElapsed;
    }

    public void runAsyncTests(int count, int iterations) throws Exception {
        long totalTime = 0;
        for (int i=0; i<iterations; i++) {
            totalTime += basicCall(asyncClient, count);
            Thread.sleep(10000);
        }
        info("AVERAGE time for {0} ASYNC calls after {1} iterations: {2}", count, iterations, totalTime/(float)iterations);
    }

    public void shutdown() {
        info("Shutting down...");
        asyncClientManager.stop();
        transportSocket.close();
        executor.shutdown();
        try {
            info("awaiting executor termination...");
            boolean grace = executor.awaitTermination(60*2, TimeUnit.SECONDS);
            if (grace) {
                info("shutdown gracefully");
            } else {
                info("termination time out!!!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


            System.out.println("Number of iterations:");
            int iterations = scanner.nextInt();

            try {

                info("TESTING");
//                client.invoke(count);
                info("IGNORE -- Handshaking requests ------------------- ");
                client.runAsyncTests(1, 1);
                info("IGNORE -- END Handshing --------------------");
                client.runAsyncTests(1, iterations);
                Thread.sleep(30*1000);
                client.runAsyncTests(1000, iterations);
                Thread.sleep(30*1000);
                client.runAsyncTests(10000, iterations);
                Thread.sleep(30*1000);
                client.runAsyncTests(100000, 1);
                Thread.sleep(30*1000*3);

            } catch (TException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "TException", e);
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Unknown general Exception", e);
            }
        client.shutdown();

    }
}
