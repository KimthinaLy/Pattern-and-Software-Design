#include <iostream>
using namespace std;

class Backpack{
  public:
    ~Backpack(){}
    virtual string produce() = 0;
    virtual float price(int numberOfBags) = 0;
};

class NylonBackpack: public Backpack{
  public:
    string produce(){
      return "Nylon Backpack";
    }
    float price(int numberOfBags){
      return numberOfBags * 8;
    }
};

class CottonBackpack: public Backpack{
  public:
    string produce(){
      return "Cotton Backpack";
    }
    float price(int numberOfBags){
      return numberOfBags * 6;
    }
};

class CanvasBackpack: public Backpack{
  public:
    string produce(){
      return "Canvas Backpack";
    }
    float price(int numberOfBags){
      return numberOfBags * 7;
    }
};

class Handbag{
  public:
    ~Handbag(){}
    virtual string produce() = 0;
    virtual float price(int numberOfBags) = 0;
};

class NylonHandbag: public Handbag{
  public:
    string produce(){
      return "Nylon Handbag";
    }
    float price(int numberOfBags){
      return numberOfBags * 7;
    }
};

class CottonHandbag: public Handbag{
  public:
    string produce(){
      return "Cotton Handbag";
    }
    float price(int numberOfBags){
      return numberOfBags * 5;
    }
};

class CanvasHandbag: public Handbag{
  public:
    string produce(){
      return "Canvas Handbag";
    }
    float price(int numberOfBags){
      return numberOfBags * 6;
    }
};

class ToteBag{
  public:
    ~ToteBag(){}
    virtual string produce() = 0;
    virtual float price(int numberOfBags) = 0;
  };

class NylonToteBag: public ToteBag{
  public:
    string produce(){
      return "Nylon ToteBag";
    }
    float price(int numberOfBags){
      return numberOfBags * 4;
    }
};

class CottonToteBag: public ToteBag{
  public:
    string produce(){
      return "Cotton ToteBag";
    }
    float price(int numberOfBags){
      return numberOfBags * 1.5;
    }
};

class CanvasToteBag: public ToteBag{
  public:
    string produce(){
      return "Canvas ToteBag";
    }
    float price(int numberOfBags){
      return numberOfBags * 2;
    }
};

class BagFactory{
  public:
    ~BagFactory(){};
    virtual Backpack* createBackpack() = 0;
    virtual Handbag* createHandbag() = 0;
    virtual ToteBag* createToteBag() = 0;
};

class NylonBagFactory: public BagFactory{
  public:
    Backpack* createBackpack(){
      return new NylonBackpack();
    }
    Handbag* createHandbag(){
      return new NylonHandbag();
    }  
    ToteBag* createToteBag(){
      return new NylonToteBag();
    }
};

class CottonBagFactory: public BagFactory{
  public:
    Backpack* createBackpack(){
      return new CottonBackpack();
    }
    Handbag* createHandbag(){
      return new CottonHandbag();
    }  
    ToteBag* createToteBag(){
      return new CottonToteBag();
    }
};

class CanvasBagFactory: public BagFactory{
  public:
    Backpack* createBackpack(){
      return new CanvasBackpack();
    }
    Handbag* createHandbag(){
      return new CanvasHandbag();
    }  
    ToteBag* createToteBag(){
      return new CanvasToteBag();
    }
};

class Client{
  private:
    BagFactory* factory;
    Backpack* backpack;
    Handbag* handbag;
    ToteBag* toteBag;
  public:
    Client(){
      factory = 0;
      backpack = 0;
      handbag = 0;
      toteBag = 0;
    }
    Client(BagFactory* f){
      factory = f;
      backpack = 0;
      handbag = 0;
      toteBag = 0;
    }
    ~Client(){
      delete backpack;
      delete handbag;
      delete toteBag;
    }
    void createClientBackpack(){
      if(backpack != 0)
        delete backpack;
      
      backpack = factory->createBackpack();
    }
    void createClientHandbag(){
      if(handbag != 0)
        delete handbag;

      handbag = factory->createHandbag();
    }

    void createClientToteBag(){
      if(toteBag != 0)
        delete toteBag;

      toteBag = factory->createToteBag();
    }

    string produceBackpack(){
      return backpack->produce();
    }

    string produceHandbag(){
      return handbag->produce();
    }

    string produceToteBag(){
      return toteBag->produce();
    }
};

void client(BagFactory &factory){
  Backpack* backpack = factory.createBackpack();
  Handbag* handbag = factory.createHandbag();
  ToteBag* toteBag = factory.createToteBag();
  cout<<backpack->produce()<<endl;
  cout<<backpack->price(10)<<endl;
  cout<<handbag->produce()<<endl; 
  cout<<handbag->price(5)<<endl;
  cout<<toteBag->produce()<<endl;
  cout<<toteBag->price(15)<<endl;

  delete backpack;
  delete handbag;
  delete toteBag;
}

int main(){
  cout<<"Nylon Bag Factory"<<endl;
  cout<<"-------------"<<endl;
  NylonBagFactory* nylon = new NylonBagFactory();
  client(*nylon);
  cout<<endl;
  
  cout<<"Cotton Bag Factory"<<endl;
  cout<<"-------------"<<endl;
  CottonBagFactory* cotton = new CottonBagFactory();
  client(*cotton);
  cout<<endl;

  cout<<"Canvas Bag Factory"<<endl;
  cout<<"-------------"<<endl;
  CanvasBagFactory* canvas = new CanvasBagFactory();
  client(*canvas);
  cout<<endl;

  cout<<"Client class"<<endl;
  cout<<"-------------"<<endl;
  Client client1(nylon);
  client1.createClientBackpack();
  client1.createClientHandbag();
  client1.createClientToteBag();
  cout<<client1.produceBackpack()<<endl;
  cout<<client1.produceHandbag()<<endl;
  cout<<client1.produceToteBag()<<endl;

  Client client2(cotton);
  client2.createClientToteBag();
  cout<<client2.produceToteBag();

  delete nylon;
  delete cotton;
  delete canvas;

  return 0;
}