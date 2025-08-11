#include <iostream>
using namespace std;

class HouseholdRefrigerator
{
private:
  int door;
  int drawer;
  int shelf;
  bool camera;
  bool sensor;
  bool touchscreen;

public:
  HouseholdRefrigerator(){
    door = 1;
    drawer = 0;
    shelf = 1;
    camera = false;
    sensor = false;
    touchscreen = false;
  }
  void setDoor(int n)
  {
    if (n > 0)
      door = n;
    else
      door = 1;
  }
  void setDrawer(int d)
  {
    if (d >= 0)
      drawer = d;
    else
      drawer = 0;
  }
  void setShelf(int s){
    if(s>= 0)
      shelf = s;
    else  
      shelf = 0;
  }
  void constructTheCabinet(){
    cout<<"The cabinet structure of household fridge is constructed."<<endl;
  }
  void installCoolingSystem(){
    cout<<"The coolingSystem of household fridge is installed."<<endl;
  }
  void connectRefrigerationCircuit(){
    cout<<"The refrigeration circuit of household fridge is connected."<<endl;
  }
  void fillRefrigerantGas(){
    cout<<"The refrigerant gas is filled."<<endl;
  }
  void installElectricalAndControlingSystem(){
    cout<<"The electrical and control systems of household fridge are installed."<<endl;
  }
  void assembleInternalParts(int door, int shelf, int drawer){
    setDoor(door);
    setShelf(shelf);
    setDrawer(drawer);
    cout<<"The internal parts of household fridge is assembled."<<endl;
  }
  void installSmartSensors(bool smartSensors){
    sensor = smartSensors;
    cout<<"The smart sensors are installed."<<endl;
  }
  void installCameras(bool c){
    camera = c;
    cout<<"The cameras are installed."<<endl;
  }
  void installTouchScreenControlPanel(bool t){
    touchscreen = t;
    cout<<"The touchscreen control panel is installed."<<endl;
  }
  void show(){
    cout<<"Create:: household frige consists of "
        <<door<<(door>1? " doors":" door");
    if(drawer>0) 
      cout<<", "<<drawer<<(drawer>1? " drawers": " drawer");
    if(shelf > 0)
      cout<<", "<<shelf<<(shelf>1? " shelves":" shelf");
    if(sensor)
      cout<<", smart sensors";
    if(camera)
      cout<<", cameras";
    if(touchscreen)
      cout<<" and touchscreen control panel";
    cout<<endl;
  }
};

class CommercialRefrigerator
{
private:
  int door;
  int drawer;
  int shelf;
  bool camera;
  bool sensor;
  bool touchscreen;

public:
  CommercialRefrigerator(){
    door = 1;
    drawer = 0;
    shelf = 1;
    camera = false;
    sensor = false;
    touchscreen = false;
  }
  void setDoor(int n)
  {
    if (n > 0)
      door = n;
    else
      door = 1;
  }
  void setDrawer(int d)
  {
    if (d >= 0)
      drawer = d;
    else
      drawer = 0;
  }
  void setShelf(int s){
    if(s>= 0)
      shelf = s;
    else  
      shelf = 0;
  }
  void constructTheCabinet(){
    cout<<"The cabinet structure of commercial fridge is constructed."<<endl;
  }
  void installCoolingSystem(){
    cout<<"The coolingSystem of commercial fridge is installed."<<endl;
  }
  void connectRefrigerationCircuit(){
    cout<<"The refrigeration circuit of commercial fridge is connected."<<endl;
  }
  void fillRefrigerantGas(){
    cout<<"The refrigerant gas is filled."<<endl;
  }
  void installElectricalAndControlingSystem(){
    cout<<"The electrical and control systems of commercial fridge are installed."<<endl;
  }
  void assembleInternalParts(int door, int shelf, int drawer){
    setDoor(door);
    setShelf(shelf);
    setDrawer(drawer);
    cout<<"The internal parts of commercial fridge is assembled."<<endl;
  }
  void installSmartSensors(bool smartSensors){
    sensor = smartSensors;
    cout<<"The smart sensors are installed."<<endl;
  }
  void installCameras(bool c){
    camera = c;
    cout<<"The cameras are installed."<<endl;
  }
  void installTouchScreenControlPanel(bool t){
    touchscreen = t;
    cout<<"The touchscreen control panel is installed."<<endl;
  }
  void show(){
    cout<<"Create::commercial frige consists of "
        <<door<<(door>1? " doors":" door");
    if(drawer>0) 
      cout<<", "<<drawer<<(drawer>1? " drawers": " drawer");
    if(shelf > 0)
      cout<<", "<<shelf<<(shelf>1? " shelves":" shelf");
    if(sensor)
      cout<<", smart sensors";
    if(camera)
      cout<<", cameras";
    if(touchscreen)
      cout<<" and touchscreen control panel";
    cout<<endl;
  }
};

class Builder{
  public:
    virtual ~Builder(){}
    virtual void reset() = 0;
    virtual void buildCabinetStructure() = 0;
    virtual void buildCoolingSystemComponents() = 0;
    virtual void buildRefrigerationCircuit() = 0;
    virtual void buildElectricalAndControlSystem() = 0;
    virtual void buildInternalParts(int door, int shelf, int drawer) = 0;
    virtual void setRefrigerantGas() = 0;
    virtual void addSmartSensors(bool sensor) = 0;
    virtual void addCameras(bool camera) = 0;
    virtual void addTouchScreenControlPanel(bool touchscreen) = 0;
};

class HouseholdRefrigeratorBuilder: public Builder{
  private:
    HouseholdRefrigerator* householdFridge;
  public:
    HouseholdRefrigeratorBuilder(){
      this->reset();
    }
    ~HouseholdRefrigeratorBuilder(){
      delete householdFridge;
    }
    void reset(){
      householdFridge = new HouseholdRefrigerator();
    }
    void buildCabinetStructure(){
      householdFridge->constructTheCabinet();
    }
    void buildCoolingSystemComponents(){
      householdFridge->installCoolingSystem();
    }
    void buildRefrigerationCircuit(){
      householdFridge->connectRefrigerationCircuit();
    }
    void buildElectricalAndControlSystem(){
      householdFridge->installElectricalAndControlingSystem();
    }
    void buildInternalParts(int door, int shelf, int drawer){
      householdFridge->assembleInternalParts(door, shelf, drawer);
    }
    void setRefrigerantGas(){
      householdFridge->fillRefrigerantGas();
    }
    void addSmartSensors(bool sensor){
      householdFridge->installSmartSensors(sensor);
    }
    void addCameras(bool camera){
      householdFridge->installCameras(camera);
    }
    void addTouchScreenControlPanel(bool touchscreen){
      householdFridge->installTouchScreenControlPanel(touchscreen);
    }
    HouseholdRefrigerator* getProduct(){
      HouseholdRefrigerator* result = householdFridge;
      this->reset();
      return result;
    }
};

class CommercialRefrigeratorBuilder: public Builder{
  private:
    CommercialRefrigerator* commercialFridge;
  public:
     CommercialRefrigeratorBuilder(){
      this->reset();
    }
    ~CommercialRefrigeratorBuilder(){
      delete commercialFridge;
    }
    void reset(){
      commercialFridge = new CommercialRefrigerator();
    }
    void buildCabinetStructure(){
      commercialFridge->constructTheCabinet();
    }
    void buildCoolingSystemComponents(){
      commercialFridge->installCoolingSystem();
    }
    void buildRefrigerationCircuit(){
      commercialFridge->connectRefrigerationCircuit();
    }
    void buildElectricalAndControlSystem(){
      commercialFridge->installElectricalAndControlingSystem();
    }
    void buildInternalParts(int door, int shelf, int drawer){
      commercialFridge->assembleInternalParts(door, shelf, drawer);
    }
    void setRefrigerantGas(){
      commercialFridge->fillRefrigerantGas();
    }
    void addSmartSensors(bool sensor){
      commercialFridge->installSmartSensors(sensor);
    }
    void addCameras(bool camera){
      commercialFridge->installCameras(camera);
    }
    void addTouchScreenControlPanel(bool touchscreen){
      commercialFridge->installTouchScreenControlPanel(touchscreen);
    }
    CommercialRefrigerator* getProduct(){
      CommercialRefrigerator* result = commercialFridge;
      this->reset();
      return result;
    }
};

class Director1{
  public:
    void makeSingleDoorRefrigerator(Builder* builder){
      builder->buildCabinetStructure();
      builder->buildCoolingSystemComponents();
      builder->buildRefrigerationCircuit();
      builder->setRefrigerantGas();
      builder->buildElectricalAndControlSystem();
      builder->buildInternalParts(1,2,1);
    }
    void makeDoubleDoorRefrigerator(Builder* builder){
      builder->buildCabinetStructure();
      builder->buildCoolingSystemComponents();
      builder->buildRefrigerationCircuit();      
      builder->buildElectricalAndControlSystem();
      builder->buildInternalParts(2,3,1);
      builder->setRefrigerantGas();
      builder->addSmartSensors(true);
    }
};

class Director2{
  private:
    Builder* builder;
  public:
    Director2(Builder* b){
      this->builder = b;
    }
    void changeBuilder(Builder* b){
      if(this->builder != nullptr)
        delete builder;
      this->builder = b;
    }
    void makeSmartRefrigerator(){
      builder->buildCabinetStructure();
      builder->buildCoolingSystemComponents();
      builder->buildRefrigerationCircuit();      
      builder->buildElectricalAndControlSystem();
      builder->buildInternalParts(2,3,2);
      builder->setRefrigerantGas();
      builder->addSmartSensors(true);
      builder->addCameras(true);
      builder->addTouchScreenControlPanel(true);
    }
};

int main()
{
  cout<<"--------------------"<<endl;
  cout<<"Create household single door refrigerator."<<endl;
  HouseholdRefrigeratorBuilder* householdFridge = new HouseholdRefrigeratorBuilder();
  Director1 *d = new Director1();
  d->makeSingleDoorRefrigerator(householdFridge);
  HouseholdRefrigerator* householdFridgeProduct = householdFridge->getProduct();
  householdFridgeProduct->show();
  delete householdFridgeProduct;

  cout<<"--------------------"<<endl;
  cout<<"Create commercial refrigerator with double doors."<<endl;
  CommercialRefrigeratorBuilder* commercialFrige = new CommercialRefrigeratorBuilder();
  d->makeDoubleDoorRefrigerator(commercialFrige);
  CommercialRefrigerator* commercialFridgeProduct = commercialFrige->getProduct();
  commercialFridgeProduct->show();
  delete commercialFridgeProduct;

  cout<<"--------------------"<<endl;
  cout<<"Create smart refrigerator for household."<<endl;
  Director2* d2 = new Director2(householdFridge);
  d2->makeSmartRefrigerator();
  householdFridgeProduct = householdFridge->getProduct();
  householdFridgeProduct->show();
  delete householdFridgeProduct;

  cout<<"--------------------"<<endl;
  cout<<"Create smart refrigerator for restaurant kitchen."<<endl;
  d2->changeBuilder(commercialFrige);
  d2->makeSmartRefrigerator();
  commercialFridgeProduct = commercialFrige->getProduct();
  commercialFridgeProduct->show();
  delete commercialFridgeProduct;

  delete householdFridge;
  delete commercialFrige;
  delete d;
  delete d2;

  return 0;
}