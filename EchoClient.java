/**
 * Created by near on 24/01/17.
 */

/**
 *   What does the client ?
 * - Connect to the server
 * - Initialize a BufferedReader and a PrintStream thank to the communication socket
 * - Send a line of text to the server
 * - Wait for a line of text from the server and print it
 */

//Imports
import java.io.*;
import java.net.*;

class EchoClient  {

    public static void main(String[] args) {

        BufferedReader br = null;
        PrintStream ps = null;
        String line = null;
        Socket sock = null;
        int port = -1;

        if (args.length != 3) {
            System.out.println("usage: EchoClient ip_server port message");
            System.exit(1);
        }

        try {
            port = Integer.parseInt(args[1]);
            sock = new Socket(args[0],port);
        }
        catch(IOException e) {
            System.out.println("Error connecting to the server : "+e.getMessage());
            System.exit(1);
        }

        try {
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            ps = new PrintStream(sock.getOutputStream());

            ps.println(args[2]);
            line = br.readLine();
            System.out.println("Server's answer : "+line);
            br.close();
            ps.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}