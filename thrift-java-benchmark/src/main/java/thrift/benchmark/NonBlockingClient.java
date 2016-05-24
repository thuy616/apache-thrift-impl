package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.*;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.File;
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
public class NonBlockingClient {
    private static final java.util.logging.Logger logger = Logger.getLogger(NonBlockingClient.class.getName());
    private FileHandler fh;
    private MovieService.Client client;
    private TTransport transport;
    private int port;

    public NonBlockingClient(int port) throws TTransportException {

        this.port = 9091;

        // difference here
        // TFramedTransport wrapping normal TSocket transport.
        // Non blocking server requires client to use TFramedTransport which would frame
        // the data sent over the wire. Fire up the server and send a request using the client.
        // You will see the same results as before, this time using non blocking mode.
        transport = new TFramedTransport(new TSocket("localhost", this.port));

        TProtocol protocol = new TBinaryProtocol(transport);

        client = new MovieService.Client(protocol);
        transport.open();

        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Thrift_Client_NON_Blocking_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);
    }

    private void invoke() {

        try {
            Movies result = client.getMovies();

            // test Tserializer
            info("$$$ TEST TSERIALIZER $$$");
            TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
            long startSer = System.nanoTime();
            byte[] serializedData = serializer.serialize(result);
            long endSer = System.nanoTime();
            info("Serialized length: {0} bytes", serializedData.length);
            info("Serialized time: {0} nanoseconds", endSer - startSer);

            // test TDeserializer
            info("$$$ TEST TDESERIALIZER $$$");
            TDeserializer deser = new TDeserializer(new TBinaryProtocol.Factory());
            Movies deserializedMovies = new Movies();
            long startDeser = System.nanoTime();
            deser.deserialize(deserializedMovies, serializedData);
            long endDeser = System.nanoTime();
            info("De-Serialized time: {0} nanoseconds", endDeser - startDeser);


            getMovies(client, 1);
            getMovies(client, 1000);
            getMovies(client, 10000);
            getMovies(client, 100000);


        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private void shutdown() {
        info("Shutting down");
        transport.close();
    }

    private Movies getMovies(MovieService.Client client, int count) throws TException {
        logger.info("#### NON BLOCKING REQUEST ####  " + count + " CALLS ####");
        Movies result = null;
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            result = client.getMovies();
        }
        long end = System.nanoTime();
        long duration = end - start;
        logTransmissionTime(duration);
        return result;
    }

    public static void main(String[] args) {
        NonBlockingClient client = null;
        try {
            client = new NonBlockingClient(9091);
        } catch (TTransportException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Failed to initialize client - ", e);
        }
        client.runTestIteration(10);
        client.shutdown();
    }

    public void runTestIteration(int iteration) {
        for (int i = 0; i < iteration; i++) {
            info("***********************   ITERATION {0}   ***********************", i);
            info("           START           ");
            invoke();
        }

    }

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private static void logTransmissionTime(long value) {
        info("Transmission time: {0} nanoseconds ===== {1} milliseconds ", value, value / (double) 1000000);

    }
}
