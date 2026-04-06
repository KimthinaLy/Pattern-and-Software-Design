from abc import ABC, abstractmethod

#=========================
#         DECORATOR
#=========================

class SmartKitchen(ABC):
  @abstractmethod
  def get_smart_kitchen_description(self):
    pass

class Kitchen(SmartKitchen):
  def __init__(self, width, length, height):
    self.width = 0
    self.length = 0
    self.height = 0
    self.set_width(width)
    self.set_height(height)
    self.set_length(length)

  def set_width(self, w):
    if(w < 5):
      w = 5
    self.width = w

  def set_length(self, l):
    if(l < 5):
      l = 5
    self.length = l

  def set_height(self, h):
    if(h < 5):
      h = 5
    self.height = h
  
  def get_smart_kitchen_description(self):
    return f"Kitchen size = {self.width} x {self.length} x {self.height}\n"

class SmartKitchenDecorator(SmartKitchen):
  def __init__(self, kitchen):
    self.smart_kitchen = kitchen
  
  def get_smart_kitchen_description(self):
    return self.smart_kitchen.get_smart_kitchen_description()

class SmartAppliance(ABC):
  @abstractmethod
  def accept(visitor):
    pass

class SmartRefrigerator(SmartKitchenDecorator,SmartAppliance):
  def __init__(self, brand, capacity, wifi_connected, voice_assistant_enable, smart_kitchen):
    super().__init__(smart_kitchen)
    self.brand = brand
    self.capacity = 0
    self.set_capacity(capacity)
    self.wifi_connected = wifi_connected
    self.voice_assistance_enable = voice_assistant_enable
  
  def set_capacity(self, c):
    if(c < 150):
      c = 150
    self.capacity = c

  def get_smart_kitchen_description(self):
    return super().get_smart_kitchen_description() + f"It has a {self.brand} smart refrigerator with capacity of {self.capacity} litters.\n"
  
  def accept(self, visitor):
    visitor.visit_smart_refrigerator(self)

class SmartOven(SmartKitchenDecorator,SmartAppliance):
  def __init__(self, brand, dimension, wifi_connected, voice_assistant_enable, smart_kitchen):
    super().__init__(smart_kitchen)
    self.brand = brand
    self.dimension = dimension #width x length X height (inches)
    self.wifi_connected = wifi_connected
    self.voice_assistance_enable = voice_assistant_enable
  

  def get_smart_kitchen_description(self):
    return super().get_smart_kitchen_description() + f"It has a {self.brand} smart oven with size of of {self.dimension} inches.\n"
  
  def accept(self, visitor):
    visitor.visit_smart_oven(self)


class SmartCoffeeMaker(SmartKitchenDecorator,SmartAppliance):
  def __init__(self, brand, water_capacity_liters,bean_capacity_grams, wifi_connected, voice_assistant_enable, smart_kitchen):
    super().__init__(smart_kitchen)
    self.brand = brand
    self.water_capacity_liters = max(water_capacity_liters, 1)
    self.bean_capacity_grams = max(bean_capacity_grams, 200)
    self.wifi_connected = wifi_connected
    self.voice_assistance_enable = voice_assistant_enable
  
  def set_water_capacity(self, l):
    if(l < 1):
      l = 1
    self.water_capacity_liters = l
  
  def set_bean_capacity(self, g):
    if(g < 200):
      g = 200
    self.bean_capacity_grams = g

  def get_smart_kitchen_description(self):
    return super().get_smart_kitchen_description() + f"It has a {self.brand} smart coffee maker with water capacity of {self.water_capacity_liters} litters and bean capacity of {self.bean_capacity_grams} grams.\n"
  
  def auto_brew(self):
    print("[SmartCoffeeMaker] Brew a cup of coffee.")

  def custom_brew(self, water_temp_cel, brew_strength, size_of_cup_ml):
    print(f"[SmartCoffeeMaker] Brewing {size_of_cup_ml} ml of coffee at {water_temp_cel} degree Celsuis with {brew_strength} strength.")

  def set_brewing_time(self, time):
    print(f"[SmartCoffeeMaker] Schedual brewing a cup of coffee at {time}.")
  
  def accept(self, visitor):
    visitor.visit_smart_coffee_maker(self)

#=========================
#         VISITOR
#=========================

class Visitor(ABC):
  @abstractmethod
  def visit_smart_refrigerator(self, r):
    pass

  @abstractmethod
  def visit_smart_oven(self, o):
    pass

  @abstractmethod
  def visit_smart_coffee_maker(self, c):
    pass
    
class CleaningVisitor(Visitor):
  def visit_smart_refrigerator(self, r):
    print(f"Clean the surface and internal parts of {r.brand} refrigerator to maintain hygience and prevent build up or damange.")

  def visit_smart_oven(self, o):
    print(f"Clean the surface and internal parts of {o.brand} oven to maintain hygience and prevent build up or damange.")

  def visit_smart_coffee_maker(self, c):
    print(f"Clean the surface and internal parts of {c.brand} coffee maker to maintain hygience and prevent build up or damange.")

class ComponentCheckUpVisitor(Visitor):
  def visit_smart_refrigerator(self, r):
    print(f"Check cooling parts, sensors, seals, and connectivity of the {r.brand} refrigerator for efficient operation.")
  
  def visit_smart_oven(self, o):
    print(f"Inspect heating parts, sensors, and smart controls of the {o.brand}  oven for safe, consistent cooking.")

  def visit_smart_coffee_maker(self, c):
    print(f"Examine water, heating, and smart systems of the {c.brand} for proper brewing performance.")

#=========================
#         COMMAND
#=========================

class Command(ABC):
  @abstractmethod
  def execute(self):
    pass

class AutoBrew(Command):
  def __init__(self, smart_coffee_maker):
    self.smart_coffee_maker = smart_coffee_maker

  def execute(self):
    self.smart_coffee_maker.auto_brew()

class CustonBrew(Command):
  def __init__(self,smart_coffee_maker, water_temp_cel, brew_strength, size_of_cup_ml):
    self.smart_coffee_maker = smart_coffee_maker
    self.water_temp_cel = water_temp_cel
    self.brew_strength = brew_strength
    self.size_of_cup_ml = size_of_cup_ml

  def execute(self):
    self.smart_coffee_maker.custom_brew(self.water_temp_cel, self.brew_strength, self.size_of_cup_ml)

class SetBrewingTime(Command):
  def __init__(self, smart_coffee_maker, time):
    self.smart_coffee_maker = smart_coffee_maker
    self.time = time

  def execute(self):
    self.smart_coffee_maker.set_brewing_time(self.time)

class MobileAppInvoker:
  def __init__(self):
    self.command = None

  def set_command(self, command):
    self.command = command

  def execute_command(self):
    self.command.execute()

class VoiceInvoker:
  def __init__(self):
    self.command = None

  def set_command(self, command):
    self.command = command

  def execute_command(self):
    self.command.execute()

# =============================
#           CLIENT CODE
# =============================
def decorator_client(smart_kitchen):
  print("\n----------View Smart Kitchen Details----------")
  print(smart_kitchen.get_smart_kitchen_description())

def visitor_client(smart_kitchen_appliance, visitor, msg):
  print("\n----------Perform " + msg + " service for smart kitchen appliances----------")
  for smart_appliance in smart_kitchen_appliance:
    smart_appliance.accept(visitor)

def main():
  #Decorator Pattern
  kitchen = Kitchen(7,8,6)
  refigerator_kitchen = SmartRefrigerator("\"SAMSUNG\"", 615,True, True, kitchen)
  oven_refri_kitchen = SmartOven("\"Breville Smart Oven\"", "15x18x11", True, True, refigerator_kitchen)
  coffee_maker1_oven_refri_kitchen = SmartCoffeeMaker("\"Keurig\"", 1, 300,True, True, oven_refri_kitchen)
  coffee_maker2_coffee_maker1_oven_refri_kitchen = SmartCoffeeMaker("\"OXO\"", 1.15,400,True,True,coffee_maker1_oven_refri_kitchen)

  decorator_client(coffee_maker2_coffee_maker1_oven_refri_kitchen)

  #Visitor Pattern
  smart_kitchen_appliance = []
  smart_kitchen_appliance.append(refigerator_kitchen)
  smart_kitchen_appliance.append(oven_refri_kitchen)
  smart_kitchen_appliance.append(coffee_maker1_oven_refri_kitchen)
  smart_kitchen_appliance.append(coffee_maker2_coffee_maker1_oven_refri_kitchen)

  cleaning_visitor = CleaningVisitor()
  visitor_client(smart_kitchen_appliance, cleaning_visitor, "cleaning")

  component_checkup_visitor = ComponentCheckUpVisitor()
  visitor_client(smart_kitchen_appliance, component_checkup_visitor,"component check up")

  #Command Pattern
  print("\n---------Operate Smart Coffee Maker---------")
  mobile_app_invoker = MobileAppInvoker()
  auto_brew = AutoBrew(coffee_maker2_coffee_maker1_oven_refri_kitchen)
  mobile_app_invoker.set_command(auto_brew)
  mobile_app_invoker.execute_command()

  set_brewing_time = SetBrewingTime(coffee_maker2_coffee_maker1_oven_refri_kitchen, "7:00am")
  mobile_app_invoker.set_command(set_brewing_time)
  mobile_app_invoker.execute_command()

  voice_invoker = VoiceInvoker()
  custom_brew = CustonBrew(coffee_maker1_oven_refri_kitchen, 90,"strong", 500)
  voice_invoker.set_command(custom_brew)
  voice_invoker.execute_command()
  
if __name__ == "__main__":
  main()
    
  