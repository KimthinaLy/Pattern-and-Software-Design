import java.util.List;
import java.util.ArrayList;

class UserAccount {
  private String id;
  private String username;

  public UserAccount(String id, String username) {
    this.id = id;
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }
}

class UserRegistry {
  private List<UserAccount> users = new ArrayList<UserAccount>();

  public UserAccount findUserById(String id) {
    for (UserAccount user : users) {
      if (user.getId().equals(id)) {
        return user;
      }
    }
    return null;
  }

  public void registerUser(UserAccount user) {
    users.add(user);
    System.out.println("User " + user.getId() + " registered to the game.");
  }
}

interface IDiamondStore {
  void purchaseDiamond(UserAccount user, int amount);
}

class DiamondStore implements IDiamondStore {
  private String name;
  private int totalDiamondsSold;

  public DiamondStore(String name) {
    this.name = name;
    totalDiamondsSold = 0;
  }

  @Override
  public void purchaseDiamond(UserAccount user, int amount) {
    if (amount < 50 || amount > 5000) {
      System.out.println("Purchase amount must be between 50 and 5,000 diamonds.");
    } else {
      double cost = amount * 1.5;
      totalDiamondsSold += amount;
      System.out.println("[" + name + " Store] Account: " + user.getUsername() +
          " purchased " + amount + " diamonds for " + cost + " Baht");
    }
  }

  public int getTotalDiamondsSold() {
    return totalDiamondsSold;
  }
}

class DiamondStoreProxy implements IDiamondStore {
  DiamondStore realStore;
  UserRegistry registry;

  public DiamondStoreProxy(DiamondStore realStore, UserRegistry registry) {
    this.realStore = realStore;
    this.registry = registry;
  }

  @Override
  public void purchaseDiamond(UserAccount user, int amount) {
    if (user != null && registry.findUserById(user.getId()) != null) {
      System.out.println("[DiamondStoreProxy] User is valid. Forwarding request to DiamondStore.");
      realStore.purchaseDiamond(user, amount);
    } else {
      System.out.println("[DiamondStoreProxy] Invalid user. Purchase denied.");
    }
  }

  public void purchaseDiamond(String id, int amount){
    UserAccount user = registry.findUserById(id);
    purchaseDiamond(user, amount);
  }
}

public class ProxyPatternDemo {
  public static void client(IDiamondStore diamondStore, UserAccount user, int amount){
    System.out.println("\nPurchasing diamond: ");
    diamondStore.purchaseDiamond(user, amount);
  }
  public static void main(String[] args) {
    UserAccount user1 = new UserAccount("001", "Supertank");
    UserAccount user2 = new UserAccount("002", "Ohlala");
    UserAccount user3 = new UserAccount("003", "SoloPlayer");

    UserRegistry registry = new UserRegistry();
    registry.registerUser(user1);
    registry.registerUser(user2);

    DiamondStore nikaStore = new DiamondStore("Nika");

    DiamondStoreProxy proxy = new DiamondStoreProxy(nikaStore, registry);

    client(nikaStore, user1, 100);
    client(proxy, user2, 1000);
    client(proxy, user3, 200);

    System.out.println("\nPurchasing diamond: ");
    proxy.purchaseDiamond("002", 350);

    System.out.println("\nTotal diamond solds: " + nikaStore.getTotalDiamondsSold());
  }
}
