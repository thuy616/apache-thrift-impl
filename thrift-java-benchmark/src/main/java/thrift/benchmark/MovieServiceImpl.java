package thrift.benchmark;

import movieservice.Movie;
import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.TException;

import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thuy on 21/05/16.
 */
public class MovieServiceImpl implements MovieService.Iface {

    private URL moviesFile;
    private Movies moviesData;

    private static final java.util.logging.Logger logger = Logger.getLogger(MovieServiceImpl.class.getName());


    public MovieServiceImpl(Movies moviesData) {
        this.moviesData = moviesData;
    }

    @Override
    public Movie getMovie(long id) throws TException {
        for (Movie movie: moviesData.getMovies()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public Movies getMovies() throws TException {
        logger.log(Level.INFO, "Request get Movies");
        return moviesData;
    }

    @Override
    public List<Movie> getMovieList() throws TException {
        logger.log(Level.INFO, "Request get LIST");
        return moviesData.getMovies();
    }
}
