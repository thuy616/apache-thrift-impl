import sys
import movie_service_impl
import logging

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.protocol.TBinaryProtocol import TBinaryProtocolAccelerated
from thrift.server import TServer
from thrift.server import TNonblockingServer
reload(sys)
sys.setdefaultencoding('utf-8')

from movieservice import MovieService

class PingHandler:
    def __init__(self):
        self.text = "Hello!"

    def ping(self):
        print("ping()")


if __name__ == '__main__':
    logger = logging.getLogger('thrift.server.TNonblockingServer')
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

    handler = movie_service_impl.MovieServiceAsyncHandler()

    processor = MovieService.Processor(handler)
    # processor = Ping.Processor(handler)
    transport = TSocket.TServerSocket(host="127.0.0.1", port=9090)

    t_factory = TBinaryProtocol.TBinaryProtocolFactory()
    p_factory = TBinaryProtocol.TBinaryProtocolAcceleratedFactory()
    server = TNonblockingServer.TNonblockingServer(processor, transport, t_factory, p_factory, threads=100)

    print 'Starting server'
    server.serve()
