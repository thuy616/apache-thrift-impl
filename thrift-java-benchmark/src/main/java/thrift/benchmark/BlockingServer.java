package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by thuy on 21/05/16.
 * NonBlocking Server that uses Java non-blocking IO under the hood
 */
public class BlockingServer {
    private static final java.util.logging.Logger logger = Logger.getLogger(BlockingServer.class.getName());
    public static MovieServiceImpl handler;
    public static MovieService.Processor processor;
    public static int port;
    public static TServer server;
    private FileHandler fh;

    public BlockingServer(int port) throws IOException, TTransportException {
        this.port = port;
        Movies moviesData = MovieServiceUtil.parseMovies(MovieServiceUtil.getDefaultMoviesFile());
        handler = new MovieServiceImpl(moviesData);
        processor = new MovieService.Processor(handler);
        TServerTransport serverTransport = new TServerSocket(port);
        server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
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

            fh = new FileHandler(dir + "//Thrift_Server_Blocking_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);
    }

    public void start() {
        logger.info("Starting the simple BLOCKING server...");
        logger.info("Server listening on port: " + port);
        server.serve();
    }

}
