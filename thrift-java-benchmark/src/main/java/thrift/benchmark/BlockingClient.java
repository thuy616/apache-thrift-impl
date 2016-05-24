package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
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
public class BlockingClient {
    private static final java.util.logging.Logger logger = Logger.getLogger(BlockingClient.class.getName());
    private FileHandler fh;

    public BlockingClient() {
        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
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

    private void testSerializer() {

        TTransport transport;

        try {
            int port = 9090;
            transport = new TSocket("localhost", port);

            TProtocol protocol = new TBinaryProtocol(transport);

            MovieService.Client client = new MovieService.Client(protocol);
            transport.open();

            Movies result = client.getMovies();
            for (int j=0; j<10; j++) {
                info("####################     ITERATION {0}     #####################", j);

                info("$$$ TEST TSERIALIZER $$$");
                TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
                byte[] serializedData = null;
                long startSer = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    serializedData = serializer.serialize(result);
                }
                long endSer = System.nanoTime();
                info("Serialized length: {0} bytes", serializedData.length);
                info("Average Serialized time: {0} nanoseconds", (endSer - startSer) / (float) 1000);

                // test TDeserializer
                info("$$$ TEST TDESERIALIZER $$$");
                TDeserializer deser = new TDeserializer(new TBinaryProtocol.Factory());
                Movies deserializedMovies = new Movies();
                long startDeser = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    deser.deserialize(deserializedMovies, serializedData);
                }
                long endDeser = System.nanoTime();
                info("Average De-Serialized time: {0} nanoseconds", (endDeser - startDeser) / (float) 1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void invoke() {
        TTransport transport;

        try {
            int port = 9090;
            transport = new TSocket("localhost", port);

            TProtocol protocol = new TBinaryProtocol(transport);

            MovieService.Client client = new MovieService.Client(protocol);
            transport.open();


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

            transport.close();

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    private Movies getMovies(MovieService.Client client, int count) throws TException {
        Movies result = null;
        logger.info("#### BLOCKING REQUEST ####  " + count + " CALLS ####");
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
        BlockingClient client = new BlockingClient();
//        client.runTestIteration(10);
        client.testSerializer();
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
