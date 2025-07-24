// See https://aka.ms/new-console-template for more information
using System;
using System.Collections.Generic;

interface IApp
{
  string Founder();
  string DateFounded();
  string Website();
}

class Facebook : IApp
{
  public string Founder()
  {
    return "Facebook's founder: Mark Zuckerberg";
  }
  public string DateFounded()
  {
    return "Facebook's founded date: February 4, 2004";
  }
  public string Website()
  {
    return "Facebook's website: www.facebook.com";
  }
}

class Instagram : IApp
{
  public string Founder()
  {
    return "Instagram's founder: Kevin Systrom and Mike Krieger";
  }
  public string DateFounded()
  {
    return "Instagram's founded date: October 6, 2010";
  }
  public string Website()
  {
    return "Instagram's website: www.instagram.com";
  }
}

class TikTok : IApp
{
  public string Founder()
  {
    return "TikTok's founder: Zhang Yiming";
  }
  public string DateFounded()
  {
    return "TikTok's founded date: September 2016";
  }
  public string Website()
  {
    return "TikTok's website: www.tiktok.com";
  }
}

class Amazon : IApp
{
  public string Founder()
  {
    return "Amazon's founder: Jeff Bezos";
  }
  public string DateFounded()
  {
    return "Amazon's founded date: July 5, 1994";
  }
  public string Website()
  {
    return "Amazon's website: www.amazon.com";
  }
}

class Shopee : IApp
{
  public string Founder()
  {
    return "Shopee's founder: Chris Feng";
  }
  public string DateFounded()
  {
    return "Shopee's founded date: 2015";
  }
  public string Website()
  {
    return "Shopee's website: www.shopee.com";
  }
}

abstract class DigitalPlatform
{
  public abstract List<IApp> GetApp();
  public string BasicInformation()
  {
    List<IApp> apps = this.GetApp();
    string result = "";

    foreach (IApp a in apps)
    {
      result += a.Founder() + "\n";
      result += a.DateFounded() + "\n";
      result += a.Website() + "\n";
      result += "\n";
    }
     return result;
  }
}

class SocialMedia : DigitalPlatform
{
  public override List<IApp> GetApp()
  {
    List<IApp> apps = new List<IApp>();
    apps.Add(new Facebook());
    apps.Add(new Instagram());
    apps.Add(new TikTok());
    return apps;
  }
}

class E_commerce : DigitalPlatform
{
  public override List<IApp> GetApp()
  {
    List<IApp> apps = new List<IApp>() { new Amazon(), new Shopee() };
    return apps;
  }
}

class Program 
{
  public static void Client(DigitalPlatform d)
  {
    Console.WriteLine(d.BasicInformation());
  }
  static void Main(string[] args)
  {
    DigitalPlatform d = new SocialMedia();
    Console.WriteLine("--------SocialMedia-------");
    Client(d);

     d = new E_commerce();
    Console.WriteLine("--------E-commerce-------");
    Client(d);

  }
}