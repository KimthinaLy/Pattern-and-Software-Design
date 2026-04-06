abstract class LaundryProcess{
  public void sort(){}
  abstract void wash();
  public void rinse(){
    System.out.println("  Rinse thoroughly.");
  }
  public void dry(){
    System.out.println("  Hang under sunlight to dry.");
  }
  public void fold(){}
  public void iron(){}
  public final void doLaundry(){
    sort();
    wash();
    rinse();
    dry();
    fold();
    iron();
  }
}

class ClothesLaundry extends LaundryProcess{
  private boolean whiteClothes;
  private boolean coloredClothes;

  public ClothesLaundry(boolean whiteClothes, boolean coloredClothes){
    this.whiteClothes = whiteClothes;
    this.coloredClothes = coloredClothes;
  }
  public void setWhiteClothes(boolean whiteClothes){
    this.whiteClothes = whiteClothes;
  }
  public void setColoredClothes(boolean colorClothes){
    this.coloredClothes = colorClothes;
  }
  public void sort(){
    if(whiteClothes && coloredClothes){
      System.out.println("  Separate white clothes from colored ones before washing.");
    }
  }
  public void wash(){
    if(whiteClothes){
      System.out.println("  Wash: soak for 15 minutes, washing with normal water and special detetergent for white clothes.");
    }
    if(coloredClothes){
      System.out.println("  Wash: soak for 10 minutes, washing with normal water and normal detetergent for colored clothes.");
    }
  }
  public void fold(){
    System.out.println("  Fold the clothes neatly after drying except uniforms.");
  }
  public void iron(){
    System.out.println("  Iron all uniforms.");
  }
}

class BedClothesLaundry extends LaundryProcess{
  public void wash(){
    System.out.println("  Wash: soak for 30 minutes, washing with warm water and extra detergent.");
  }
  public void dry(){
    System.out.println("  Using drying machine to dry bedclothes.");
  }
}

public class TemplatePatternDemo{
  public static void client(LaundryProcess laundry){
    laundry.doLaundry();
  }
  public static void main(String[] args) {
    ClothesLaundry clothes = new ClothesLaundry(true,true);
    System.out.println("\nClothes Laundry Process:");
    client(clothes);

    clothes.setWhiteClothes(false);
    System.out.println("\nColored Clothes Laundry Process:");
    client(clothes);

    BedClothesLaundry bedding = new BedClothesLaundry();
    System.out.println("\nBedclothes Laundry Process:");
    client(bedding);
  }
}