package carwash;

import car.Car;
import car.CarOwner;
import exceptions.BlackListedException;
import exceptions.NegativeBalanceException;

import java.util.ArrayList;
import java.util.List;

public class CarWashService {
    private int totalWashes = 0;
    private double moneyEarned = 0.0;
    private final List<CarOwner> blackList = new ArrayList<>();

    public void setWashStrategy(WashStrategy washStrategy) {
        this.washStrategy = washStrategy;
    }

    private WashStrategy washStrategy;

    public int getTotalWashes() {
        return totalWashes;
    }

    public void setTotalWashes(int totalWashes) {
        this.totalWashes = totalWashes;
    }

    public double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public boolean wash(Car car, CarOwner carOwner) {
        if (blackList.contains(carOwner)) {
            throw new BlackListedException("This client is in black list");
        }
        if ((carOwner.getBalance() <= 0) || ((carOwner.getBalance() - washStrategy.getPrice(true)) < 0)) {
            throw new NegativeBalanceException("Balance of this client is negative");
        }
        if (washStrategy == null) {
            return false;
        }
        washStrategy.wash(car, carOwner);
        totalWashes++;
        moneyEarned = moneyEarned + washStrategy.getPrice(true);
        return true;

    }

    public boolean dry(Car car, CarOwner carOwner) {
        if (isClientBlackListed(carOwner)) {
            throw new BlackListedException("This client is in black list");
        }
        if ((carOwner.getBalance() <= 0) && (carOwner.getBalance() - washStrategy.getPrice(false)) < 0) {
            throw new NegativeBalanceException("Balance of this client is negative");
        }
        washStrategy.washAndDry(car, carOwner);
        totalWashes++;
        moneyEarned = moneyEarned + washStrategy.getPrice(false);
        return true;

    }

    public boolean isClientBlackListed(CarOwner carOwner) {
        return blackList.contains(carOwner);

    }

    public void addClientToBlackListed(CarOwner carOwner) {
        blackList.add(carOwner);
    }


}
