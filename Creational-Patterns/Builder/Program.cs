using System;
using System.ComponentModel;

class HouseholdRefrigerator
{
  private int door;
  private int drawer;
  private int shelf;
  private bool camera;
  private bool sensor;
  private bool touchscreen;

  public HouseholdRefrigerator()
  {
    door = 1;
    drawer = 0;
    shelf = 1;
    camera = false;
    sensor = false;
    touchscreen = false;
  }
  public void SetDoor(int n)
  {
    if (n > 0)
      door = n;
    else
      door = 1;
  }
  public void SetDrawer(int d)
  {
    if (d >= 0)
      drawer = d;
    else
      drawer = 0;
  }
  public void SetShelf(int s)
  {
    if (s >= 0)
      shelf = s;
    else
      shelf = 0;
  }
  public void ConstructTheCabinet()
  {
    Console.WriteLine("The cabinet structure of household fridge is constructed.");
  }
  public void InstallCoolingSystem()
  {
    Console.WriteLine("The coolingSystem of household fridge is installed.");
  }
  public void ConnectRefrigerationCircuit()
  {
    Console.WriteLine("The refrigeration circuit of household fridge is connected.");
  }
  public void FillRefrigerantGas()
  {
    Console.WriteLine("The refrigerant gas is filled.");
  }
  public void InstallElectricalAndControlingSystem()
  {
    Console.WriteLine("The electrical and control systems of household fridge are installed.");
  }
  public void AssembleInternalParts(int door, int shelf, int drawer)
  {
    SetDoor(door);
    SetShelf(shelf);
    SetDrawer(drawer);
    Console.WriteLine("The internal parts of household fridge is assembled.");
  }
  public void InstallSmartSensors(bool smartSensors)
  {
    sensor = smartSensors;
    Console.WriteLine("The smart sensors are installed.");
  }
  public void InstallCameras(bool c)
  {
    camera = c;
    Console.WriteLine("The cameras are installed.");
  }
  public void InstallTouchScreenControlPanel(bool t)
  {
    touchscreen = t;
    Console.WriteLine("The touchscreen control panel is installed.");
  }
  public void Show()
  {
    Console.Write("Create:: household frige consists of ");
    Console.Write(door + (door > 1 ? " doors" : " door"));
    if (shelf > 0)
      Console.Write(", " + shelf + (shelf > 1 ? " shelves" : " shelf"));
    if (drawer > 0)
      Console.Write(", " + drawer + (drawer > 1 ? " drawers" : " drawer"));
    if (sensor)
      Console.Write(", smart sensors");
    if (camera)
      Console.Write(", cameras");
    if (touchscreen)
      Console.Write(" and touchscreen control panel");
    Console.WriteLine();
  }
}

class CommercialRefrigerator
{
  private int door;
  private int drawer;
  private int shelf;
  private bool camera;
  private bool sensor;
  private bool touchscreen;

  public CommercialRefrigerator()
  {
    door = 1;
    drawer = 0;
    shelf = 1;
    camera = false;
    sensor = false;
    touchscreen = false;
  }
  public void SetDoor(int n)
  {
    if (n > 0)
      door = n;
    else
      door = 1;
  }
  public void SetDrawer(int d)
  {
    if (d >= 0)
      drawer = d;
    else
      drawer = 0;
  }
  public void SetShelf(int s)
  {
    if (s >= 0)
      shelf = s;
    else
      shelf = 0;
  }
  public void ConstructTheCabinet()
  {
    Console.WriteLine("The cabinet structure of commercial fridge is constructed.");
  }
  public void InstallCoolingSystem()
  {
    Console.WriteLine("The coolingSystem of commercial fridge is installed.");
  }
  public void ConnectRefrigerationCircuit()
  {
    Console.WriteLine("The refrigeration circuit of commercial fridge is connected.");
  }
  public void FillRefrigerantGas()
  {
    Console.WriteLine("The refrigerant gas is filled.");
  }
  public void InstallElectricalAndControlingSystem()
  {
    Console.WriteLine("The electrical and control systems of commercial fridge are installed.");
  }
  public void AssembleInternalParts(int door, int shelf, int drawer)
  {
    SetDoor(door);
    SetShelf(shelf);
    SetDrawer(drawer);
    Console.WriteLine("The internal parts of commercial fridge is assembled.");
  }
  public void InstallSmartSensors(bool smartSensors)
  {
    sensor = smartSensors;
    Console.WriteLine("The smart sensors are installed.");
  }
  public void InstallCameras(bool c)
  {
    camera = c;
    Console.WriteLine("The cameras are installed.");
  }
  public void InstallTouchScreenControlPanel(bool t)
  {
    touchscreen = t;
    Console.WriteLine("The touchscreen control panel is installed.");
  }
  public void Show()
  {
    Console.Write("Create:: commercial frige consists of ");
    Console.Write(door + (door > 1 ? " doors" : " door"));
    if (shelf > 0)
      Console.Write(", " + shelf + (shelf > 1 ? " shelves" : " shelf"));
    if (drawer > 0)
      Console.Write(", " + drawer + (drawer > 1 ? " drawers" : " drawer"));
    if (sensor)
      Console.Write(", smart sensors");
    if (camera)
      Console.Write(", cameras");
    if (touchscreen)
      Console.Write(" and touchscreen control panel");
    Console.WriteLine();
  }
}

interface IBuilder
{
  void Reset();
  void BuildCabinetStructure();
  void BuildCoolingSystemComponents();
  void BuildRefrigerationCircuit();
  void BuildElectricalAndControlSystem();
  void BuildInternalParts(int door, int shelf, int drawer);
  void SetRefrigerantGas();
  void AddSmartSensors(bool sensor);
  void AddCameras(bool camera);
  void AddTouchScreenControlPanel(bool touchscreen);
}

class HouseholdRefrigeratorBuilder : IBuilder
{
  private HouseholdRefrigerator householdFridge = new();

  public void Reset()
  {
    householdFridge = new HouseholdRefrigerator();
  }
  public void BuildCabinetStructure()
  {
    householdFridge.ConstructTheCabinet();
  }
  public void BuildCoolingSystemComponents()
  {
    householdFridge.InstallCoolingSystem();
  }
  public void BuildRefrigerationCircuit()
  {
    householdFridge.ConnectRefrigerationCircuit();
  }
  public void BuildElectricalAndControlSystem()
  {
    householdFridge.InstallElectricalAndControlingSystem();
  }
  public void BuildInternalParts(int door, int shelf, int drawer)
  {
    householdFridge.AssembleInternalParts(door, shelf, drawer);
  }
  public void SetRefrigerantGas()
  {
    householdFridge.FillRefrigerantGas();
  }
  public void AddSmartSensors(bool sensor)
  {
    householdFridge.InstallSmartSensors(sensor);
  }
  public void AddCameras(bool camera)
  {
    householdFridge.InstallCameras(camera);
  }
  public void AddTouchScreenControlPanel(bool touchscreen)
  {
    householdFridge.InstallTouchScreenControlPanel(touchscreen);
  }
  public HouseholdRefrigerator GetProduct()
  {
    HouseholdRefrigerator product = householdFridge;
    this.Reset();
    return product;
  }
}

class CommercialRefrigeratorBuilder : IBuilder
{
  private CommercialRefrigerator commercialFridge = new();

  public void Reset()
  {
    commercialFridge = new CommercialRefrigerator();
  }
  public void BuildCabinetStructure()
  {
    commercialFridge.ConstructTheCabinet();
  }
  public void BuildCoolingSystemComponents()
  {
    commercialFridge.InstallCoolingSystem();
  }
  public void BuildRefrigerationCircuit()
  {
    commercialFridge.ConnectRefrigerationCircuit();
  }
  public void BuildElectricalAndControlSystem()
  {
    commercialFridge.InstallElectricalAndControlingSystem();
  }
  public void BuildInternalParts(int door, int shelf, int drawer)
  {
    commercialFridge.AssembleInternalParts(door, shelf, drawer);
  }
  public void SetRefrigerantGas()
  {
    commercialFridge.FillRefrigerantGas();
  }
  public void AddSmartSensors(bool sensor)
  {
    commercialFridge.InstallSmartSensors(sensor);
  }
  public void AddCameras(bool camera)
  {
    commercialFridge.InstallCameras(camera);
  }
  public void AddTouchScreenControlPanel(bool touchscreen)
  {
    commercialFridge.InstallTouchScreenControlPanel(touchscreen);
  }
  public CommercialRefrigerator GetProduct()
  {
    CommercialRefrigerator product = commercialFridge;
    this.Reset();
    return product;
  }
}

class Director1
{
  public static void MakeSingleDoorRefrigerator(IBuilder builder)
  {
    builder.BuildCabinetStructure();
    builder.BuildCoolingSystemComponents();
    builder.BuildRefrigerationCircuit();
    builder.SetRefrigerantGas();
    builder.BuildElectricalAndControlSystem();
    builder.BuildInternalParts(1, 2, 1);
  }
  public static void MakeDoubleDoorRefrigerator(IBuilder builder)
  {
    builder.BuildCabinetStructure();
    builder.BuildCoolingSystemComponents();
    builder.BuildRefrigerationCircuit();
    builder.BuildElectricalAndControlSystem();
    builder.BuildInternalParts(2, 3, 1);
    builder.SetRefrigerantGas();
    builder.AddSmartSensors(true);
  }
}

class Director2(IBuilder b)
{
  private IBuilder builder = b;
  public void ChangeBuilder(IBuilder b)
  {
    builder = b;
  }
  public void MakeSmartRefrigerator()
  {
    builder.BuildCabinetStructure();
    builder.BuildCoolingSystemComponents();
    builder.BuildRefrigerationCircuit();
    builder.BuildElectricalAndControlSystem();
    builder.BuildInternalParts(2, 3, 2);
    builder.SetRefrigerantGas();
    builder.AddSmartSensors(true);
    builder.AddCameras(true);
    builder.AddTouchScreenControlPanel(true);
  }
}

class Program
{
  static void Main(string[] args)
  {
    Console.WriteLine("-------------------");
    Console.WriteLine("Create household single door refrigerator.");
    HouseholdRefrigeratorBuilder householdFridge = new();
    Director1.MakeSingleDoorRefrigerator(householdFridge);
    HouseholdRefrigerator householdFridgeProduct = householdFridge.GetProduct();
    householdFridgeProduct.Show();

    Console.WriteLine("-------------------");
    Console.WriteLine("Create commercial refrigerator with double doors.");
    CommercialRefrigeratorBuilder commercialFridge = new();
    Director1.MakeDoubleDoorRefrigerator(commercialFridge);
    CommercialRefrigerator commercialFridgeProcuct = commercialFridge.GetProduct();
    commercialFridgeProcuct.Show();

    Console.WriteLine("-------------------");
    Console.WriteLine("Create smart refrigerator for household.");
    Director2 d2 = new(householdFridge);
    d2.MakeSmartRefrigerator();
    householdFridgeProduct = householdFridge.GetProduct();
    householdFridgeProduct.Show();

    Console.WriteLine("-------------------");
    Console.WriteLine("Create smart refrigerator for restaurant kitchen.");
    d2.ChangeBuilder(commercialFridge);
    d2.MakeSmartRefrigerator();
    commercialFridgeProcuct = commercialFridge.GetProduct();
    commercialFridgeProcuct.Show();
  }
}
