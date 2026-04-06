
class DentalClinicColleague{
  protected String colleagueName;
  protected Mediator mediator;

  public void setClinicMediator (Mediator mediator){
    this.mediator = mediator;
  }
  public String getColleagueName(){return colleagueName;}
}

class Receptionist extends DentalClinicColleague{
  private String receptionistName;
  private String gender;

  public Receptionist(String name, String gender){
    colleagueName = "receptionist";
    this.receptionistName = name;
    this.gender = gender;
  }
  public String getReceptionistName(){return receptionistName;}
  public String getGender(){return gender;}
  public void greetPatient(){
    System.out.println("[Receptionist] Receptionist welcomes patient.");
  }
  public void getPatientInfo(){
    System.out.println("[Receptionist] Receptionist gets patient's information.");
    mediator.notifyMediator(this, "get patient info");
  }
  public void chargeTreatmentFee(){
    System.out.println("[Receptionist] Receptionist charge treatment fee from patient.");
  }
  public void informPatientAboutNextAppointment(){
    System.out.println("[Receptionist] Receptionist informs patient about thier next appointment.");
  }
}

class Patient extends DentalClinicColleague{
  private String patientName;
  private String patientGender;
  private int patientAge;
  private String phoneNo;

  public Patient(String name, String gender, int age, String phoneNo){
    colleagueName = "patient";
    this.patientName = name;
    this.patientGender = gender;
    this.patientAge = age;
    this.phoneNo = phoneNo;
  }
  public String getPatientName(){return patientName;}
  public String getPatientGender(){return patientGender;}
  public int getPatientAge(){return patientAge;}
  public String getPhoneNo(){return phoneNo;}
  public void visitClinic(){
    System.out.println("[Patient] Patient  " + patientName + " visits dental clinic for treatment.");
    mediator.notifyMediator(this, "visit clinic");
  }
  public void receiveTreatment(){
    System.out.println("[Patient] Patient receives treatment from dentist.");
  }
  public void payTreatmentFee(){
    System.out.println("[Patient] Patient pays treatment fee." );
  }
  public void fillInPersonalInfo(){
    System.out.println("[Patien] Patient fills in personal information.");
  }
}

class DentalAssistant extends DentalClinicColleague{
  private String assistantName;
  private String assistantGender;

  public DentalAssistant(String name, String gender){
    colleagueName = "assistant";
    assistantName = name;
    assistantGender = gender;
  }
  public String getAssistantName(){return assistantName;}
  public String getAssistantGender(){return assistantGender;}
  public void preparePatientForTreatment(){
    System.out.println("[DentalAssistant] Dental assistant prepares patient for treatment.");
    mediator.notifyMediator(this, "prepare patient");
  }
  public void sanitizeEquipment(){
    System.out.println("[DentalAssistant] Dental assistant sanitizes equipments.");
  }
  public void assistDentist(){
    System.out.println("[DentalAssistant] Dental assistant assists Dentist when treating patient.");
  }
  public void manageSchdualing(){
    System.out.println("[DentalAssistant] Dental assistant manages schedualing for the next appointment.");
    mediator.notifyMediator(this, "schedualing");
  }
}

class Dentist extends DentalClinicColleague{
  private String dentistName;
  private String dentistGender;

  public Dentist(String name, String gender){
    colleagueName = "dentist";
    this.dentistName = name;
    this.dentistGender = gender;
  }
  public String getDentistName(){return dentistName;}
  public String getDentistGender(){return dentistGender;}
  public void prepareForTreatingPatient(){
    System.out.println("[Dentist] Dentist gets ready to treat patient.");
  }
  public void treatPatient(){
    System.out.println("[Dentist] Dentist treats patient.");
    mediator.notifyMediator(this, "treat patient");
  }
  public void billing(){
    System.out.println("[Dentist] Dentist issues a receipt after treating patient.");
    mediator.notifyMediator(this, "billing");
  }
  public void confirmSchedual(){
    System.out.println("[Dentist] Dentist issues confirm schedualing for the next appointment.");
  }
}

interface Mediator{
  void notifyMediator(DentalClinicColleague sender, String msg);
}

class ClinicMediator implements Mediator{
  private Patient patient;
  private DentalAssistant dentalAssistant;
  private Dentist dentist;
  private Receptionist receptionist;

  public ClinicMediator(Patient patient, DentalAssistant dentalAssistant, Dentist dentist,Receptionist receptionist){
    this.patient = patient;
    this.dentalAssistant = dentalAssistant;
    this.dentist = dentist;
    this.receptionist = receptionist;
    receptionist.setClinicMediator(this);
    patient.setClinicMediator(this);
    dentalAssistant.setClinicMediator(this);
    dentist.setClinicMediator(this);
  }
  public void notifyMediator(DentalClinicColleague sender, String msg){
    if((sender.getColleagueName()) == "patient" && (msg =="visit clinic")){
      System.out.print("(Mediator called) ");
      receptionist.greetPatient();
      System.out.print("(Mediator called) ");
      dentalAssistant.sanitizeEquipment();
    }

    if((sender.getColleagueName() == "receptionist") && (msg == "get patient info")){
      System.out.print("(Mediator called) ");
      patient.fillInPersonalInfo();
      System.out.print("(Mediator called) ");
      dentalAssistant.preparePatientForTreatment();
      System.out.print("(Mediator called) ");
      dentist.prepareForTreatingPatient();
    }

    if((sender.getColleagueName().equals("dentist")) && (msg.equals("treat patient"))){
      System.out.print("(Mediator called) ");
      dentalAssistant.assistDentist();
      System.out.print("(Mediator called) ");
      patient.receiveTreatment();
    }

    if((sender.getColleagueName().equals("dentist")) && (msg.equals("billing"))){
      System.out.print("(Mediator called) ");
      receptionist.chargeTreatmentFee();
      System.out.print("(Mediator called) "); 
      patient.payTreatmentFee();
    }

    if((sender.getColleagueName() == "assistant") && (msg == "schedualing")){
      System.out.print("(Mediator called) ");
      dentist.confirmSchedual();
      System.out.print("(Mediator called) ");
      receptionist.informPatientAboutNextAppointment();
    }
  }
}

public class MediatorPatternDemo {
  public static void main(String[] args) {
    Patient patient = new Patient("Rian", "male", 0, "099-899-0045");
    Receptionist receptionist = new Receptionist("Yaya", "Female");
    DentalAssistant assistant = new DentalAssistant("Yanin","Female");
    Dentist dentist = new Dentist("Brian", "Male");
    ClinicMediator mediator = new ClinicMediator(patient, assistant, dentist, receptionist);

    System.out.println("\n------Patient visits clinic------");
    patient.visitClinic();

    System.out.println("\n------Receptionist get patient's personal information------");
    receptionist.getPatientInfo();
    
    System.out.println("\n------Dentist treat patient------");
    dentist.treatPatient();

    System.out.println("\n------Doctor issues a bill------");
    dentist.billing();

    System.out.println("\n------Dental Assistant manage schedualing for the next appointment------");
    assistant.manageSchdualing();


  }
}
