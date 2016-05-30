package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by thuy on 21/05/16.
 */
public class NonBlockingServer {
    private static final java.util.logging.Logger logger = Logger.getLogger(NonBlockingServer.class.getName());
    public static MovieServiceImpl handler;
    public static MovieService.Processor processor;
    public static int port;
    public static TServer server;
    private FileHandler fh;

    public NonBlockingServer(int port) throws IOException, TTransportException {
        this.port = port;
        Movies moviesData = MovieServiceUtil.parseMovies(MovieServiceUtil.getDefaultMoviesFile());
        handler = new MovieServiceImpl(moviesData);
        processor = new MovieService.Processor(handler);
        try {
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
            server = new THsHaServer(new THsHaServer.Args(serverTransport).
                    processor(processor));

            SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");

            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Thrift_Server_NON_Blocking_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (TTransportException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "TTransport exception -", e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Unknown exception -", e);

        }
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);

    }

    public void start() {
        logger.info("Starting the NON server...");
        logger.info("Server listening on port: " + port);
        server.serve();
    }

}
