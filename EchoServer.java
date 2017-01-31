/**
 * Created by near on 24/01/17.
 */
import java.io.*;
import java.net.*;

class EchoServer  {

    public static void main(String []args) {

        BufferedReader br = null;
        PrintStream ps = null;
        String line = null;
        ServerSocket conn = null;
        Socket sock = null;
        int port = -1;

        if (args.length != 1) {
            System.out.println("usage: Server port");
            System.exit(1);
        }

        try {
            port = Integer.parseInt(args[0]);
            conn = new ServerSocket(port);
        }
        catch(IOException e) {
            System.out.println("Error creating server's socket : "+e.getMessage());
            System.exit(1);
        }

        try {
            sock = conn.accept();
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            ps = new PrintStream(sock.getOutputStream());

            line = br.readLine();
            System.out.println("Client's telling me : "+line);

            ps.println(line);
            br.close();
            ps.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}