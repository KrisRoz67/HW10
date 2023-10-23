package carwash;

import car.Car;
import car.CarOwner;

public class PremiumWash implements WashStrategy {
    private final int WASH_PRICE = 25;
    private final int WASH_AND_DRY_PRICE = 30;
    private final int CLEAN_FACTOR = 100;

    public int getWashPrice() {
        return WASH_PRICE;
    }

    public int getWashAndDryPrice() {
        return WASH_AND_DRY_PRICE;
    }

    public int getCleanFactor() {
        return CLEAN_FACTOR;
    }

    @Override
    public void wash(Car car, CarOwner carOwner) {
        carOwner.setBalance(carOwner.getBalance() - WASH_PRICE);
        int dirtness = car.getDirtness() - CLEAN_FACTOR;
        car.setDirtness(Math.max(dirtness, 0));

    }

    @Override
    public void washAndDry(Car car, CarOwner carOwner) {

        int dirtness = car.getDirtness() - CLEAN_FACTOR;
        car.setDirtness(Math.max(dirtness, 0));

    }

    @Override
    public int getPrice(boolean isWashOrDry) {
        if (isWashOrDry) {
            return WASH_PRICE;
        } else {
            return WASH_AND_DRY_PRICE;
        }
    }
}
