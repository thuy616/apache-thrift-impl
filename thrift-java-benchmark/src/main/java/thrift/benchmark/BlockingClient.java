package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by thuy on 21/05/16.
 */
public class BlockingClient {
    private static final java.util.logging.Logger logger = Logger.getLogger(BlockingClient.class.getName());
    private FileHandler fh;
    private TTransport transport;

    public BlockingClient() {
        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging//Thrift";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Thrift_Client_Blocking_Log_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);
    }

    private void invoke(int iterations) {


        try {
            int port = 9090;
            transport = new TSocket("localhost", port);

            TProtocol protocol = new TBinaryProtocol(transport);

            MovieService.Client client = new MovieService.Client(protocol);
            transport.open();

            getMovies(client, 1, iterations);
            getMovies(client, 1000, iterations);
            getMovies(client, 10000, iterations);
            getMovies(client, 100000, iterations);

            transport.close();

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private void shutdown() {
        transport.close();
    }

    private void getMovies(MovieService.Client client, int count, int iterations) throws TException {
        info("#### BLOCKING REQUEST ####  {0} CALLS ####", count);
        long totalElapsed = 0;
        for (int j=0; j<iterations; j++) {
            info("=========== ITERATION {0} ==============", j);
            long start = System.nanoTime();
            for (int i = 0; i < count; i++) {
                Movies result = client.getMovies();
            }
            long end = System.nanoTime();
            long duration = end - start;
            totalElapsed += duration;
            logTransmissionTime(duration);
        }
        info("AVERAGE transmission time: {0}", totalElapsed/(float)iterations);
    }

    public static void main(String[] args) {
        BlockingClient client = new BlockingClient();
        Scanner scanner = new Scanner(System.in);
        while(scanner.nextLine()!="q") {
            System.out.println("Press q to quit. Press any other key to continue...");
            client.invoke(10);
        }
        client.shutdown();
    }


    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private static void logTransmissionTime(long value) {
        info("Transmission time: {0} nanoseconds ===== {1} milliseconds ", value, value / (double) 1000000);
    }

}
