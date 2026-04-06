// See https://aka.ms/new-console-template for more information
using System;
using System.Collections.Generic;

interface IMedicine
{
  string GetMedicineType();
  string GetForm();
  string GetEffect();
}

class Liquid(string type, string effect) : IMedicine
{
  private readonly string type = type;
  private readonly string effect = effect;

  public string GetMedicineType() => $"medicine's type = {type}";
  public string GetForm() =>"liquid-form medicine";
  public string GetEffect() =>effect;
}
class Pill(string type, string effect) : IMedicine
{
  private readonly string type = type;
  private readonly string effect = effect;

  public string GetMedicineType() => $"medicine's type = {type}";
  public string GetForm() =>"pill-form medicine";
  public string GetEffect() =>effect;
}

abstract class DiseaseTreament(IMedicine medicine)
{
  private readonly IMedicine medicine = medicine;
  public IMedicine GetMedicine() =>medicine;
  public abstract string GetTreatmentDetail();
}

class HumanTreatment(IMedicine medicine) : DiseaseTreament(medicine)
{
  public override string GetTreatmentDetail()
  {
    IMedicine medicine = GetMedicine();

    return $"Human's treatment: {medicine.GetMedicineType()}, {medicine.GetForm()}, {medicine.GetEffect()}";
  }
}

class AnimalTreatment(IMedicine medicine, string animal) : DiseaseTreament(medicine)
{
  private readonly string animal = animal;
  public override string GetTreatmentDetail()
  {
    IMedicine medicine = GetMedicine();
    return $"{animal}'s treatment: {medicine.GetMedicineType()}, {medicine.GetForm()}, {medicine.GetEffect()}";
  }
}

class Program
{
  public static void Client(List<DiseaseTreament> treaments)
  {
    foreach (var t in treaments)
    {
      Console.WriteLine(t.GetTreatmentDetail());
    }
  }
  static void Main(string[] args)
  {
    Console.WriteLine("-----Cat's Treatment-----");
    Liquid liquidAntibiotic = new("antibiotic", "treat bacterial infections");

    Pill corticosteriodPill = new("Corticosteroid", "reduces inflammation and helps manage allergies");

    List<DiseaseTreament> catTreatment = [new AnimalTreatment(liquidAntibiotic, "cat"), new AnimalTreatment(corticosteriodPill, "cat")];
    Client(catTreatment);

    Console.WriteLine();
    Console.WriteLine("-----Human's Treatment-----");
    List<DiseaseTreament> humanTreatment = [new HumanTreatment(liquidAntibiotic), new HumanTreatment(corticosteriodPill)];
    Client(humanTreatment);

    Console.WriteLine();
    Console.WriteLine("-----Liquid-Fome Medical Treatment-----");
    List<DiseaseTreament> liquidMedicine = [new AnimalTreatment(liquidAntibiotic, "animal"), new HumanTreatment(liquidAntibiotic)];
    Client(liquidMedicine);

  }
}