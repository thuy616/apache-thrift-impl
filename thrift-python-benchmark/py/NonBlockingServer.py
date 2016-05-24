import movie_service_resources

from movie_service_impl import MovieServiceImpl
from movieservice import MovieService

from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.server import TNonblockingServer


if __name__ == '__main__':
    handler = MovieServiceImpl()
    processor = MovieService.Processor(handler)
    p = 9091
    transport = TSocket.TServerSocket(port=p)
    tFactory = TTransport.TBufferedTransportFactory()
    pFactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TThreadedServer(processor, transport, tFactory, pFactory)
    print('Starting the NON blocking server...')
    print('Server listening on port ' + str(p))
    server.serve()
    print('done.')