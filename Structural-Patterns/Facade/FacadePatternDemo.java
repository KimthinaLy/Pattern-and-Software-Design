import java.util.List;
import java.util.ArrayList;



class WorkshopOrganizerMemeber{
  private String name;
  private String role;

  public WorkshopOrganizerMemeber(String name, String role){
    this.name = name;
    this.role = role;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setRole(String role){
    this.role = role;
  }
  public String getName(){return name;}
  public String getRole(){return role;}
}

class Location{
  private String address;
  private String floor;
  private String room;

  public Location(String address, String floor, String room){
    this.address = address;
    this.floor = floor;
    this.room = room;
  }
  public void setOnsiteLocatoin(String address, String floor, String room){
    this.address = address;
    this.floor = floor;
    this.room = room;
  }
  public String getAddress(){return address;}
  public String getFloor(){return floor;}
  public String getRoom(){return room;}
}

class PublicRelationTeam{
  private List<WorkshopOrganizerMemeber> members;
  
  public PublicRelationTeam(List<WorkshopOrganizerMemeber> listMemebers){
    members = listMemebers;
  }
  public void showTeamMembers(){
    System.out.println("Public Relation Officers are:");
    for (WorkshopOrganizerMemeber member : members) {
      System.out.println("  "+member.getName() + ": responsible for " + member.getRole());
    }
  }
  public void announceWorkshop(String topic){
    System.out.println("Public Relation Officers: post public announcement about " + topic + " workshop.");
  }
  public void communicateWithSponsor(){
    System.out.println("Public Relation Officers: communicate with sponsors.");
  } 
  public void handleParticipantRequeries(){
    System.out.println("Public Relation Officers: handle participant requeries.");
  }
}

class MediaTeam{
  private List<WorkshopOrganizerMemeber> members;

  public MediaTeam(List<WorkshopOrganizerMemeber> listMembers){
    members = listMembers;
  }
  public void showTeamMembers(){
    System.out.println("Media Officers are:");
    for (WorkshopOrganizerMemeber member : members) {
      System.out.println("  "+member.getName() + ": responsible for " + member.getRole());
    }
  }
  public void promoteWorkshop(){
    System.out.println("Media Officers: design promotional materials and invite media or press.");
  }
  public void recordeEventVideo(){
    System.out.println("Media Officers: record event video.");
  }
  public void takeEventPhotographs(){
    System.out.println("Media Officers: take event photographs.");
  }
}

class LogisticTeam{
  private List<WorkshopOrganizerMemeber> members;

  public LogisticTeam(List<WorkshopOrganizerMemeber> listMembers){
    members = listMembers;
  }
  public void showTeamMembers(){
    System.out.println("Logistic Officers are:");
    for (WorkshopOrganizerMemeber member : members) {
      System.out.println("  "+member.getName() + ": responsible for " + member.getRole());
    }
  }
  public void preprareVenue(){
    System.out.println("Logistic Officers: prepare venue for the workshop.");
  }
  public void arrangeEquipment(){
    System.out.println("Logistic Officers: arrange equipments for the workshop.");
  }
  public void provideRefreshment(){
    System.out.println("Logistic Officers: provide refreshment for participants and geust speakers.");
  }
}

class MCTeam{
  private List<WorkshopOrganizerMemeber> members;

  public MCTeam(List<WorkshopOrganizerMemeber> listMembers){
    members = listMembers;
  }
  public void showTeamMembers(){
    System.out.println("MC team are:");
    for (WorkshopOrganizerMemeber member : members) {
      System.out.println("  "+member.getName() + ": responsible for " + member.getRole());
    }
  } 
  public void prepareAgenda(){
    System.out.println("MC team: prepare agenda and shedule for the event.");
  }
  public void hostEvent(){
    System.out.println("MC team: host the event(welcome speech and transiton between event).");
  }
  public void manageQandA(){
    System.out.println("MC team: manage Q&A session.");
  }

  public void closeEvent(){
    System.out.println("MC team: close the event.");
  }
}

class Sponsor{
  private String sponsorName;
  private String sponsorItem;

  public Sponsor(String name, String sponsorItem){
    sponsorName = name;
    this.sponsorItem = sponsorItem;
  }
  public String getSponsorName(){return "Sponsor is "+sponsorName;}
  public String getSponsorItem(){return "Sponsor: " + sponsorItem;}
}

class GeustSpeakers{
  private List<WorkshopOrganizerMemeber> members;

  public GeustSpeakers(List<WorkshopOrganizerMemeber> listMembers){
    members = listMembers;
  }
  public void showGeustSpeakers(){
    System.out.println("Geust Speakers are:");
    for (WorkshopOrganizerMemeber member : members) {
      System.out.println("  "+member.getName() + ": responsible for " + member.getRole());
    }
  }
  public void preparePresentation(){
    System.out.println("Geust Speakers: prepare for presentation.");
  }
  public void presentTopic(String topic){
    System.out.println("Geust Speakers: present about " + topic);
  }
  public void answerQueston(){
    System.out.println("Geust Speakers: answer all question related to the workshop's topic.");
  } 
}

class WorkShopFacade{
  private Location locatoin;
  private PublicRelationTeam publicRelationTeam;
  private MediaTeam mediaTeam;
  private LogisticTeam logisticTeam;
  private MCTeam mc;
  private Sponsor sponsor;
  private GeustSpeakers geustSpeakers;

  WorkShopFacade(Location l, PublicRelationTeam pr,MediaTeam media,LogisticTeam logistic,MCTeam mc, Sponsor s, GeustSpeakers gs  ){
    locatoin = l;
    publicRelationTeam = pr;
    mediaTeam = media;
    logisticTeam = logistic;
    this.mc = mc;
    sponsor = s;
    geustSpeakers = gs;
  }

  public void hostAWorkshop(String topic){
    System.out.println("Host a workshop about \"" + topic + "\"");

      System.out.println("Location: " + locatoin.getAddress() +", floor " + locatoin.getFloor() + ", room: " + locatoin.getRoom() + ".");
 
    //sponsor
    System.out.println(sponsor.getSponsorName() + ", " + sponsor.getSponsorItem()+ ".");

    //guest speakers
    geustSpeakers.preparePresentation();

    //public relatoin team
    publicRelationTeam.announceWorkshop(topic);
    publicRelationTeam.communicateWithSponsor();
    publicRelationTeam.handleParticipantRequeries();

    //media team
    mediaTeam.promoteWorkshop();

    mediaTeam.recordeEventVideo();
    mediaTeam.takeEventPhotographs();

    //logistic team
    logisticTeam.preprareVenue();
    logisticTeam.arrangeEquipment();
    logisticTeam.provideRefreshment();

    //MC and guest speakers
    mc.prepareAgenda();
    mc.hostEvent();
    geustSpeakers.presentTopic(topic);
    mc.manageQandA();
    geustSpeakers.answerQueston();
    mc.closeEvent();

    System.out.println("**Event successfully hosted**");
  }
}

public class FacadePatternDemo{
  public static void main(String[] args) {
    List<WorkshopOrganizerMemeber> publicRelationMembers = new ArrayList<WorkshopOrganizerMemeber>();
    publicRelationMembers.add(new WorkshopOrganizerMemeber("Jasine", "posting public announcement about workshop"));
    publicRelationMembers.add(new WorkshopOrganizerMemeber("Sokha", "finding sponsor"));
    publicRelationMembers.add(new WorkshopOrganizerMemeber("Dee", "handle participant queries"));

    List<WorkshopOrganizerMemeber> mediaMembers = new ArrayList<WorkshopOrganizerMemeber>();
    mediaMembers.add(new WorkshopOrganizerMemeber("Kai", "recording event video and photo"));
    mediaMembers.add(new WorkshopOrganizerMemeber("Ton", "promoting workshop"));

    List<WorkshopOrganizerMemeber> logisticMemebers = new ArrayList<WorkshopOrganizerMemeber>();
    logisticMemebers.add(new WorkshopOrganizerMemeber("Mot", "preparing venue and eqipments"));
    logisticMemebers.add(new WorkshopOrganizerMemeber("Bou", "providing refreshment"));

    List<WorkshopOrganizerMemeber> mcMemebers = new ArrayList<WorkshopOrganizerMemeber>();
    mcMemebers.add(new WorkshopOrganizerMemeber("Mek", "preparing agenda"));
    mcMemebers.add(new WorkshopOrganizerMemeber("May", "hosting event"));

    List<WorkshopOrganizerMemeber> guestSpeakerMembers = new ArrayList<WorkshopOrganizerMemeber>();
    guestSpeakerMembers.add(new WorkshopOrganizerMemeber("Faye", "presenting topic and q&a"));
    guestSpeakerMembers.add(new WorkshopOrganizerMemeber("Mak", "presenting topic and q&a"));

    //---------------------------
    System.out.println("\n------Workshop1------");
    Location location = new Location("CJCC Building, Institute of Technology, New York", "2nd floor", "205");
    PublicRelationTeam publicRelationTeam = new PublicRelationTeam(publicRelationMembers);
    MediaTeam mediaTeam = new MediaTeam(mediaMembers);
    LogisticTeam logisticTeam = new LogisticTeam(logisticMemebers);
    MCTeam mcTeam = new MCTeam(mcMemebers);
    Sponsor sponsor = new Sponsor("Samsuang", "fund, gifts");
    GeustSpeakers geustSpeakers = new GeustSpeakers(guestSpeakerMembers);

    WorkShopFacade facade = new WorkShopFacade(location, publicRelationTeam, mediaTeam, logisticTeam, mcTeam, sponsor, geustSpeakers);

    facade.hostAWorkshop("Cereer in Tech:How to Build Your Porfolio");

    System.out.println("\n------Workshop2------");
    facade.hostAWorkshop("AI Tools for Productivity");

  }
}