import java.util.List;
import java.util.ArrayList;

abstract class Publisher{
  protected List<Observer> listSubscriber = new ArrayList<Observer>();

  public void subscribe(Observer s){
    listSubscriber.add(s);
  }
  public void unsubscribe(Observer s){
    listSubscriber.remove(s);
  }
  abstract void notifySubscriber();
} 

class LibraryPublisher extends Publisher{
  private String topic;
  private String libraryMessage;

  public void setTopic(String topic){
    this.topic = topic;
  }
  public void setLibraryMessage(String message){
    libraryMessage = message;
    notifySubscriber();
  }
  public void setNewUpdate(String topic, String message){
    this.topic = topic;
    libraryMessage = message;
    notifySubscriber();
  }
  public void notifySubscriber(){
    for (Observer subscriber : listSubscriber) {
      subscriber.update(topic, libraryMessage);
    }
  }
}

class AcademicAffairsOfficePublisher extends Publisher{
  private String topic;
  private String academicAffairsMessage;

  public void setTopic(String topic){
    this.topic = topic;
  }
  public void AcedmicAffairsMessage(String message){
    academicAffairsMessage = message;
    notifySubscriber();
  }
  public void setNewUpdate(String topic, String message){
    this.topic = topic;
    academicAffairsMessage = message;
    notifySubscriber();
  }
  public void notifySubscriber(){
    for (Observer subscriber : listSubscriber) {
      subscriber.update(topic, academicAffairsMessage);
    }
  }
}

interface Observer{
  void update(String topic, String message);
}

class EmailSubscriber implements Observer{
  private String emailAddress;

  public EmailSubscriber(String emailAddress){
    this.emailAddress = emailAddress;
  }
  public void update(String topic, String message){
    System.out.println("To email " + emailAddress + ": ");
    
    System.out.println("   Topic: " + topic);
    System.out.println("      " + message);
  }
}

class SMSSubscriber implements Observer{
  private String phoneNumber;

  public SMSSubscriber(String phoneNumber){
    this.phoneNumber = phoneNumber;
  }
  public void update(String topic, String message){
    System.out.println("To phone number " + phoneNumber + ": ");
    
    System.out.println("   Topic: " + topic);
    System.out.println("      " + message);
  }
}

public class ObserverPatternDemo{
  public static void main(String[] args) {
    LibraryPublisher library = new LibraryPublisher();

    EmailSubscriber email1 = new EmailSubscriber("25001111@myschool.edu");
    library.subscribe(email1);
    EmailSubscriber email2 = new EmailSubscriber("24768999@myschool.edu");
    library.subscribe(email2);
    EmailSubscriber email3 = new EmailSubscriber("24760506@myschool.edu");
    library.subscribe(email3);
    SMSSubscriber phoneNo1 = new SMSSubscriber("099-092-1342");
    library.subscribe(phoneNo1);
    SMSSubscriber phoneNo2 = new SMSSubscriber("078-442-1669");
    library.subscribe(phoneNo2);

    System.out.println();
    library.setNewUpdate("Library Open House", "\"We’re excited to invite all students and staff to the University Library Open House from October 15th to October 18th.\"");

    library.unsubscribe(email3);
    library.unsubscribe(phoneNo2);

    System.out.println("");
    library.setLibraryMessage("\"Library Open House coming up tomorrow, October 15.\"");

    AcademicAffairsOfficePublisher academicAffairsOffice = new AcademicAffairsOfficePublisher();
    academicAffairsOffice.subscribe(phoneNo1);
    academicAffairsOffice.subscribe(phoneNo2);
    academicAffairsOffice.subscribe(email1);
    academicAffairsOffice.subscribe(email2);
    academicAffairsOffice.subscribe(email3);

    System.out.println();
    academicAffairsOffice.setNewUpdate("\"Upcoming Final Examination Schedule", "The Final Examinatoins for the current semester will begin on Octore 20th, 2025.\"");

    academicAffairsOffice.unsubscribe(email1);
    academicAffairsOffice.unsubscribe(email2);

    System.out.println();
    academicAffairsOffice.setTopic("Semester Exam Results Released.");
    academicAffairsOffice.AcedmicAffairsMessage("\"The Final Examination Results are now available on E-Service.\"");
  }
}