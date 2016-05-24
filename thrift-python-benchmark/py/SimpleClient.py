import sys

from thrift.transport.TTransport import TMemoryBuffer

sys.path.append('gen-py')

from movieservice import MovieService
from ping import Ping

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol


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

        transport_out = TMemoryBuffer()
        protocol_out = TBinaryProtocol(transport_out)
        re.write(protocol_out)
        bytes = transport_out.getvalue()
        print("bytes len: " + len(bytes))

        # transport_in = TMemoryBuffer(serializedData)
        # protocol_in = TBinaryProtocol(transport_in)
        # movie_list = Movies()
        # movie_list.read(protocol_in)
        # print("deserializatime time: ")


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
