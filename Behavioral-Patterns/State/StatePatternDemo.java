
class Water {
  private double volume;
  private double Celsius;
  private WaterState state;

  public Water(WaterState state, double volume, double Celsius) {
    changeState(state);
    this.volume = volume;
    this.Celsius = Celsius;
  }

  public void setCelsius(double c) {
    Celsius = c;
  }

  public double getCelsius() {
    return Celsius;
  }

  public double getVolume() {
    return volume;
  }

  public void changeState(WaterState state) {
    System.out.println("Current state: " + state.getStateName() + ".");
    this.state = state;
    state.setWater(this);
  }

  public void heat() {
    state.heat();
  }

  public void heat(double temperature) {
    state.heat(temperature);
  }

  public void cool() {
    state.cool();
  }

  public void cool(double temperature) {
    state.cool(temperature);
  }
}

abstract class WaterState {
  protected String stateName;
  protected Water water;

  public void setWater(Water w) {
    water = w;
  }

  public String getStateName() {
    return stateName;
  }

  abstract void heat();

  abstract void heat(double temperature);

  abstract void cool();

  abstract void cool(double temperature);
}

class Frozen extends WaterState {
  public Frozen() {
    stateName = "frozen";
  }

  public void heat() {
    System.out.println("Change state from frozen to cold.");
    water.changeState(new Cold());
  }

  public void heat(double temperature){
    if(temperature > 0 && temperature < 16){
      System.out.println("Change state from frozen to cold.");
      water.changeState(new Cold());
    }else if(temperature < 36){
      System.out.println("Change state from frozen to normal temperature.");
      water.changeState(new NormalTemperature());
    }else if(temperature < 61){
      System.out.println("Change state from frozen to warm.");
      water.changeState(new Warm());
    }else if(temperature < 100){
      System.out.println("Change state from frozen to hot.");
      water.changeState(new Hot());
    }else if(temperature >= 100){
      System.out.println("Change state from frozen to boiled.");
      water.changeState(new Boiled());
    }
  }

  public void cool() {
    System.out.println("No state change.");
    System.out.println("Current state: frozen.");
  }

  public void cool(double temperature) {
    if(temperature <= 0)
      cool();
  }
}

class Cold extends WaterState {
  public Cold() {
    stateName = "cold";
  }

  public void heat() {
    System.out.println("Change state from cold to normal temperature");
    water.changeState(new NormalTemperature());
  }

  public void heat(double temperature){
    if(temperature > 15 && temperature < 36){
      System.out.println("Change state from cold to normal temperature.");
      water.changeState(new NormalTemperature());
    }else if(temperature < 61){
      System.out.println("Change state from cold to warm.");
      water.changeState(new Warm());
    }else if(temperature < 100){
      System.out.println("Change state from cold to hot.");
      water.changeState(new Hot());
    }else if(temperature >= 100){
      System.out.println("Change state from cold to boiled.");
      water.changeState(new Boiled());
    }
  }

  public void cool() {
    System.out.println("Change state from cold to frozen");
    water.changeState(new Frozen());
  }

  public void cool(double temperature) {
    if(temperature < 1)
      cool();
  }
}

class NormalTemperature extends WaterState {
  public NormalTemperature() {
    stateName = "normal temperature";
  }

  public void heat() {
    System.out.println("Change state from normal temperature to warm.");
    water.changeState(new Warm());
  }

  public void heat(double temperature){
    if(temperature > 35 && temperature < 61){
      heat();
    }else if(temperature < 100){
      System.out.println("Change state from normal temperature to hot.");
      water.changeState(new Hot());
    }else if(temperature >= 100){
      System.out.println("Change state from normal temperature to boiled.");
      water.changeState(new Boiled());
    }
  }
  public void cool() {
    System.out.println("Change state from normal temperature to cold.");
    water.changeState(new Cold());
  }
  public void cool(double temperature) {
    if(temperature < 1){
      System.out.println("Change state from normal temperature to frozen.");
      water.changeState(new Frozen());
    }else if(temperature <16){
      cool();
    } 
  }
}

class Warm extends WaterState {
  public Warm() {
    stateName = "warm";
  }

  public void heat() {
    System.out.println("Change state from warm to hot.");
    water.changeState(new Hot());
  }

  public void heat(double temperature){
    if(temperature > 60 && temperature < 100){
      heat();
    }else if(temperature >= 100){
      System.out.println("Change state from warm to boiled.");
      water.changeState(new Boiled());
    }
  }

  public void cool() {
    System.out.println("Change state from warm to normal temperature.");
    water.changeState(new NormalTemperature());
  }

  public void cool(double temperature) {
    if(temperature < 1){
      System.out.println("Change state from warm to frozen.");
      water.changeState(new Frozen());
    }else if(temperature < 16){
      System.out.println("Change state from warm to cold.");
      water.changeState(new Cold());
    }else if(temperature < 36){
      cool();
    }
  }
}

class Hot extends WaterState {
  public Hot() {
    stateName = "hot";
  }

  public void heat() {
    System.out.println("Change state from hot to boiled.");
    water.changeState(new Boiled());
  }

  public void heat(double temperature){
    if(temperature >= 100){
      heat();
    }
  }

  public void cool() {
    System.out.println("Change state from hot to warm.");
    water.changeState(new Warm());
  }

  public void cool(double temperature) {
    if(temperature < 1){
      System.out.println("Change state from hot to frozen.");
      water.changeState(new Frozen());
    }else if(temperature < 16){
      System.out.println("Change state from hot to cold.");
      water.changeState(new Cold());
    }else if(temperature < 36){
      System.out.println("Change state from hot to normal temperature.");
      water.changeState(new NormalTemperature());
    }else if(temperature < 61){
      cool();
    }
  }
}

class Boiled extends WaterState {
  public Boiled() {
    stateName = "boiled";
  }

  public void heat() {
    System.out.println("No state change.");
    System.out.println("Current state: boiled.");
  }

  public void heat(double temperature){
    if(temperature >= 100)
      heat();
  }

  public void cool() {
    System.out.println("Change state from boiled to hot.");
    water.changeState(new Hot());
  }

  public void cool(double temperature) {
    if(temperature < 1){
      System.out.println("Change state from boiled to frozen.");
      water.changeState(new Frozen());
    }else if(temperature < 16){
      System.out.println("Change state from boiled to cold.");
      water.changeState(new Cold());
    }else if(temperature < 36){
      System.out.println("Change state from boild to normal temperature.");
      water.changeState(new NormalTemperature());
    }else if(temperature < 61){
      System.out.println("Change state from hot to warm.");
      water.changeState(new Warm());
    }else if(temperature <100){
      cool();
    }
  }
}

public class StatePatternDemo {
  public static void main(String[] args) {
    Water freshWater = new Water(new Frozen(), 3, 0);
    System.out.println("\nProcessing with " + freshWater.getVolume() + " liters of fresh water at temperature of " + freshWater.getCelsius() + " Celsius.");

    System.out.println("\nHeat water.");
    freshWater.heat();

    System.out.println("\nHeat water at 55 Celsius.");
    freshWater.heat(55);

    System.out.println("\nHeat water.");
    freshWater.heat();

    System.out.println("\nHeat water.");
    freshWater.heat();

    System.out.println("\nCool water at 20 Celsius.");
    freshWater.cool(20);

    System.out.println("\nCool water at -4 Celsius.");
    freshWater.cool(-4);

  }
}