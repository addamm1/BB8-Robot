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

    public Motor(GpioController gpio, int dir1PinNum, int dir2PinNum, int magPinNum){
        this.dir1PinNum = dir1PinNum;
        this.dir2PinNum = dir2PinNum;
        this.magPinNum = magPinNum;
        this.gpio = gpio;
        dir1 = this.gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(this.dir1PinNum));
        dir2 = this.gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(this.dir2PinNum));
        magPin = gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(this.magPinNum));
        dir1.setShutdownOptions(true, PinState.LOW);
        dir2.setShutdownOptions(true, PinState.LOW);
        magPin.setShutdownOptions(true, PinState.LOW);
        dir1.setPwm(0);
        dir2.setPwm(0);
        magPin.setPwm(0);
    }

    public void changeDir(String dir){
        dir_fwd = dir.equals("FWD");
    }

    public void changeMagnitude(int magnitude){
        this.magnitude = magnitude;
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
    public void exit(){
        gpio.shutdown();
    }
}
