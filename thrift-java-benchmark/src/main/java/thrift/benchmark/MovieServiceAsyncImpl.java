package thrift.benchmark;

import movieservice.MovieService;
import movieservice.Movies;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thuy on 21/05/16.
 */
public class MovieServiceAsyncImpl implements MovieService.AsyncIface {
    private static final java.util.logging.Logger logger = Logger.getLogger(MovieServiceAsyncImpl.class.getName());

//    private BlockingQueue<AsyncServer.MovieListRequest> requestQ;

    private MovieServiceImpl movieService;

    public MovieServiceAsyncImpl(Movies moviesData) throws IOException {
        movieService = new MovieServiceImpl(moviesData);
    }

    @Override
    public void getMovie(long id, AsyncMethodCallback resultHandler) throws TException {

    }

    @Override
    public void getMovies(AsyncMethodCallback resultHandler) throws TException {
        logger.log(Level.INFO, "Request received...");
        resultHandler.onComplete(movieService.getMovies());
    }

    @Override
    public void getMovieList(AsyncMethodCallback resultHandler) throws TException {
        logger.log(Level.INFO, "Request received...");
        resultHandler.onComplete(movieService.getMovieList());
    }


}
