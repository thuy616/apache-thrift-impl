package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by thuy on 21/05/16.
 */
public class AsyncServer {

    private static final java.util.logging.Logger logger = Logger.getLogger(AsyncServer.class.getName());
    public static MovieServiceAsyncImpl handler;
    public static MovieService.AsyncProcessor processor;
    public static int port;
    public static TServer server;
    private FileHandler fh;

    public static class MovieListRequest {
        public AsyncMethodCallback callback;

        public MovieListRequest(AsyncMethodCallback callback) {
            this.callback = callback;
        }
    }

//    public static class MovieServiceWorker implements Runnable {
//
//        private BlockingQueue<MovieListRequest> requestQueue;
//
//        public MovieServiceWorker() {
//            requestQueue = new LinkedBlockingDeque<MovieListRequest>();
//        }
//
//        public BlockingQueue<MovieListRequest> getQ() {
//            return requestQueue;
//        }
//
//        @Override
//        public void run() {
//            try {
//                while(true) {
//                    MovieListRequest req = requestQueue.take();
//                    Thread.sleep(10000);
//                    req.callback.onComplete(
//
//                    );
//                }
//            }
//        }
//    }

    public AsyncServer(int port) throws IOException, TTransportException {
        this.port = port;
        Movies moviesData = MovieServiceUtil.parseMovies(MovieServiceUtil.getDefaultMoviesFile());
        handler = new MovieServiceAsyncImpl(moviesData);
        processor = new MovieService.AsyncProcessor(handler);
        TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
        // Half-Sync/Half-Async server
        server = new THsHaServer(new THsHaServer.Args(serverTransport)
                .processor(processor)
                .protocolFactory(new TBinaryProtocol.Factory())
                .minWorkerThreads(4)
                .maxWorkerThreads(64));



        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Thrift_Server_NON_Blocking_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);

    }

    public void start() {
        logger.info("Starting the ASYNC server...");
        logger.info("Server listening on port: " + port);
        server.serve();
    }

}
