import com.pi4j.io.gpio.*;


public class Motor {
    private boolean dir_fwd = true;
    private int magnitude = 0;
    private final GpioController gpio;
    private GpioPinDigitalOutput dir1;
    private GpioPinDigitalOutput dir2;
    private GpioPinDigitalOutput magPin;
    private String dir1PinNum;
    private String dir2PinNum;
    private String magPinNum;

    public Motor(int dir1PinNum, int dir2PinNum, int magPinNum){
        this.dir1PinNum = "GPIO_" + dir1PinNum;
        this.dir2PinNum = "GPIO_" + dir2PinNum;
        this.magPinNum = "GPIO_" + magPinNum;
        gpio = GpioFactory.getInstance();
        dir1 = gpio.provisionDigitalOutputPin(RaspiPin.getPinByName(this.dir1PinNum), "dir1", PinState.LOW);
        dir2 = gpio.provisionDigitalOutputPin(RaspiPin.getPinByName(this.dir2PinNum), "dir2", PinState.LOW);
        magPin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByName(this.magPinNum), "dir2", PinState.LOW);
    }

    public void changeDir(){
        this.dir_fwd = !dir_fwd;
        updatePins();
    }

    public void changeMagnitude(int magnitude){
        this.magnitude = magnitude;
        updatePins();
    }
    public void updatePins(){

    }
}
