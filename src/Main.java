import car.Car;
import car.CarOwner;
import carwash.*;

public class Main {
    public static void main(String[] args) {
        CheapWash cheapWash = new CheapWash();
        RegularWash regularWash = new RegularWash();
        PremiumWash premiumWash = new PremiumWash();
        CarWashService carWashService = new CarWashService();
        Car car1 = new Car(123);
        CarOwner car1Owner = new CarOwner(59, "Oleg");
        Car car2 = new Car(20);
        CarOwner car2Owner = new CarOwner(100, "Vasja");
        Car car3 = new Car(90);
        CarOwner car3Owner = new CarOwner(100, "Petja");
        System.out.println("--------------");
        printBeforeInfo(car1, car1Owner);
        carWashService.setWashStrategy(cheapWash);
        if (carWashService.wash(car1, car1Owner)) {
            printAfterInfo(car1, car1Owner, cheapWash, true);
        }
        System.out.println("--------------");
        if (carWashService.wash(car1, car1Owner)) {
            printAfterInfo(car1, car1Owner, cheapWash, true);
        }
        System.out.println("--------------");
        System.out.println("--------------");
        carWashService.setWashStrategy(regularWash);
        printBeforeInfo(car2, car2Owner);
        if (carWashService.dry(car2, car2Owner)) {
            printAfterInfo(car2, car2Owner, regularWash, false);
        }
        System.out.println("--------------");
        System.out.println("--------------");
        carWashService.setWashStrategy(premiumWash);
        printBeforeInfo(car3, car3Owner);
        if (carWashService.wash(car3, car3Owner)) {
            printAfterInfo(car3, car3Owner, premiumWash, true);
        }
        System.out.println("--------------");
        System.out.printf("\nOrdered services so far : %s.\nAnd earned money : %s eur\n",
                carWashService.getTotalWashes(), carWashService.getMoneyEarned());
        carWashService.addClientToBlackListed(car1Owner);
        System.out.println("--------------");
        System.out.println("--------------");
        if (carWashService.wash(car1, car1Owner)) {
            printAfterInfo(car1, car1Owner, cheapWash, true);
        }

    }

    public static void printBeforeInfo(Car car, CarOwner carOwner) {
        System.out.printf("Client start balance : %s eur. Current car  dirtness before wash : %s ",
                carOwner.getBalance(), car.getDirtness());
    }

    public static void printAfterInfo(Car car, CarOwner carOwner, WashStrategy washStrategy, boolean isOnlywashOrDry) {
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