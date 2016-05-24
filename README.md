# Benchmark Apache Thrift RPC and Serialization Framework

Implementation includes Java Server, Java Client, and Python Server.
This project tested the interoperability of Thrift for Java Client and Python Server communication.
The results are also compared to other rpc frameworks, GRPC and Avro.

Includes tests for data serialization/deserialization to binary, and end-to-end rpc calls.

## Installing Thrift

On Mac OS X, to unstall the codegeneration core

```
brew install thrift
```
To verify that thrift is install correctly, run
```
thrift --version
```
To know the location that thrift is install
```
which thrift
```

### Java Runtime
For Java runtime environment, make sure to inlcude libs/libthrift jar as dependency.

### Python Runtime
Create virtual environent, activate it then do
```
pip install thrift
```

### Deployment
This repo contains the already generated code for both Java and Python. If you modify the .thrift file, to regenerate code, do
for Java:
```
thrift -r -gen java movieservice.thrift
```
and for Python:
```
thrift -r -gen py movieservice.thrift
```

## Run Java application
```
gradle build
gradle installDist
```

The executables are in the build folder under build/install/thrift-benchmark/bin

## Run Python
Navigate to py folder
```
python SimpleServer.py
```





