
class AirPurifier {
  private String brand;
  private boolean isTurnOne;
  private boolean childLockMode;
  private String mode;

  public AirPurifier(String brand) {
    this.brand = brand;
    isTurnOne = false;
    childLockMode = false;
    mode = "auto";
  }

  public String getBrand(){return brand;}
  public void turnOn() {
    if (isTurnOne)
      System.out.println("Airpurifier is aleady turned on with " + mode + " mode.");
    else {
      isTurnOne = true;
      System.out.println("Airpurifier is turned on with auto mode.");
    }
  }

  public void turnOff() {
    if (!isTurnOne)
      System.out.println("Airpurifier is aleady turned off.");
    else {
      isTurnOne = false;
      System.out.println("Airpurifier is turned off.");
    }
  }

  public void setTimerOn(String time, String mode) {
    System.out.println("Turn on airpurifier at " + time + " with " + mode + " mode.");
  }

  public void setTimerOff(String time) {
    System.out.println("Turn off airpurifier at " + time);
  }

  public void turnOnChildLockMode() {
    if (childLockMode)
      System.out.println("Child Lock mode is already turned on.");
    else {
      childLockMode = true;
      System.out.println("Turn on Child Lock mode.");
    }
  }

  public void turnOffChildLockMode() {
    if (!childLockMode)
      System.out.println("Child Lock mode is already turned off.");
    else {
      childLockMode = false;
      System.out.println("Turn off Child Lock mode.");
    }
  }

  public void changeMode(String mode) {
    this.mode = mode;
    System.out.println("Change mode to " + mode + " mode.");
  }
}

interface Command {
  void execute();
}

class TurnOnAirpurifier implements Command {

  private AirPurifier airPurifier;

  public TurnOnAirpurifier(AirPurifier a) {
    airPurifier = a;
  }

  public void execute() {
    airPurifier.turnOn();
  }
}

class TurnOffAirpurifier implements Command {
  private AirPurifier airPurifier;

  public TurnOffAirpurifier(AirPurifier a) {
    airPurifier = a;
  }

  public void execute() {
    airPurifier.turnOff();
  }
}

class SetTimerOn implements Command {
  private AirPurifier airPurifier;
  private String mode;
  private String time;

  public SetTimerOn(AirPurifier a, String mode, String time) {
    airPurifier = a;
    this.time = time;
    this.mode = mode;
  }

  public void execute() {
    airPurifier.setTimerOn(time, mode);
  }
}

class SetTimerOff implements Command {
  private AirPurifier airPurifier;
  private String time;

  public SetTimerOff(AirPurifier a, String time) {
    airPurifier = a;
    this.time = time;
  }

  public void execute() {
    airPurifier.setTimerOff(time);
  }
}

class TurnOnChildLockMode implements Command{
  private AirPurifier airPurifier;

  public TurnOnChildLockMode(AirPurifier a){
    airPurifier = a;
  }
  public void execute(){
    airPurifier.turnOnChildLockMode();
  }
}

class TurnOffChildLockMode implements Command{
  private AirPurifier airPurifier;

  public TurnOffChildLockMode(AirPurifier a){
    airPurifier = a;
  }
  public void execute(){
    airPurifier.turnOffChildLockMode();
  }
}

class ChangeMode implements Command{
  private AirPurifier airPurifier;
  private String mode;

  public ChangeMode(AirPurifier a, String mode){
    airPurifier = a;
    this.mode = mode;
  }
  public void execute(){
    airPurifier.changeMode(mode);
  }
}

class AirpurifierMobileApp{
  private Command command;

  public void SetCommand(Command c){
    command = c;
  }
  public void executeCommand(){
    command.execute();
  }
}

public class CommandPatternDemo {
  public static void main(String[] args) {
    AirpurifierMobileApp mobileApp = new AirpurifierMobileApp();
    AirPurifier smartAirPurifier = new AirPurifier("Xiaomi Smart Air Purifier");
    TurnOnAirpurifier turnOn = new TurnOnAirpurifier(smartAirPurifier);
    mobileApp.SetCommand(turnOn);
    System.out.println("");
    mobileApp.executeCommand();
    
    System.out.println("");
    ChangeMode changeMode = new ChangeMode(smartAirPurifier, "sleep");
    mobileApp.SetCommand(changeMode);
    mobileApp.executeCommand();

    System.out.println("");
    TurnOnChildLockMode turnOnChildLockMode = new TurnOnChildLockMode(smartAirPurifier);
    mobileApp.SetCommand(turnOnChildLockMode);
    mobileApp.executeCommand();

    System.out.println("");
    SetTimerOff setTimerOff = new SetTimerOff(smartAirPurifier, "3am");
    mobileApp.SetCommand(setTimerOff);
    mobileApp.executeCommand();

    System.out.println("");
    TurnOffAirpurifier turnOff = new TurnOffAirpurifier(smartAirPurifier);
    mobileApp.SetCommand(turnOff);
    mobileApp.executeCommand();
  }
}
