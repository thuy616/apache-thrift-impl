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
            System.out.println("Enter Server Mode: ");
            mode = scanner.nextInt();
            int port = 9090;
            switch (mode) {
                case 1:
                    BlockingServer blockingServer = new BlockingServer(port);
                    blockingServer.start();
                    break;
                case 2:
                    NonBlockingServer nonBlockingServer = new NonBlockingServer(port);
                    nonBlockingServer.start();
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
