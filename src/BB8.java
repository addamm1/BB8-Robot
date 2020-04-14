import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class BB8 {
    private static Socket socket;
    private static PrintWriter networkOut;
    private static BufferedReader networkIn;
    private static String host = "localhost";
    private static int port = 50000;
    private static long timeout = 5;

    public static void main(String[] args) throws IOException, InterruptedException {
        // setting up networking
        socket = new Socket(host, port);
        networkOut = new PrintWriter(socket.getOutputStream(), true);
        networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        runRobotClient();
    }
    private static void runRobotClient() throws InterruptedException, IOException {
        while(!networkIn.ready()) {
            networkOut.println("RIGHT FWD 128 LEFT FWD 128");
            TimeUnit.SECONDS.sleep(timeout);
            networkOut.println("RIGHT FWD 128 LEFT BWD 128");
            TimeUnit.SECONDS.sleep(timeout);
            networkOut.println("RIGHT BWD 128 LEFT FWD 128");
            TimeUnit.SECONDS.sleep(timeout);
            networkOut.println("RIGHT BWD 128 LEFT BWD 128");
            TimeUnit.SECONDS.sleep(timeout);
            networkOut.println("RIGHT FWD 128 LEFT FWD 128");
            TimeUnit.SECONDS.sleep(timeout);
            networkOut.println("RIGHT FWD 0 LEFT FWD 0");
        }
        networkOut.println("SHUTDOWN");
    }
}
