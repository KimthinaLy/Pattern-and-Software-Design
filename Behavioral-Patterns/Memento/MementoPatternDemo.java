import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

interface Originator {
  CalculationMemento save();
}

class Calculator implements Originator{
  private String mathExpression;
  private double answer;

  public Calculator(String mathExpression, double answer){
    this.mathExpression = mathExpression;
    this.answer = answer;
  }
  public void setMathExpression(String mathExpression){
    this.mathExpression = mathExpression;
  }
  public void setAnswer(double answer){
    this.answer = answer;
  }
  public void show(){
    System.out.println(mathExpression + " = " + answer);
  }

  @Override
  public CalculationMemento save(){
    return new CalculationMemento(this, mathExpression, answer);
  }
}

interface Memento{
  void restore();
}

class CalculationMemento implements Memento{
  private String mathExpression;
  private double answer;
  private Originator originator;

  public CalculationMemento(Originator originator, String mathExpression, double answer){
    this.originator = originator;
    this.mathExpression = mathExpression;
    this.answer = answer;
  }
  public void show(){
    System.out.println("[Memento] " + mathExpression + " = "+answer);
  }

  @Override
  public void restore(){
    Calculator calculator = (Calculator) originator;
    calculator.setAnswer(answer);
    calculator.setMathExpression(mathExpression);
    System.out.print("\nRestore: ");
    calculator.show();
  }
}

class Caretaker{
  private List<Memento> history = new ArrayList<Memento>();

  public void addHistory(Memento memento){
    history.addLast(memento);
  }
  public void undo(){
    if(history.isEmpty()){
      System.out.println("No calculation history to undo.");
      return;
    }
    Memento memento = history.removeLast();
    memento.restore();
  }
  public void showHistory(){
    if(history.isEmpty()){
      System.out.println("No calculation history.");
      return;
    }
    System.out.println("\nCalculation History:");
    Iterator<Memento> memento = history.iterator();
    while (memento.hasNext()) {
      CalculationMemento calculationMemento = (CalculationMemento) memento.next();
      calculationMemento.show();
    } 
  }
}

public class MementoPatternDemo {
  public static void main(String[] args) {
    Calculator calculator = new Calculator("8 * 8 + 1",8*8+1);
    Caretaker caretaker = new Caretaker();
    caretaker.addHistory(calculator.save());

    calculator.setMathExpression("4^7");
    calculator.setAnswer(Math.pow(4, 7));
    caretaker.addHistory(calculator.save());

    calculator.setMathExpression("sqrt(64)");
    calculator.setAnswer(Math.sqrt(64));

    System.out.print("Current Calculation: ");
    calculator.show();

    caretaker.showHistory();

    caretaker.undo();
    caretaker.undo(); 
  }
}
