package thrift.benchmark;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import movieservice.Movies;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TSimpleJSONProtocol;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by thuy on 21/05/16.
 */
public class MovieServiceUtil {

    private static final Logger logger = Logger.getLogger(MovieServiceUtil.class.getName());

    public static URL getDefaultMoviesFile() {
        return MovieServiceUtil.class.getResource("movie_service_db.json");
    }

    private static final Gson gson = new Gson();

    public static Movies parseMovies(URL file) throws IOException {

        InputStream input = file.openStream();
        try {
            Reader reader = new InputStreamReader(input);
            try {
                JsonReader jsonReader = new JsonReader(reader);
                Movies movies = gson.fromJson(jsonReader, Movies.class);
                return movies;
            } finally {
                reader.close();
            }
        } finally {
            input.close();
        }
    }
}
