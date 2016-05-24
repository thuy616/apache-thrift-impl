package thrift.benchmark;

import movieservice.Movies;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by thuy on 21/05/16.
 */
public class MovieServiceUtilTest {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Test
    public void parseMovies() throws Exception {
        String path = "src/test/java/thrift/benchmark/test.json";
        URL url = new File(path).toURI().toURL();
        logger.info(String.format("url path: %s", url.getPath()));

        //URL url = MovieServiceUtil.getDefaultMoviesFile();

        Movies movies = MovieServiceUtil.parseMovies(url);
        String expectedTitle = "Captain America: Civil War";

        logger.info(String.format("number of movies: %s", movies.getMovies().size()));
        logger.info(String.format("first movie title: %s", movies.getMovies().get(0).getTitle()));
        assertEquals(expectedTitle, movies.getMovies().get(0).getTitle());
    }

}