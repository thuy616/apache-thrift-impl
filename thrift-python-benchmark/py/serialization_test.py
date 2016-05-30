from thrift.protocol.TBinaryProtocol import TBinaryProtocolAccelerated

import movie_service_resources
import logging
import os
import time
import datetime
import timeit
from movieservice.ttypes import *


class SerializationTest:
    def __init__(self):
        self.db = movie_service_resources.read_movie_service_database()
        self.movie_list = Movies(movies=[])
        for m in self.db:
            self.movie_list.movies.append(m)
        # print("movie list len:", str(len(self.movie_list.movies)))
        # print(str(self.movie_list.movies[24]))
        logger = logging.getLogger('SerializationTest')
        logger.setLevel(logging.DEBUG)  # create file handler which logs even debug messages
        directory = "Logging"
        if not os.path.exists(directory):
            os.makedirs(directory)
        ts = time.time()
        st = datetime.datetime.fromtimestamp(ts).strftime('%Y_%m_%d_%H%M%S')
        filepath = directory + "//Serialization_" + st + ".log"

        fh = logging.FileHandler(filepath)
        fh.setLevel(logging.INFO)
        # create console handler with a higher log level
        ch = logging.StreamHandler()
        ch.setLevel(logging.INFO)  # handler = PingHandler()
        # create formatter and add it to the handlers
        formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        fh.setFormatter(formatter)
        ch.setFormatter(formatter)
        # add the handlers to the logger
        logger.addHandler(fh)
        logger.addHandler(ch)
        self.logger = logger
        self.logger.log(logging.INFO, "initializing completed...")

    def run_tbinary(self):
        total_ser = 0
        total_deser = 0
        serialized_len = 0
        n = 10000
        for i in range(0, n):
            transport_out = TTransport.TMemoryBuffer()
            protocol_out = TBinaryProtocol.TBinaryProtocol(transport_out)
            # self.logger.log(logging.INFO, "here!!")
            # movie = self.movie_list.movies[0]
            self.movie_list.validate()
            tic = timeit.default_timer()
            self.movie_list.write(protocol_out)
            # movie.write(protocol_out)
            b_array = transport_out.getvalue() # this is the serialized data
            elapsed = timeit.default_timer() - tic
            total_ser = total_ser + elapsed
            # self.logger.log(logging.INFO, "serialization done, time: %s", elapsed*(10**9))
            # the string 'bytes' can be written out to disk
            #  to be read in at a different time
            serialized_len = len(b_array)
            # print(str(b_array))

            # print("start de-serializing...")
            transport_in = TTransport.TMemoryBuffer(b_array)
            protocol_in = TBinaryProtocol.TBinaryProtocol(transport_in)
            tic2 = timeit.default_timer()
            new_movies = Movies()
            try:
                new_movies.read(protocol_in)
            except EOFError as err:
                # self.logger.log(logging.ERROR, "silence EOF error - thrift's bug ")
                pass
            finally:
                elapsed2 = timeit.default_timer() - tic2
                new_movies.validate()
                total_deser = total_deser + elapsed2
                # self.logger.log(logging.INFO, "de-serialization done, time: %s", elapsed2*(10**9))
            transport_in.close()
            transport_out.close()

        self.logger.log(logging.INFO, "serialized len: %s bytes", serialized_len)
        avg_ser = (total_ser*(10**9))/n
        avg_deser = (total_deser*(10**9))/n
        self.logger.log(logging.INFO, "Serialization time: \n%s", avg_ser)
        self.logger.log(logging.INFO, "De-serialization time: \n%s", avg_deser)

    def run_tbinaryaccelerated(self):
        total_ser = 0
        total_deser = 0
        serialized_len = 0
        n = 10000
        for i in range(0, n):
            transport_out = TTransport.TMemoryBuffer()
            protocol_out = TBinaryProtocol.TBinaryProtocolAccelerated(transport_out)
            # self.logger.log(logging.INFO, "here!!")
            # movie = self.movie_list.movies[0]
            self.movie_list.validate()
            tic = timeit.default_timer()
            self.movie_list.write(protocol_out)
            # movie.write(protocol_out)
            b_array = transport_out.getvalue() # this is the serialized data
            elapsed = timeit.default_timer() - tic
            total_ser = total_ser + elapsed
            # self.logger.log(logging.INFO, "serialization done, time: %s", elapsed*(10**9))
            # the string 'bytes' can be written out to disk
            #  to be read in at a different time
            serialized_len = len(b_array)
            # print(str(b_array))

            # print("start de-serializing...")
            transport_in = TTransport.TMemoryBuffer(b_array)
            protocol_in = TBinaryProtocol.TBinaryProtocolAccelerated(transport_in)
            tic2 = timeit.default_timer()
            new_movies = Movies()
            try:
                new_movies.read(protocol_in)
            except EOFError as err:
                # self.logger.log(logging.ERROR, "silence EOF error - thrift's bug ")
                pass
            finally:
                elapsed2 = timeit.default_timer() - tic2
                new_movies.validate()
                total_deser = total_deser + elapsed2
                # self.logger.log(logging.INFO, "de-serialization done, time: %s", elapsed2*(10**9))
            transport_in.close()
            transport_out.close()

        self.logger.log(logging.INFO, "serialized len: %s bytes", serialized_len)
        avg_ser = (total_ser*(10**9))/n
        avg_deser = (total_deser*(10**9))/n
        self.logger.log(logging.INFO, "Serialization time: \n%s", avg_ser)
        self.logger.log(logging.INFO, "De-serialization time: \n%s", avg_deser)

if __name__ == '__main__':

    tester = SerializationTest()
    for j in range(0, 10):
        tester.logger.log(logging.INFO, "######### ITERATION %s ##########", j)
        # tester.run_tbinary()
        tester.logger.log(logging.INFO, "## ACCELERATED %s ##", j)
        tester.run_tbinaryaccelerated()
    tester.logger.log(logging.INFO, "######### FINISHED ##########")