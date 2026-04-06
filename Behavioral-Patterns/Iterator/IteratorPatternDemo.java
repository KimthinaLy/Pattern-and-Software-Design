import java.util.List;
import java.util.ArrayList;

interface Citizen{
  int getAge();
  String getPhysicalCondition(); //pragnancy, disability, elder, normal
  void showCitizenInfo();
}


class NormalCitizen implements Citizen{
  private String name;
  private int age;
  private String gender;
  private String citizenId;
  private String physicalCondition;

  public NormalCitizen(String name, int age, String gender, String citizenId, String physicalCondition){
    this.name = name;
    setAge(age);
    this.gender = gender;
    this.citizenId = citizenId;
    this.physicalCondition = physicalCondition;
  }
  public void setAge(int age){
    if(age < 15)
      age = 15;
    this.age = age;
  }
  public String getName(){return name;}
  public int getAge(){return age;}
  public String getGender(){return gender;}
  public String getCitizenId(){return citizenId;}
  public String getPhysicalCondition() {return physicalCondition;}
  public void showCitizenInfo(){
    System.out.println("\nName: " + name + " Age: " + age + "  Gender: " + gender);
    System.out.println("Citizen Id: " + citizenId);
    System.out.println("Physical Condition: " + physicalCondition);
  }
}

class GovernmentOfficial extends NormalCitizen{
  private String officialId;
  private String ministry;

  public GovernmentOfficial(String name, int age, String gender, String citizenId, String physicalCondition, String officialId, String ministry){
    super(name, age, gender, citizenId,  physicalCondition);
    this.officialId = officialId;
    this.ministry = ministry;
  }

  public String getOfficialId(){return officialId;}
  public String getMinistry() {return ministry;}
  public void showCitizenInfo(){
    super.showCitizenInfo();
    System.out.println("[Government Official]Official Id: " + officialId);
    System.out.println("[Government Official]Works for: " + ministry);
  }
}

interface CitizenIterator{
  Citizen getNext();
  boolean hasMore();
}

class SpecialPhysicalConditionIterator implements CitizenIterator{
  int currentIndex;
  private List<Citizen> citizens = new ArrayList<>();

  public SpecialPhysicalConditionIterator(CitizenCollection citizenQueue){
    int queueSize = citizenQueue.getQueueSize();
    Citizen citizen;
    for(int i = 0; i < queueSize ; i++ ){
      citizen = citizenQueue.getCitizen(i);
      if(citizen.getPhysicalCondition().equals("Pregnancy") || citizen.getPhysicalCondition().equals("Disability") || citizen.getPhysicalCondition().equals("Elder")){
        citizens.add(citizen);
      }
    }
    currentIndex = 0;
  }
  public Citizen getNext(){
    return citizens.get(currentIndex++);
  }
  public boolean hasMore(){
    return currentIndex < citizens.size();
  }
} 

class AgeDescendingOrderIterator implements CitizenIterator{
  int currentIndex;
  private List<Citizen> citizens;

  public AgeDescendingOrderIterator(CitizenCollection citizenQueue){
    citizens = citizenQueue.getCitizenQueue();
    int queueSize = citizenQueue.getQueueSize();
    for (int k = 1; k < queueSize; k++) {
      Citizen temp = citizens.get(k);
      int cur = k - 1;
      while ((cur > -1) && (temp.getAge() > citizens.get(cur).getAge()) ){
        citizens.set(cur+1,citizens.get(cur));
        cur--;
      }
      citizens.set(cur+1, temp);
    }
    currentIndex = 0;
  }
  public Citizen getNext(){
    return citizens.get(currentIndex++);
  }
  public boolean hasMore(){
    return currentIndex < citizens.size();
  }
}

class FIFOIterator implements CitizenIterator{
  int currentIndex;
  private List<Citizen> citizens;

  public FIFOIterator(CitizenCollection citizenQueue){
    citizens = citizenQueue.getCitizenQueue();
    currentIndex = 0;
  }
  public Citizen getNext(){
    return citizens.get(currentIndex++);
  }
  public boolean hasMore(){
    return currentIndex < citizens.size();
  }
}

interface CitizenCollection{
  CitizenIterator CreateSpecialPhysicalConditionIterator();
  CitizenIterator CreateAgeDescendingOrderIterator();
  CitizenIterator CreateFIFOIterator();
  int getQueueSize();
  Citizen getCitizen(int index);
  List<Citizen> getCitizenQueue();
}

class NormalCitizenQueue implements CitizenCollection{
  private List<NormalCitizen> normalCitizens = new ArrayList<>();

  public void addCitizen(NormalCitizen c){
    normalCitizens.add(c);
  }
  public CitizenIterator CreateSpecialPhysicalConditionIterator(){
    return new SpecialPhysicalConditionIterator(this);
  }
  public CitizenIterator CreateAgeDescendingOrderIterator(){
    return new AgeDescendingOrderIterator(this);
  }
  public CitizenIterator CreateFIFOIterator(){
    return new FIFOIterator(this);
  }
  public int getQueueSize(){return normalCitizens.size();}
  public Citizen getCitizen(int index){return normalCitizens.get(index);}
  public List<Citizen> getCitizenQueue(){return new ArrayList<>(normalCitizens);}
}

class GovernmentOfficialQueue implements CitizenCollection{
  private List<GovernmentOfficial> governmentOfficials = new ArrayList<>();

  public void addCitizen(GovernmentOfficial c){
    governmentOfficials.add(c);
  }
  public CitizenIterator CreateSpecialPhysicalConditionIterator(){
    return new SpecialPhysicalConditionIterator(this);
  }
  public CitizenIterator CreateAgeDescendingOrderIterator(){
    return new AgeDescendingOrderIterator(this);
  }
  public CitizenIterator CreateFIFOIterator(){
    return new FIFOIterator(this);
  }
  public int getQueueSize(){return governmentOfficials.size();}
  public Citizen getCitizen(int index){return governmentOfficials.get(index);}
  public List<Citizen> getCitizenQueue(){return new ArrayList<>(governmentOfficials);}
}

public class IteratorPatternDemo{
  public static void showCitizen(CitizenIterator it){
    while(it.hasMore()){
      it.getNext().showCitizenInfo();
    }
  }
  public static void main(String[] args) {
    NormalCitizenQueue normalCitizenQueue = new NormalCitizenQueue();
    normalCitizenQueue.addCitizen(new NormalCitizen("Jaa", 30, "Female", "0123456789", "Pregnancy")); 
    normalCitizenQueue.addCitizen(new NormalCitizen("Milky", 20, "Male", "0123456788", "Disability")); 
    normalCitizenQueue.addCitizen(new NormalCitizen("Ben", 70, "Male", "0003456789", "Elder")); 
    normalCitizenQueue.addCitizen(new NormalCitizen("Jorden", 18, "Male", "022456789", "Normal")); 
    normalCitizenQueue.addCitizen(new NormalCitizen("Haya", 48, "Male", "022456789", "Normal")); 
    normalCitizenQueue.addCitizen(new NormalCitizen("Jessica", 28, "Female", "022110722", "Normal")); 
    normalCitizenQueue.addCitizen(new NormalCitizen("Kita", 27, "Female", "099110745", "Pregnancy")); 
    normalCitizenQueue.addCitizen(new NormalCitizen("Zasa", 67, "Female", "001280766", "Elder")); 
    
    System.out.println("\n--------Special Physical Condition Iterator--------");
    showCitizen(normalCitizenQueue.CreateSpecialPhysicalConditionIterator());
    
    System.out.println("\n--------First In First Out Iterator--------");
    showCitizen(normalCitizenQueue.CreateFIFOIterator());

    GovernmentOfficialQueue governmentOfficialQueue = new GovernmentOfficialQueue();
    governmentOfficialQueue.addCitizen(new GovernmentOfficial("Mony", 45, "Male", "1688890039", "Normal","00001111", "Ministry of Education"));
    governmentOfficialQueue.addCitizen(new GovernmentOfficial("SokSan", 29, "Female", "8901267880", "Pregnancy","00001112", "Ministry of Telecommunication"));
    governmentOfficialQueue.addCitizen(new GovernmentOfficial("Vanna", 50, "Male", "0020034445", "Disability", "00001113", "Ministry of Education"));
    governmentOfficialQueue.addCitizen(new GovernmentOfficial("Alexa", 32, "Female", "5670000114", "Pregnancy","00001111", "Ministry of Education"));
    governmentOfficialQueue.addCitizen(new GovernmentOfficial("Kayla", 29, "Male", "2299000456", "Normal","00001115", "Ministry of Education"));
    System.out.println("\n--------Age Descending Order Iterator--------");
    showCitizen(governmentOfficialQueue.CreateAgeDescendingOrderIterator());
  }
}