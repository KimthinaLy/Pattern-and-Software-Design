
interface ACMaintenanceHandler {
  void setNext(ACMaintenanceHandler handler);

  void handle(String problem);
}

class AirConditionerMaintenance implements ACMaintenanceHandler {
  protected ACMaintenanceHandler nextHandler = null;

  public void setNext(ACMaintenanceHandler handler) {
    nextHandler = handler;
  }

  public void handle(String problem) {
    if(nextHandler!=null)
      nextHandler. handle(problem);
  }
}

class ReplaceFilter extends AirConditionerMaintenance {
  public void handle(String problem) {
    System.out.println("Filter is successfully replaced.");
    super.handle(problem);
  }
}

class CleanTheCoil extends AirConditionerMaintenance {
  public void handle(String problem) {
      System.out.println("Evaporator coil (indoor) and condenser coil (outdoor) are successfully cleaned.");
    super.handle(problem);
  }
}

class CheckDrainLine extends AirConditionerMaintenance {
  public void handle(String problem) {
    System.out.println("Drain line is successfully checked to prevent/stop clogs and leaks.");
    super.handle(problem);
  }
}

class CleanTheOutDoorUnit extends AirConditionerMaintenance {
  public void handle(String problem) {
      System.out.println("The outdoor unit is successfully cleaned for proper airflow.");
    super.handle(problem);
  }
}

class InspectForLeaks extends AirConditionerMaintenance {
  public void handle(String problem) {
    if(problem == "leaks" || problem == "need all maintenance"){
      System.out.println("The window seals and refrigerant lines are successfully inspect to prevent/stop the leaks.");
    }
    super.handle(problem);
  }
}

public class ChainOfResponsibilityDemo {
  public static void client(ACMaintenanceHandler d, String problem) {
    System.out.println("\nAC problem: " + problem);
    d.handle(problem);
  }

  public static void main(String[] args) {
    ReplaceFilter replaceFilter = new ReplaceFilter();
    CleanTheCoil cleanTheCoil = new CleanTheCoil();
    CheckDrainLine checkDrainLine = new CheckDrainLine();
    CleanTheOutDoorUnit cleanTheOutDoorUnit = new CleanTheOutDoorUnit();
    InspectForLeaks inspectForLeaks = new InspectForLeaks();

    replaceFilter.setNext(cleanTheCoil);
    cleanTheCoil.setNext(checkDrainLine);
    checkDrainLine.setNext(cleanTheOutDoorUnit);
    cleanTheOutDoorUnit.setNext(inspectForLeaks);

    client(replaceFilter, "need all maintenance");

    client(checkDrainLine, "leaks");

    client(replaceFilter, "cooling inefficiency");

    replaceFilter.setNext(cleanTheCoil);
    cleanTheCoil.setNext(checkDrainLine);
    checkDrainLine.setNext(inspectForLeaks);
    client(replaceFilter, "***need all maintenance");
  }
}
