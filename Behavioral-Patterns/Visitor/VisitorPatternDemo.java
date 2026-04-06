import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

interface Flower{
  void accept(Visitor v);
}

class Lavender implements Flower{
  private String name;
  private String color;
  private String region;

  public Lavender(String name, String color, String region){
    this.name = name;
    this.color = color;
    this.region = region;
  }
  public String getName() {return name;}
  public String getColor() {return color;}
  public String getRegion() {return region;}
  public void accept(Visitor v){
    v.visitLavender(this);
  }
}

class Rose implements Flower{
  private String name;
  private String color;
  private String region;

  public Rose(String name, String color, String region){
    this.name = name;
    this.color = color;
    this.region = region;
  }
  public String getName() {return name;}
  public String getColor() {return color;}
  public String getRegion() {return region;}
  public void accept(Visitor v){
    v.visitRose(this);
  }
}
class Jasmine implements Flower{
  private String name;
  private String region;

  public Jasmine(String name, String region){
    this.name = name;
    this.region = region;
  }
  public String getName() {return name;}
  public String getRegion() {return region;}
  public void accept(Visitor v){
    v.visitJasmine(this);
  }
}

interface Visitor{
  void visitRose(Rose r);
  void visitLavender(Lavender l);
  void visitJasmine(Jasmine j);
}

class MakePerfumeVisitor implements Visitor{
  public void visitRose(Rose r){
    System.out.println("  Make perfume out of " + r.getColor() + " " + r.getName() + " from "+ r.getRegion()+ ".");
  }
  public void visitLavender(Lavender l){
    System.out.println("  Make perfume out of " + l.getColor() + " " + l.getName() + " from "+ l.getRegion()+ ".");
  }
  public void visitJasmine(Jasmine j){
    System.out.println("  Make perfume out of "+ j.getName() + " from "+ j.getRegion()+ ".");
  }
}

class MakeTeaVisitor implements Visitor{
  public void visitRose(Rose r){
    System.out.println("  Make tea out of dry " + r.getColor() + " " + r.getName() + " from "+ r.getRegion()+ ".");
  }
  public void visitLavender(Lavender l){
    System.out.println("  Make tea out of dry " + l.getColor() + " " + l.getName() + " from "+ l.getRegion()+ ".");
  }
  public void visitJasmine(Jasmine j){
    System.out.println("  Make tea out of dry "+ j.getName() + " from "+ j.getRegion()+ ".");
  }
}

class MakeBodyWashVisitor implements Visitor{
  public void visitRose(Rose r){
    System.out.println("  Make body wash out of " + r.getColor() + " " + r.getName() + " from "+ r.getRegion()+ ".");
  }
  public void visitLavender(Lavender l){
    System.out.println("  Make body wash out of " + l.getColor() + " " + l.getName() + " from "+ l.getRegion()+ ".");
  }
  public void visitJasmine(Jasmine j){
    System.out.println("  Make body wash out of "+ j.getName() + " from "+ j.getRegion()+ ".");
  }
}

class FlowerGarden{
  private List<Flower> flowers = new ArrayList<Flower>();

  public void addFlower(Flower flower){
    flowers.add(flower);
  }
  public void applyVisitor(Visitor v){
    Iterator<Flower> it = flowers.iterator();
    while (it.hasNext()) {
      it.next().accept(v);
    }
  }
}

public class VisitorPatternDemo{
  public static void main(String[] args) {
    FlowerGarden garden = new FlowerGarden();
    garden.addFlower(new Rose("Rose", "Red", "South Asia"));
    garden.addFlower(new Lavender("Lavender", "Purple", "France"));
    garden.addFlower(new Jasmine("Jasmine", "Southease Asia"));

    System.out.println("\nUsing MakePerfumeVisitor:");
    MakePerfumeVisitor perfumeVisitor = new MakePerfumeVisitor();
    garden.applyVisitor(perfumeVisitor);

    System.out.println("\nUsing MakeBodyWashVisitor:");
    MakeBodyWashVisitor bodyWashVisitor = new MakeBodyWashVisitor();
    garden.applyVisitor(bodyWashVisitor);


    garden.addFlower(new Lavender("Fernleaf Lavender", "Purple and Blue", "Europe"));
    System.out.println("\nUsing MakeTeaVisitor:");
    MakeTeaVisitor teaVisitor = new MakeTeaVisitor();
    garden.applyVisitor(teaVisitor);
  }
}