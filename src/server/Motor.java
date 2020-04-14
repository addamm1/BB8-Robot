import com.pi4j.io.gpio.*;

public class Motor {
    private boolean dir_fwd = true;
    private int magnitude = 0;
    private final GpioController gpio;
    private GpioPinPwmOutput dir1;
    private GpioPinPwmOutput dir2;
    private int dir1PinNum;
    private int dir2PinNum;

    public Motor(GpioController gpio, int dir1PinNum, int dir2PinNum){
        this.dir1PinNum = dir1PinNum;
        this.dir2PinNum = dir2PinNum;
        this.gpio = gpio;
        dir1 = this.gpio.provisionSoftPwmOutputPin(RaspiPin.getPinByAddress(this.dir1PinNum));
        dir2 = this.gpio.provisionSoftPwmOutputPin(RaspiPin.getPinByAddress(this.dir2PinNum));
        dir1.setShutdownOptions(true, PinState.LOW);
        dir2.setShutdownOptions(true, PinState.LOW);
        dir1.setPwm(0);
        dir2.setPwm(0);
    }

    public void changeDir(String dir){
        dir_fwd = dir.equals("FWD");
    }

    public void changeMagnitude(int magnitude){
        this.magnitude = magnitude;
    }
    public void updatePins(){
        if(magnitude == 0){
            dir1.setPwm(0);
            dir2.setPwm(0);
        }
        else if (magnitude >= 0 && magnitude <= 255){
            if(dir_fwd){
                dir1.setPwm(magnitude);
                dir2.setPwm(0);
            }
            else{
                dir1.setPwm(0);
                dir2.setPwm(magnitude);
            }
        }
        else{
            exit();
        }
    }
    public void exit(){
        gpio.shutdown();
    }
}
