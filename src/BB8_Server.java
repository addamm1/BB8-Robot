import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

public class BB8_Server {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static PrintWriter networkOut;
    private static BufferedReader networkIn;
    private static int port = 50000;
    private static Motor motor1;
    private static Motor motor2;
    private static GpioController gpio;


    public static void main(String[] args) throws IOException {
        // setting up networking
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        networkOut = new PrintWriter(socket.getOutputStream(), true);
        networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        gpio = GpioFactory.getInstance();
        motor1  = new Motor(gpio, 25,24,23);
        motor2 = new Motor(gpio, 29,28,27);
        runRobot();
    }
    public static void runRobot() throws IOException {
        while(true) {
            String[] msg = networkIn.readLine().split(" ");
            if (msg[0].equals("RIGHT") && msg[3].equals("LEFT")) {
                if((msg[1].equals("FWD") || msg[1].equals("BWD")) && (msg[4].equals("FWD") || msg[4].equals("BWD"))) {
                    motor1.changeDir(msg[1]);
                    motor1.changeMagnitude(Integer.parseInt(msg[2]));
                    motor1.updatePins();
                    motor2.changeDir(msg[4]);
                    motor2.changeMagnitude(Integer.parseInt(msg[5]));
                    motor2.updatePins();
                }
                else{
                    motor1.exit();
                    motor2.exit();
                    gpio.shutdown();
                }
            }
            else {
                motor1.exit();
                motor2.exit();
                gpio.shutdown();
                break;
            }
        }
    }
    public static void errorExit() throws IOException {
        motor1.exit();
        motor2.exit();
        networkOut.println("SHUTDOWN");
        networkIn.close();
        networkOut.close();
        socket.close();
        serverSocket.close();
    }
}
