#include <iostream>
#include <list>

using namespace std;

//interface
class App{
  public:
    virtual ~App(){};
    virtual string founder() = 0;
    virtual string dateFounded() = 0;
    virtual string website() = 0;
};

//implementation of interface
class Facebook: public App{
  public:
    string founder(){
      return "Facebook's founder: Mark Zuckerberg";
    }
    string dateFounded(){
      return "Facebook's founded date: February 4, 2004";
    }
    string website(){
      return "Facebook's website: www.facebook.com";
    }
};

//implementation of interface
class Instagram: public App{
  public:
    string  founder(){
      return "Instagram's founder: Kevin Systrom and Mike Krieger";
    }
    string dateFounded(){
      return "Instagram's founded date: October 6, 2010";
    }
    string website(){
      return "Instagram's website: www.instagram.com";
    }
};

//implementation of interface
class TikTok: public App{
  public:
    string founder(){
      return "TikTok's founder: Zhang Yiming";
    }
    string dateFounded(){
      return "TikTok's founded date: September 2016";
    }
    string website(){
      return "TikTok's website: www.tiktok.com";
    }
};

//implementation of interface
class Amazon: public App{
  public:
    string founder(){
      return "Amazon's founder: Jeff Bezos";
    }
    string dateFounded(){
      return "Amazon's founded date: July 5, 1994";
    }
    string website(){
       return "Amazon's website: www.amazon.com";
    }
};

//implementation of interface
class Shopee: public App{
  public:
    string founder(){
      return "Shopee's founder: Chris Feng";
    }
    string dateFounded(){
      return "Shopee's founded date: 2015";
    }
    string website(){
      return "Shopee's website: www.shopee.com";
    }
};

//Abstract class
class DigitalPlatform{
  public:
    virtual ~DigitalPlatform(){}
    virtual list <App*> getApp() = 0;
    string basicInformation(){
      list <App*> app = this->getApp();
      string result = "";

      for (App* a: app){
        result += a->founder() + "\n";
        result += a->dateFounded() + "\n";
        result += a->website() + "\n";
        result += "\n";
      }

      for(App* a: app){
        delete a;
      }
      return result;
    }
};

class SocialMedia: public DigitalPlatform{
  public:
    list <App*> getApp(){
      list <App*> a{new Facebook, new Instagram, new TikTok};
      return a;
    }
};

class E_commerce: public DigitalPlatform{
  public:
    list <App*> getApp(){
      list <App*> a{new Amazon, new Shopee};
      return a;
    }
};

void client(DigitalPlatform& d){
  cout<<d.basicInformation()<<endl;
}

int main(){
  DigitalPlatform *d = new SocialMedia;
  cout<<"--------SocialMedia-------"<<endl;
  client(*d);
  delete d;

  d = new E_commerce;
  cout<<"--------E-commerce--------"<<endl;
  client(*d);
  delete d;

  return 0;
}