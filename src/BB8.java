import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BB8 {
    public static Socket socket;
    public static PrintWriter networkOut;
    public static BufferedReader networkIn;
    public static String host = "localhost";
    public static int port = 50000;

    public static void main(String[] args) throws IOException {
        // setting up networking
        socket = new Socket(host, port);
        networkOut = new PrintWriter(socket.getOutputStream(), true);
        networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
