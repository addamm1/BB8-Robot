import com.pi4j.io.gpio.*;


public class Motor {
    private boolean dir_fwd = true;
    private int magnitude = 0;
    private final GpioController gpio;
    private GpioPinPwmOutput dir1;
    private GpioPinPwmOutput dir2;
    private GpioPinPwmOutput magPin;
    private int dir1PinNum;
    private int dir2PinNum;
    private int magPinNum;

    public Motor(int dir1PinNum, int dir2PinNum, int magPinNum){
        this.dir1PinNum = dir1PinNum;
        this.dir2PinNum = dir2PinNum;
        this.magPinNum = magPinNum;
        gpio = GpioFactory.getInstance();
        dir1 = gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(this.dir1PinNum));
        dir2 = gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(this.dir2PinNum));
        magPin = gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(this.magPinNum));
        dir1.setPwm(0);
        dir2.setPwm(0);
        magPin.setPwm(0);
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
        magPin.setPwm(magnitude);
        if(magnitude == 0){
            dir1.setPwm(0);
            dir2.setPwm(0);
        }
        else{
            if(dir_fwd){
                dir1.setPwm(magnitude);
                dir2.setPwm(0);
            }
            else{
                dir1.setPwm(0);
                dir2.setPwm(magnitude);
            }
        }
    }
}
