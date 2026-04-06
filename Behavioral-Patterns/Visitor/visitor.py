from abc import ABC, abstractmethod

class Flower(ABC):
  @abstractmethod
  def accept(self, visitor):
    pass

class Lavender(Flower):
  def __init__(self, name, color, region):
    self.__name = name
    self.__color = color
    self.__region = region

  def get_name(self): 
    return self.__name
  
  def get_color(self):
    return self.__color
  
  def get_region(self):
    return self.__region
  
  def accept(self, visitor):
    visitor.visit_lavender(self)

class Rose(Flower):
  def __init__(self, name, color, region):
    self.__name = name
    self.__color = color
    self.__region = region
  
  def get_name(self):
    return self.__name

  def get_color(self):
    return self.__color
  
  def get_region(self):
    return self.__region
  
  def accept(self, visitor):
    visitor.visit_rose(self)
  
class Jasmine(Flower):
  def __init__(self, name, region):
    self.__name = name
    self.__region = region
  
  def get_name(self):
    return self.__name
  
  def get_region(self):
    return self.__region
  
  def accept(self, visitor):
    visitor.visit_jasmine(self)
  

class Visitor(ABC):
  @abstractmethod
  def visit_rose(self, rose):
    pass

  @abstractmethod
  def visit_lavender(self, lavender):
    pass
  
  @abstractmethod
  def visit_jasmine(self, jasmine):
    pass

class MakerPerfumeVisitor(Visitor):
  def visit_rose(self, rose):
    print(f"  Make perfume out of {rose.get_name()}.")
  
  def visit_lavender(self, lavender):
    print(f"  Make perfume out of {lavender.get_name()}.")

  def visit_jasmine(self, jasmine):
    print(f"  Make perfume out of {jasmine.get_name()}.")

class MakeTeaVisitor(Visitor):
  def visit_rose(self, rose):
    print(f"  Make tea out of dry {rose.get_name()}.")

  def visit_lavender(self, lavender):
    print(f"  Make tea out of dry {lavender.get_name()}")

  def visit_jasmine(self, jasmine):
    print(f"  Make tea out of dry {jasmine.get_name()}.")

class FlowerGarden:
  def __init__(self):
    self.flowers = []
  
  def add_flower(self, flower):
    self.flowers.append(flower)

  def apply_visitor(self, visitor):
    for flower in self.flowers:
      flower.accept(visitor)
    

def main():
  garden = FlowerGarden()
  garden.add_flower(Rose("Rose","Red","South Asia"))
  garden.add_flower(Lavender("Lavender","purple","France"))
  garden.add_flower(Jasmine("Jasmine","Southeaset Asia"))

  print("\nUsing MakePerfumeVisitor:")
  garden.apply_visitor(MakerPerfumeVisitor())

  print("\nUsing MakeTeaVisitor")
  garden.apply_visitor(MakeTeaVisitor())

if __name__ == "__main__":
  main()