// See https://aka.ms/new-console-template for more information
using System;
using System.Collections.Generic;

abstract class Room
{
  private string type;
  private double width;
  private double length;
  private double height;

  public Room(string type, double w, double l, double h)
  {
    this.type = "";
    SetRoomType(type);
    SetWidth(w);
    SetLength(l);
    SetHeight(h);
  }
  public Room(Room r)
  {
    this.type = r.type;
    this.width = r.width;
    this.length = r.length;
    this.height = r.height;
  }
  public abstract Room Clone();
  public void SetRoomType(string id)
  {
    type = id;
  }
  public void SetWidth(double w)
  {
    if (w > 2)
      width = w;
    else
      width = 3;
  }
  public void SetLength(double l)
  {
    if (l > 2)
      length = l;
    else
      length = 3;
  }
  public void SetHeight(double h)
  {
    if (h > 2)
      height = h;
    else
      height = 3;
  }
  public double GetWidth()
  {
    return width;
  }
  public double GetLength()
  {
    return length;
  }
  public double GetHeight()
  {
    return height;
  }

  public virtual void Show()
  {
    Console.WriteLine("room-type = " + type + ", width = " + width + ", height = " + height + ", length = " + length);
  }
}

class BedRoom : Room
{
  private int numOfBed;
  private int numOfWaredrobe;
  private int numOfNightstand;

  public BedRoom(int bed, int waredrobe, int nightstand, string type, double width, double length, double height) : base(type, width, length, height)
  {
    SetNumOfBed(bed);
    SetNumOfWaredrobe(waredrobe);
    SetNumOfNightstand(nightstand);
  }
  public BedRoom(BedRoom r) : base(r)
  {

    this.numOfBed = r.numOfBed;
    this.numOfWaredrobe = r.numOfWaredrobe;
    this.numOfNightstand = r.numOfNightstand;
  }
  public override BedRoom Clone()
  {
    return new BedRoom(this);
  }
  public void SetNumOfBed(int bed)
  {
    if (bed >= 0)
      numOfBed = bed;
    else
      numOfBed = 1;
  }
  public void SetNumOfWaredrobe(int waredrobe)
  {
    if (waredrobe >= 0)
      numOfWaredrobe = waredrobe;
    else
      numOfWaredrobe = 1;
  }
  public void SetNumOfNightstand(int nightstand)
  {
    if (nightstand >= 0)
      numOfNightstand = nightstand;
    else
      numOfNightstand = 1;
  }

  public override void Show()
  {
    Console.WriteLine("---Bed Room:---");
    base.Show();
    Console.WriteLine("number of beds = " + numOfBed + ", number of waredrobes = " + numOfWaredrobe + ", number of nightstands = " + numOfNightstand);
  }
}

class LivingRoom : Room
{
  private int numOfSofa;
  private int numOfChair;
  private int numOfTable;
  public LivingRoom(int sofa, int chair, int table, string type, double width, double length, double height) : base(type, width, length, height)
  {
    SetNumOfSofa(sofa);
    SetNumOfChair(chair);
    SetNumOfTable(table);
  }
  public LivingRoom(LivingRoom r) : base(r)
  {

    this.numOfSofa = r.numOfSofa;
    this.numOfChair = r.numOfChair;
    this.numOfTable = r.numOfTable;
  }
  public override LivingRoom Clone()
  {
    return new LivingRoom(this);
  }
  public void SetNumOfSofa(int sofa)
  {
    if (sofa > 0)
      numOfSofa = sofa;
    else
      numOfSofa = 1;
  }
  public void SetNumOfChair(int chair)
  {
    if (chair > 1)
      numOfChair = chair;
    else
      numOfChair = 2;
  }
  public void SetNumOfTable(int table)
  {
    if (table > 0)
      numOfTable = table;
    else
      numOfTable = 1;
  }

  public override void Show()
  {
    Console.WriteLine("---Living Room:---");
    base.Show();
    Console.WriteLine("number of sofas = " + numOfSofa + ", number of chairs = " + numOfChair + ", number of tables = " + numOfTable);
  }
}

class StudyingRoom : Room
{
  private int numOfTable;
  private int numOfChair;
  private int numOfBookshelf;

  public StudyingRoom(StudyingRoom r) : base(r)
  {

    this.numOfTable = r.numOfTable;
    this.numOfChair = r.numOfChair;
    this.numOfBookshelf = r.numOfBookshelf;
  }
  public StudyingRoom(int table, int chair, int bookshelf, string type, double width, double length, double height) : base(type, width, length, height)
  {
    SetNumOfTable(table);
    SetNumOfChair(chair);
    SetNumOfBookshelf(bookshelf);
  }

  public override StudyingRoom Clone()
  {
    return new StudyingRoom(this);
  }
  public void SetNumOfTable(int table)
  {
    if (table > 0)
      numOfTable = table;
    else
      numOfTable = 1;
  }
  public void SetNumOfChair(int chair)
  {
    if (chair > 0)
      numOfChair = chair;
    else
      numOfChair = 1;
  }
  public void SetNumOfBookshelf(int bookshelf)
  {
    if (bookshelf > 0)
      numOfBookshelf = bookshelf;
    else
      numOfBookshelf = 1;
  }
  public override void Show()
  {
    Console.WriteLine("---Studying Room---: ");
    base.Show();
    Console.WriteLine("number of tables = " + numOfTable + ", number of chairs = " + numOfChair + ", number of bookshelves = " + numOfBookshelf);
  }
}

class RoomModel
{
  private BedRoom standardBedRoom = new(1, 1, 2, "standard", 4, 5, 3.5);
  private LivingRoom standardLivingRoom = new(2, 4, 1, "standard", 6, 6, 4);
  private StudyingRoom standardStudyingRoom = new(1, 2, 2, "standard", 4, 4, 3);

  public BedRoom GetStandardBedRoom()
  {
    return standardBedRoom.Clone();
  }
  public BedRoom GetBudgetBedRoom()
  {
    BedRoom budgetBedRoom = standardBedRoom.Clone();
    budgetBedRoom.SetRoomType("budget");
    budgetBedRoom.SetWidth(budgetBedRoom.GetWidth() - 1);
    budgetBedRoom.SetLength(budgetBedRoom.GetLength() - 1);
    return budgetBedRoom;
  }

  public LivingRoom GetStandardLivingRoom()
  {
    return standardLivingRoom.Clone();
  }
  public LivingRoom GetBudgetLivingRoom()
  {
    LivingRoom budgetLivingRoom = standardLivingRoom.Clone();
    budgetLivingRoom.SetRoomType("budget");
    budgetLivingRoom.SetHeight(budgetLivingRoom.GetHeight() - 1);
    budgetLivingRoom.SetWidth(budgetLivingRoom.GetWidth() - 2);
    return budgetLivingRoom;
  }
  public StudyingRoom GetStandardStudyingRoom()
  {
    return standardStudyingRoom.Clone();
  }
  public StudyingRoom GetBudgetStudyingRoom()
  {
    StudyingRoom budgetStudyingRoom = standardStudyingRoom.Clone();
    budgetStudyingRoom.SetRoomType("budget");
    budgetStudyingRoom.SetLength(budgetStudyingRoom.GetLength() - 1);
    budgetStudyingRoom.SetWidth(budgetStudyingRoom.GetWidth() - 0.5);
    return budgetStudyingRoom;
  }
  public void ResetStandardBedRoom(BedRoom newBedRoom)
  {
    standardBedRoom = newBedRoom;
  }
  public void ResetStandardLivigRoom(LivingRoom newLivingRoom)
  {
    standardLivingRoom = newLivingRoom;
  }
  public void ResetStandartStudyingRoom(StudyingRoom newStudyingRoom)
  {
    standardStudyingRoom = newStudyingRoom;
  }
}

class Program
{
  public static void Client(RoomModel rooms)
  {
    List<Room> house = new List<Room>();
    house.Add(rooms.GetStandardBedRoom());
    house.Add(rooms.GetStandardLivingRoom());
    house.Add(rooms.GetStandardStudyingRoom());
    house.Add(rooms.GetBudgetBedRoom());
    house.Add(rooms.GetBudgetLivingRoom());
    house.Add(rooms.GetBudgetStudyingRoom());

    foreach (Room copyRoom in house)
    {
      copyRoom.Show();
    }
  }
  static void Main(string[] args)
  {
    Console.WriteLine("----------------------------");
    Console.WriteLine("Room model1: ");
    RoomModel model1 = new();
    Client(model1);

    Console.WriteLine("----------------------------");
    Console.WriteLine("Room model2: ");
    RoomModel model2 = new();
    BedRoom newBedRoom = new(1, 2, 2, "standard", 5, 6, 5);
    model2.ResetStandardBedRoom(newBedRoom);
    LivingRoom newLivingRoom = new(2, 6, 1, "standard", 6, 7, 5);
    model2.ResetStandardLivigRoom(newLivingRoom);
    StudyingRoom newStudyingRoom = new(2, 3, 3, "standard", 6, 6, 3.5);
    model2.ResetStandartStudyingRoom(newStudyingRoom);
    Client(model2);

  }
}