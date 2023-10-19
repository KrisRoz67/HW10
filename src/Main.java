import car.Car;
import car.CarOwner;
import carwash.*;

public class Main {
    public static void main(String[] args) {
        CheapWash cheapWash = new CheapWash();
        RegularWash regularWash = new RegularWash();
        PremiumWash premiumWash = new PremiumWash();
        CarWashService carWashService = new CarWashService(cheapWash);
        CarWashService carWashService2 = new CarWashService(new RegularWash());
        CarWashService carWashService3 = new CarWashService(new PremiumWash());
        Car car1 = new Car(123);
        CarOwner car1Owner = new CarOwner(145, "Oleg");
        Car car2 = new Car(20);
        CarOwner car2Owner = new CarOwner(100, "Vasja");
        Car car3 = new Car(90);
        CarOwner car3Owner = new CarOwner(100, "Petja");
        System.out.println("--------------");
        printbeforeInfo(car1, car1Owner);
        carWashService.wash(car1, car1Owner);
        printafterInfo(car1, car1Owner, cheapWash, true);
        System.out.println("--------------");
        printbeforeInfo(car2, car2Owner);
        carWashService.dry(car2, car2Owner);
        printafterInfo(car2, car2Owner, regularWash, false);
        System.out.println("--------------");
        printbeforeInfo(car3, car3Owner);
        carWashService.wash(car3, car3Owner);
        printafterInfo(car3, car3Owner, premiumWash, true);
        carWashService.addClientToBlackListed(car1Owner);
        System.out.println("--------------");
        carWashService.wash(car1, car1Owner);
    }

    public static void printbeforeInfo(Car car, CarOwner carOwner) {
        System.out.printf("""
                Client is %s .
                Client start balance : %s eur. Current car  dirtness before wash : %s
                """, carOwner.getName(), carOwner.getBalance(), car.getDirtness());
    }

    public static void printafterInfo(Car car, CarOwner carOwner, WashStrategy washStrategy, boolean isOnlywashOrDry) {
        System.out.println(isOnlywashOrDry ? "You ordered only wash " : "You ordered dry and wash");
        if (washStrategy instanceof CheapWash) {
            System.out.printf("You have choosen cheap service. The service price is " +
                    "%s eur.\n", washStrategy.getPrice(isOnlywashOrDry));
        } else if (washStrategy instanceof RegularWash) {
            System.out.printf("You have choosen regular service. The service price is " +
                    "%s eur.\n", washStrategy.getPrice(isOnlywashOrDry));
        } else {
            System.out.printf("You have choosen premium service. The service price is " +
                    "%s eur.\n", washStrategy.getPrice(isOnlywashOrDry));
        }
        System.out.printf("Client balance after wash  : %s eur. Current car " +
                "dirtness after wash : %s\n", carOwner.getBalance(), car.getDirtness());
    }
}