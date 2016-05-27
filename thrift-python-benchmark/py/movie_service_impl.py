import sys
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
        # m = Movies(movies=self.db)
        # print("m: " + str(m))
        # transport_out = TMemoryBuffer()
        # protocol_out = TBinaryProtocol(transport_out)
        # m.write(protocol_out)
        # # byte array
        # serializedData = transport_out.getvalue()
        # print("serialization time: ")
        # print("bytes len: " + len(serializedData))
        #
        # transport_in = TMemoryBuffer(serializedData)
        # protocol_in = TBinaryProtocol(transport_in)
        # movie_list = Movies()
        # movie_list.read(protocol_in)
        # print("deserializatime time: ")

    def getMovie(self, id):
        for movie in self.db:
            if movie.id == id:
                return movie

    def getMovies(self):
        print("REQUEST RECEIVED...")
        m = Movies(movies=self.db)
        print("returning result...")
        return m

    def getMovieList(self):
        return self.db

    def getStruct(self, key):
        print('getStruct(%d)' % (key))
        return self.log[key]