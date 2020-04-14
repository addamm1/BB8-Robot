import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

public class BB8_Server {
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static PrintWriter networkOut;
    public static BufferedReader networkIn;
    public static int port = 50000;

    public static void main(String[] args) throws IOException {
        // setting up networking
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        networkOut = new PrintWriter(socket.getOutputStream(), true);
        networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput dir1f = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "dir1f", PinState.LOW);
        GpioPinDigitalOutput dir1b = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "dir1b", PinState.LOW);
        GpioPinDigitalOutput dir2f = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "dir2f", PinState.LOW);
        GpioPinDigitalOutput dir2b = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "dir2b", PinState.LOW);
        runRobot();
    }
    public static void runRobot() throws IOException {
        while(true) {
            String[] msg = networkIn.readLine().split(" ");
            if (msg[0].equals("Right")) {
                for(int i = 0; i < 256; i++){

                }
            }
        }
    }
}
