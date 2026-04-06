
interface Farm{
  String getFarmDescription();
}

class Land implements Farm{
  private double width;  //in meters
  private double length; //in meters

  public Land(double width, double length){
    setWidth(width);
    setLength(length);
  }

  public void setWidth(double w){
    if(w < 50)
      w = 50;
    width = w;
  }
  public void setLength(double l){
    if(l < 50)
      l = 50;
    length = l;
  }
  public double getWidth(){return width;}
  public double getLength(){return length;}
  public double getLandSize(){return width*length;}
  public String getFarmDescription(){
    return "\nFarm size = " + getLandSize() + " spare meters(" + width + "x" + length +").\n";
  }
}

class FarmDecorator implements Farm{
  private Farm farm;

  public FarmDecorator(Farm f){
    farm = f;
  }
  public String getFarmDescription(){
    return farm.getFarmDescription();
  }
}

class Crop extends FarmDecorator{
  private String cropType;
  private double areaUsed; //in square meters

  public Crop(String cropType, double areaUsed,Farm farm){
    super(farm);
    this.cropType = cropType;
    setAreaUsed(areaUsed);
  }
  public void setAreaUsed(double area){
    if(area < 50)
      area = 50;
    areaUsed = area;
  }
  public String getCropType(){return cropType;}
  public double getAreaUsed(){return areaUsed;}
  public String getFarmDescription(){
    return super.getFarmDescription() + "  This farm plants " + cropType + " for " + areaUsed + " square meters.\n";
  }
}

class Stocklive extends FarmDecorator{
  private String animalName;
  private int animalCount;

  public Stocklive(String animalName, int animalCount, Farm farm){
    super(farm);
    this.animalName = animalName;
    setAnimalCount(animalCount);
  }
  public void setAnimalCount(int count){
    if(count < 5)
      count = 5;
    animalCount = count;
  }
  public String getAnimalName(){return animalName;}
  public int getAnimalCount(){return animalCount;}
  public String getFarmDescription(){
    return super.getFarmDescription() + "  This farm has " + animalCount + " " + animalName + "s.\n";
  }
}

class Pond extends FarmDecorator{
  private double pondSize;

  public Pond(double size, Farm farm){
    super(farm);
    setPondSize(size);
  }
  public void setPondSize(double size){
    if(size < 25)
      size = 25;
    pondSize = size;
  }
  public double getPondSize(){return pondSize;}
  public String getFarmDescription(){
    return super.getFarmDescription() + "  This farm has a " + pondSize + " square meters pond.\n";
  }
}

class FarmCottage extends FarmDecorator{
  private double length;
  private double width;
  private double height;
  private int floor;

  public FarmCottage(double l, double w, double h, int f, Farm farm){
    super(farm);
    setWidth(w);
    setHeight(h);
    setLength(l);
    setFloor(f);
  }
  public void setLength(double l){
    if(l < 5)
      l = 5;
    length = l;
  }
  public void setWidth(double w){
    if(w < 5)
      w =5;
    width = w;
  }
  public void setHeight(double h){
    if(h < 5)
      h =5;
    height = h;
  }
  public void setFloor(int f){
    if(f < 1)
      f = 1;
    floor = f;
  }
  public double getWidth(){return width;}
  public double getHeight(){return height;}
  public double getLength(){return length;}
  public int getFloor(){return floor;}
  public String getFarmDescription(){
    return super.getFarmDescription() + "  This farm has a cottage which width = " + width + " meters, height = " + height + " meters length = "+ length + " meters and has " + floor + " floor/floors.\n"; 
  }
}

public class DecoratorPatternDemo{
  public static void client(Farm f){
    System.out.println(f.getFarmDescription());
  }
  public static void main(String[] args) {
    Farm land = new Land(100, 150);
    client(land);

    Farm caretakerCottageLand = new FarmCottage(7,6,10,2,land);
    Farm durianCaretakerCottageLand = new Crop("durain", 800, caretakerCottageLand);
    Farm blackPepperDurianCaretakerCottageLand = new Crop("black pepper", 1000, durianCaretakerCottageLand);
    Farm rubberBlackPepperDurianCaretakerCottageLand = new Crop("rubber", 2000, blackPepperDurianCaretakerCottageLand);
    Farm sheepRubberBlackPepperDurianCaretakerCottageLand = new Stocklive("sheep", 50, rubberBlackPepperDurianCaretakerCottageLand);
    Farm cowSheepRubberBlackPepperDurianCaretakerCottageLand = new Stocklive("cow", 30, sheepRubberBlackPepperDurianCaretakerCottageLand);
    Farm pondCowSheepRubberBlackPepperDurianCaretakerCottageLand = new Pond(800, cowSheepRubberBlackPepperDurianCaretakerCottageLand);
    client(pondCowSheepRubberBlackPepperDurianCaretakerCottageLand);
  }
}