from thrift.transport.TTransport import TMemoryBuffer

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

from movieservice import MovieService


def main():
    # Make socket
    transport = TSocket.TSocket('localhost', 9090)

    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)

    # Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

    # Create a client to use the protocol encoder
    # client = MovieService.Client(protocol)

    client = MovieService.Client(protocol)

    # Connect!
    transport.open()
    print("Connected")

    try:
        client.ping()
        print ("ping succeeded!")

        re = client.getMovies();
        print ("getMovies succeeded!")
        print ("Result: " + str(re))

    except Exception as e:
        print("!@*^@!*^$")
        print(e.message)

    print("Done!")
    transport.close()

if __name__ == '__main__':
    try:
        main()
    except Thrift.TException as tx:
        print("exception!!!!!")
        print('%s' % tx.message)
