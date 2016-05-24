package thrift.benchmark;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Created by thuy on 22/05/16.
 */
public class ServerMain {



    public static void main(String[] args) {
        try {
            int mode = 1; // default blocking server
            Scanner scanner = new Scanner(System.in);
            System.out.println("Mode 1 : Blocking Server");
            System.out.println("Mode 2 : Non-Blocking Server");
            System.out.println("Mode 3 :  Async Server");
            System.out.println("Enter Server Mode: ");
            mode = scanner.nextInt();

            switch (mode) {
                case 1:
                    BlockingServer blockingServer = new BlockingServer(9090);
                    blockingServer.start();
                    break;
                case 2:
                    NonBlockingServer nonBlockingServer = new NonBlockingServer(9091);
                    nonBlockingServer.start();
                    break;
                case 3:
                    AsyncServer server = new AsyncServer(9092);
                    server.start();
                    break;
                default:
                    System.out.println("Invalid input. Shutting down...");
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
