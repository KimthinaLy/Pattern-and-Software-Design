// See https://aka.ms/new-console-template for more information
using System;
using System.Collections.Generic;
using System.Reflection;

class Product
{
  private string id = "";
  private int amount;
  private double price;

  public Product() : this("0000", 1, 10){}
  public Product(string id, int amount, double price)
  {
    SetId(id);
    SetAmount(amount);
    SetPrice(price);
  }
  public void SetId(string i)
  {
    id = i;
  }
  public void SetAmount(int a)
  {
    amount = a > 0 ? a : 1;
  }
  public void SetPrice(double p)
  {
     price = p > 10 ? p: 10;
  }
  public string GetId()
  {
    return id;
  }
  public int GetAmount()
  {
    return amount;
  }
  public double GetPrice()
  {
    return price;
  }
  public void Show()
  {
    Console.WriteLine($"  Product id = {id}  amount = {amount}  price-per-unit = {price} total-price = {amount * price}");
  }
}

class MyShoppingCart
{
  private static MyShoppingCart? instance;
  private List<Product> cart = new();
  private MyShoppingCart()
  {
    Console.WriteLine("Create shopping-cart.");
  }
  public static MyShoppingCart GetInstance()
  {
    if (instance == null)
    {
      instance = new();
    }
    else
    {
      Console.WriteLine("Return existing shopping-cart.");
    }
    return instance;
  }
  public void AddProduct(Product p)
  {
    cart.Add(p);
    Console.WriteLine($"Product {p.GetId()} is added to cart.");
  }
  public void RemoveProduct(string id)
  {
    Product ? product = null;
    foreach (Product p in cart)
    {
      if (p.GetId() == id)
      {
        product = p;
        break;
      }
    }
    if (product != null)
    {
      cart.Remove(product);
      Console.WriteLine($"Product {id} is remove to cart.");
    }
    else
      Console.WriteLine($"Product {id} is not in the cart.");
  }
  public double GetTotalPrice()
  {
    double price = 0;
    foreach (Product p in cart)
    {
      price += p.GetPrice() * p.GetAmount();
    }
    return price;
  }
  public void ResetCart()
  {
    cart.Clear();
    Console.WriteLine("Cart is reset.");
  }
  public void ShowCart()
  {
    Console.WriteLine("Products in cart: ");
    foreach (var p in cart)
    {
      p.Show();
    }
  }
}

class Program
{
  static void Main(String[] args)
  {
    Console.WriteLine("---Creating \"cart1\"---");
    MyShoppingCart cart1 = MyShoppingCart.GetInstance();
    cart1.AddProduct(new Product("0001", 2, 100));
    cart1.AddProduct(new Product("0002", 3, 50.5));
    cart1.ShowCart();

    Console.WriteLine();
    Console.WriteLine("---Creating \"cart2\"---");
    MyShoppingCart cart2 = MyShoppingCart.GetInstance();
    cart2.AddProduct(new Product("0003", 2, 300));
    cart2.ShowCart();

    Console.WriteLine();
    Console.WriteLine("---Remove product \"0002\" from \"cart2\"---");
    cart2.RemoveProduct("0002");
    Console.WriteLine("---Show \"cart2\"---");
    cart2.ShowCart();
    Console.WriteLine("Total price = " + cart2.GetTotalPrice());
    Console.WriteLine("---Show \"cart1\"---");
    cart1.ShowCart();
    Console.WriteLine("Total price = " + cart1.GetTotalPrice());
  }
}