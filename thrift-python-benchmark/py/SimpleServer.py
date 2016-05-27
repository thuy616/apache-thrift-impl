import sys
import movie_service_impl
import logging

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
reload(sys)
sys.setdefaultencoding('utf-8')

from movieservice import MovieService

class PingHandler:
    def __init__(self):
        self.text = "Hello!"

    def ping(self):
        print("ping()")


if __name__ == '__main__':
    logger = logging.getLogger('thrift.server.TServer')
    handler = movie_service_impl.MovieServiceHandler()
    logger.setLevel(logging.DEBUG)
    # create file handler which logs even debug messages
    fh = logging.FileHandler('thrift_python_server.log')

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

    # print("data: " + str(handler.db))
    # print("number of movies: " + str(len(handler.db)))
    processor = MovieService.Processor(handler)
    # processor = Ping.Processor(handler)
    transport = TSocket.TServerSocket(port=9090)
    tfactory = TTransport.TBufferedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()
    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

    # You could do one of these for a multithreaded server
    # server = TServer.TThreadedServer(
    #     processor, transport, tfactory, pfactory)
    # server = TServer.TThreadPoolServer(
    #     processor, transport, tfactory, pfactory)

    logger.log(logging.INFO, "Server starting on port 9090...")
    server.serve()
    print('done.')
