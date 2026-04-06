import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class RoomRental{
  private String renterName;
  private String renterTel;
  private String roomNo;
  private double roomCost;
  private double waterCost;
  private double electricityCost;
  private DormitoryFlyweight dormitory;

  public RoomRental(String renterName, String renterTel, String roomNo, double roomCost, double waterCost, double electricityCost, String dormName, String address, String campus,DormitoryFactory factory){
    this.renterName = renterName;
    this.renterTel = renterTel;
    this.roomNo = roomNo;
    this.roomCost = roomCost;
    this.waterCost = waterCost;
    this.electricityCost = electricityCost;
    dormitory = factory.getDormitoryFlyweight(dormName, address, campus);
  }
  public void printRentalReceipt(){
    dormitory.printRentalReceipt(renterName, renterTel, roomNo, roomCost, waterCost, electricityCost);
  }
}

class DormitoryFlyweight {
  private String name;
  private String address;
  private String campus;

  public DormitoryFlyweight(String name, String address, String campus) {
    this.name = name;
    this.address = address;
    this.campus = campus;
  }

  public String getName() {return name;}
  public String getAddress(){return address;}
  public String getCampus(){return campus;}

  public void printRentalReceipt(String renterName, String renterTel,String roomNo, double roomCost, double waterCost, double electricityCost){

    System.out.println(name);
    System.out.println("Campus: "+ campus);
    System.out.println(address);
    System.out.println("Room No." + roomNo);
    System.out.println("              Rentel Receipt");
    System.out.println("Renter Name : " + renterName + "    Tel." + renterTel);
    System.out.println("-------------------------------------------");
    System.out.println("Room Rental: " + roomCost);
    System.out.println("Water: " + waterCost);
    System.out.println("Electricity: " + electricityCost);
    System.out.println("-------------------------------------------");
    System.out.println("Total: " + (roomCost + waterCost + electricityCost));

    // Get the current date
    LocalDate today = LocalDate.now();

    // Print the date in a custom format (e.g., dd/MM/yyyy)
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = today.format(formatter);
    System.out.println("Date issued: " + formattedDate);
  }
}

class DormitoryFactory {
  private List<DormitoryFlyweight> dormitoryList = new ArrayList<DormitoryFlyweight>();

  public DormitoryFactory() {
    dormitoryList.add(new DormitoryFlyweight("Lady House", "67, Soi R, Road Ramkhamhaeng45/12, HauMark, BangKapi, Bankok, 10240", "Ramkhanhaeng"));
    dormitoryList.add(new DormitoryFlyweight("Lady House", "101/3, Soi Phetchaburi, Phaya Thai, Ratchathewi, BangKapi, Bankok, 10400", "Pratunam"));
    dormitoryList.add(new DormitoryFlyweight("Lady House", "84,24, Soi Saen Saep, Min Buri, BangKapi, Bankok, 10510", "Minburi"));
  }

  public DormitoryFlyweight getDormitoryFlyweight(String name, String address, String campus) {
    DormitoryFlyweight d = null;
    for (DormitoryFlyweight dormitory : dormitoryList) {
      if (dormitory.getName().equals(name) && dormitory.getAddress().equals(address) && dormitory.getCampus().equals(campus)) {
        d = dormitory;
        System.out.println("\n\n[Factory] return existing DormitoryFlyweight");
        break;
      }
    }
    if(d == null){
      d = new DormitoryFlyweight(name, address,campus);
      System.out.println("\n[Factory] Create new DormitoryFlyweight*****");
      dormitoryList.add(d);
    }
    return d;
  }
}

public class FlyweightPatternDemo {
  public static void main(String[] args) {
    DormitoryFactory factory = new DormitoryFactory();
    RoomRental renter1 = new RoomRental("Sabo Kun", "088-067-1112", "102", 4000, 20, 1200, "Lady House", "67, Soi R, Road Ramkhamhaeng45/12, HauMark, BangKapi, Bankok, 10240", "Ramkhanhaeng", factory);
    renter1.printRentalReceipt();

    RoomRental renter2 = new RoomRental("Ashly", "078-224-9906", "303", 6100, 40, 1100, "Lady House", "101/3, Soi Phetchaburi, Phaya Thai, Ratchathewi, BangKapi, Bankok, 10400", "Pratunam", factory);
    renter2.printRentalReceipt();

    RoomRental renter3 = new RoomRental("Nelly", "088-662-1779", "304", 5100, 20, 650, "Lady House", "101/3, Soi Phetchaburi, Phaya Thai, Ratchathewi, BangKapi, Bankok, 10400", "Pratunam", factory);
    renter3.printRentalReceipt(); 

    RoomRental renter4 = new RoomRental("Jonhny", "099-885-2154", "305", 5400, 20, 950, "Lady House", "101/3, Soi Phetchaburi, Phaya Thai, Ratchathewi, BangKapi, Bankok, 10400", "Pratunam", factory);
    renter4.printRentalReceipt();

    RoomRental renter5 = new RoomRental("Micky", "066-890-0045", "210", 3400, 20, 450, "Lady House", "84,24, Soi Saen Saep, Min Buri, BangKapi, Bankok, 10510", "Minburi", factory);
    renter5.printRentalReceipt();

    RoomRental renter6 = new RoomRental("Hana", "078-312-9887", "416", 3100, 20, 350, "Lady House", "84,24, Soi Saen Saep, Min Buri, BangKapi, Bankok, 10510", "Minburi", factory);
    renter6.printRentalReceipt();

    RoomRental renter7 = new RoomRental("Saisa", "098-986-1234", "101", 2800, 20, 350, "Lady House", "67, Soi R, Road Ramkhamhaeng45/12, HauMark, BangKapi, Bankok, 10240", "Ramkhanhaeng", factory);
    renter7.printRentalReceipt();

    RoomRental renter8 = new RoomRental("Rita", "098-123-8867", "102", 2800, 20, 450, "Lady House", "67, Soi R, Road Ramkhamhaeng45/12, HauMark, BangKapi, Bankok, 10240", "Ramkhanhaeng", factory);
    renter8.printRentalReceipt();
  }
}