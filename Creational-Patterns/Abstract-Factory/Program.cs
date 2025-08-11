// See https://aka.ms/new-console-template for more information
using System;

interface IBackpack
{
  string Produce();
  double Price(int numberOfBags);
}

class NylonBackpack : IBackpack
{
  public string Produce()
  {
    return "Nylon Backpack";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 8;
  }
}

class CottonBackpack : IBackpack
{
  public string Produce()
  {
    return "Cotton Backpack";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 6;
  }
}

class CanvasBackpack : IBackpack
{
  public string Produce()
  {
    return "Canvas Backpack";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 7;
  }
}

interface IHandbag
{
  string Produce();
  double Price(int numberOfBags);
}

class NylonHandbag : IHandbag
{
  public string Produce()
  {
    return "Nylon Handbag";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 7;
  }
}

class CottonHandbag : IHandbag
{
  public string Produce()
  {
    return "Cotton Handbag";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 5;
  }
}

class CanvasHandbag : IHandbag
{
  public string Produce()
  {
    return "Canvas Handbag";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 6;
  }
}

interface IToteBag
{
  string Produce();
  double Price(int numberOfBags);
}

class NylonToteBag : IToteBag
{
  public string Produce()
  {
    return "Nylon ToteBag";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 4;
  }
}

class CottonToteBag : IToteBag
{
  public string Produce()
  {
    return "Cotton ToteBag";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 1.5;
  }
}

class CanvasToteBag : IToteBag
{
  public string Produce()
  {
    return "Canvas ToteBag";
  }
  public double Price(int numberOfBags)
  {
    return numberOfBags * 2;
  }
}

interface IBagFactory
{
  IBackpack CreateBackpack();
  IHandbag CreateHandbag();
  IToteBag CreateToteBag();
}

class NylonBagFactory : IBagFactory
{
  public IBackpack CreateBackpack()
  {
    return new NylonBackpack();
  }
  public IHandbag CreateHandbag()
  {
    return new NylonHandbag();
  }
  public IToteBag CreateToteBag()
  {
    return new NylonToteBag();
  }
};

class CottonBagFactory : IBagFactory
{
  public IBackpack CreateBackpack()
  {
    return new CottonBackpack();
  }
  public IHandbag CreateHandbag()
  {
    return new CottonHandbag();
  }
  public IToteBag CreateToteBag()
  {
    return new CottonToteBag();
  }
};

class CanvasBagFactory : IBagFactory
{
  public IBackpack CreateBackpack()
  {
    return new CanvasBackpack();
  }
  public IHandbag CreateHandbag()
  {
    return new CanvasHandbag();
  }
  public IToteBag CreateToteBag()
  {
    return new CanvasToteBag();
  }
};

class Client
{
  private readonly IBagFactory? factory; //only assing in constructor "make field readonly suggested"
  private IBackpack? backpack;
  private IHandbag? handbag;
  private IToteBag? toteBag;

  public Client()
  {
    factory = null;
    backpack = null;
    handbag = null;
    toteBag = null;
  }
  public Client(IBagFactory f)
  {
    factory = f;
    backpack = null;
    handbag = null;
    toteBag = null;
  }
  public void CreateClientBackpack()
  {
    if (factory != null)
      backpack = factory.CreateBackpack();
  }
  public void CreateClientHandbag()
  {
    if (factory != null)
      handbag = factory.CreateHandbag();
  }
  public void CreateClientToteBag()
  {
    if (factory != null)
      toteBag = factory.CreateToteBag();
  }
  public string ProduceBackpack()
  {
    if (backpack != null)
      return backpack.Produce();
    return "backpack producing failed";
  }
  public string ProduceHandbag()
  {
    if (handbag != null)
      return handbag.Produce();
    return "handbag producing failed";
  }
  public string ProduceToteBag()
  {
    if (toteBag != null)
      return toteBag.Produce();
    return "toteBag producing failed";
  }
}

class Program
{
  public static void ClientFunction(IBagFactory factory)
  {
    IBackpack backpack = factory.CreateBackpack();
    IHandbag handbag = factory.CreateHandbag();
    IToteBag toteBag = factory.CreateToteBag();

    Console.WriteLine(backpack.Produce());
    Console.WriteLine("Total price = " + backpack.Price(10) + " dollars");
    Console.WriteLine(handbag.Produce());
    Console.WriteLine("Total price = " + handbag.Price(5) + " dollars");
    Console.WriteLine(toteBag.Produce());
    Console.WriteLine("Total price = " + toteBag.Price(15) + " dollars");
  }

  static void Main(string[] args)
  {
    Console.WriteLine("Nylon Bag Factory");
    Console.WriteLine("-----------------");
    NylonBagFactory nylon = new();
    ClientFunction(nylon);
    Console.WriteLine();

    Console.WriteLine("Cotton Bag Factory");
    Console.WriteLine("-----------------");
    CottonBagFactory cotton = new();
    ClientFunction(cotton);
    Console.WriteLine();

    Console.WriteLine("Canvas Bag Factory");
    Console.WriteLine("-----------------");
    CanvasBagFactory canvas = new();
    ClientFunction(canvas);
    Console.WriteLine();

    Console.WriteLine("Client class");
    Console.WriteLine("-------------");
    Client client1 = new(nylon);
    client1.CreateClientBackpack();
    client1.CreateClientHandbag();
    client1.CreateClientToteBag();
    Console.WriteLine(client1.ProduceBackpack());
    Console.WriteLine(client1.ProduceHandbag());
    Console.WriteLine(client1.ProduceToteBag());

    Client client2 = new(cotton);
    client2.CreateClientToteBag();
    Console.WriteLine(client2.ProduceToteBag());
  }
}