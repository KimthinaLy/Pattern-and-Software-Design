// See https://aka.ms/new-console-template for more information
using System;

class CylinderShapedObject
{
  private double mass; //measured in gram
  private double radius; //measured in centimeter
  private double height; //measured in centimeter

  public CylinderShapedObject(double mass, double radius, double height)
  {
    SetMass(mass);
    SetRadius(radius);
    SetHeight(height);
  }
  public void SetMass(double m)
  {
    if (m > 0)
      mass = m;
    else
      mass = 1;
  }
  public void SetRadius(double r)
  {
    if (r > 0)
      radius = r;
    else
      radius = 0.5;
  }
  public void SetHeight(double h)
  {
    if (h > 0)
      height = h;
    else
      height = 0.1;
  }
  public double GetMass()
  {
    return mass;
  }
  public double GetRadius()
  {
    return radius;
  }
  public double GetHeight()
  {
    return height;
  }
  public void Show()
  {
    Console.WriteLine("Mass = " + mass + " grams.");
    Console.WriteLine("Radius = " + radius + " centimeters.");
    Console.WriteLine("Height = " + height + " centimeters.");
  }
}

class RectangleShapedObject
{
  private double mass; //measured in gram
  private double width; //measured in centimeter
  private double height; //measured in centimeter
  private double length; //measured in centimeter

  public RectangleShapedObject(double mass, double width, double height, double length)
  {
    SetMass(mass);
    SetBaseOfObject(width);
    SetHeight(height);
    SetLength(length);
  }
  public void SetMass(double m)
  {
    if (m > 0)
      mass = m;
    else
      mass = 0.5;
  }
  public void SetBaseOfObject(double w)
  {
    if (w > 0)
      width = w;
    else
      width = 0.5;
  }
  public void SetHeight(double h)
  {
    if (h > 0)
      height = h;
    else
      height = 0.5;
  }
  public void SetLength(double l)
  {
    if (l > 0)
      length = l;
    else
      length = 0.5;
  }
  public double GetMass()
  {
    return mass;
  }
  public double GetWidth()
  {
    return width;
  }
  public double GetHeight()
  {
    return height;
  }
  public double GetLength()
  {
    return length;
  }
  public void Show()
  {
    Console.WriteLine("Mass = " + mass + " grams.");
    Console.WriteLine("Width = " + width + " centimeter.");
    Console.WriteLine("Height = " + height + " centimeters.");
    Console.WriteLine("Lenght = " + length + " centimeters;");
  }
}

interface IObject
{
  double GetObjectDensity();
}

class CylinderObjectAdapter(CylinderShapedObject a) : IObject
{
  private readonly CylinderShapedObject adaptee = a;

  public double GetObjectDensity()
  {
    const double PI = 3.14159;
    double density = adaptee.GetMass() / PI * Math.Pow(adaptee.GetRadius(), 2) * adaptee.GetHeight();
    return density;
  }
}

class RectangleObjectAdapter(RectangleShapedObject r) : IObject
{
  private readonly RectangleShapedObject adaptee = r;

  public double GetObjectDensity()
  {
    double density = adaptee.GetMass() / adaptee.GetWidth() * adaptee.GetHeight() * adaptee.GetLength();
    return density;
  }
}

class Water
{
  private const double waterDensity = 1;
  public static bool FloatOnWater(IObject obj)
  {
    if (obj.GetObjectDensity() < waterDensity)
      return true;
    else
      return false;
  }
  public static double GetWaterDensity()
  {
    return waterDensity;
  }
}

class Program
{
  static void CheckObjectFloatability(IObject obj)
  {
    if (Water.FloatOnWater(obj))
    {

      Console.WriteLine("This object floats on water.");
      Console.WriteLine("Object's density (" + obj.GetObjectDensity() +  " cm3) < Water's density (" + Water.GetWaterDensity() + " cm3) ");
    }
    else
    {
      Console.WriteLine("This object does not float on water.");
      Console.WriteLine("Object's density (" + obj.GetObjectDensity() + " cm3) > Water's density (" + Water.GetWaterDensity() + " cm3) ");
    }
  }
  static void Main(string[] args)
  {
    Console.WriteLine("---------- Coin ---------");
    CylinderShapedObject coin = new(0.1, 1.5, 0.2);
    coin.Show();
    CylinderObjectAdapter coinAdaper = new(coin);
    CheckObjectFloatability(coinAdaper);
    Console.WriteLine();

    Console.WriteLine("---------- Plastic Box ---------");
    RectangleShapedObject plasticBox = new(4, 4, 6, 6);
    plasticBox.Show();
    RectangleObjectAdapter plasticBoxAdapter = new(plasticBox);
    CheckObjectFloatability(plasticBoxAdapter);
    Console.WriteLine();

    Console.WriteLine("---------- SteelRod ---------");
    CylinderShapedObject steelRod = new(980, 2, 10);
    steelRod.Show();
    CylinderObjectAdapter steelRodAdapter = new(steelRod);
    CheckObjectFloatability(steelRodAdapter);
    Console.WriteLine();
  }
}
