package thrift.benchmark;

import movieservice.Movies;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by thuy on 26/05/16.
 */
public class SerializationTest {
    private static final java.util.logging.Logger logger = Logger.getLogger(BlockingClient.class.getName());
    private FileHandler fh;
    private Movies moviesData;

    public SerializationTest() throws IOException {
        moviesData = MovieServiceUtil.parseMovies(MovieServiceUtil.getDefaultMoviesFile());
        SimpleDateFormat format = new SimpleDateFormat("MM_dd_yyyy_HHmmss");
        try {
            String dir = Paths.get("").toAbsolutePath().toString() + "//Logging";
            File directory = new File(dir);

            if (!directory.exists()) {
                directory.mkdir();
            }

            fh = new FileHandler(dir + "//Thrift_Serialization_Log_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.INFO);
        logger.addHandler(fh);
    }

    private static void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    private void run(int calls) throws TException {

        TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
        TDeserializer deser = new TDeserializer(new TBinaryProtocol.Factory());
        long totalSer = 0;
        long totalDeser = 0;
        byte[] bArray = serializer.serialize(moviesData);
        info("Serialized length: {0}", bArray.length);

        for (int i = 0; i < calls; i++) {
            long startSer = System.nanoTime();
            byte[] serializedData = serializer.serialize(moviesData);
            long endSer = System.nanoTime();
            totalSer += endSer - startSer;

            long startDeser = System.nanoTime();
            Movies deserializedMovies = new Movies();
            deser.deserialize(deserializedMovies, serializedData);
            long endDeser = System.nanoTime();
            totalDeser += endDeser - startDeser;
        }

        info("Avg Serialized time: \n{0}", totalSer / (float) calls);
        info("Avg De-Serialized time: \n{0}", totalDeser / (float) calls);
    }

    public static void main(String[] args) {
        try {
            SerializationTest tester = new SerializationTest();
            Scanner scanner = new Scanner(System.in);
            // prevent the program to run immediate
            // so that there is time to start Instrument
            // to record Activity Monitor
            System.out.println("Press any key to continue...");
            scanner.nextLine();
            for (int i = 0; i < 10; i++) {
                info("####################     ITERATION {0}     #####################", i);
                tester.run(10000);
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "IOException: ", e);
        } catch (TException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "TException: ", e);

        }
    }
}
