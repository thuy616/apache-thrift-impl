import sys
import time
from thrift.transport.TTransport import TMemoryBuffer
from thrift.protocol import TBinaryProtocol
import movie_service_resources
from movieservice.ttypes import *
reload(sys)
sys.setdefaultencoding('utf-8')


class MovieServiceHandler:

    def __init__(self):
        self.db = movie_service_resources.read_movie_service_database()

    def ping(self):
        print("Ping(): Hello!")

    def getMovie(self, id):
        for movie in self.db:
            if movie.id == id:
                return movie

    def getMovies(self):
        print("REQUEST RECEIVED...")
        m = Movies(movies=self.db)
        # print("returning result...")
        return m

    def getMovieList(self):
        return self.db

    def getStruct(self, key):
        print('getStruct(%d)' % (key))
        return self.log[key]


class MovieServiceAsyncHandler:
    def __init__(self):
        self.db = movie_service_resources.read_movie_service_database()

    def ping(self):
        print("Ping(): Hello!")

    def getMovie(self, id):
        for movie in self.db:
            if movie.id == id:
                return movie

    def getMovies(self):
        print 'Assume that this work takes 5 seconds before returning movies'
        time.sleep(5)
        m = Movies(movies=self.db)
        # print("returning result...")
        return m

    def getMovieList(self):
        return self.db

