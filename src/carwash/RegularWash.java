package carwash;

import car.Car;
import car.CarOwner;

public class RegularWash implements WashStrategy {
    private  final int WASH_PRICE = 15 ;
    private  final int WASH_AND_DRY_PRICE = 17 ;
    private  final int  CLEAN_FACTOR = 70 ;

    public int getWASH_PRICE() {
        return WASH_PRICE;
    }

    public int getWASH_AND_DRY_PRICE() {
        return WASH_AND_DRY_PRICE;
    }

    public int getCLEAN_FACTOR() {
        return CLEAN_FACTOR;
    }

    @Override
    public void wash(Car car, CarOwner carOwner) {
        if (carOwner.getBalance()-WASH_PRICE >=0) {
            carOwner.setBalance(carOwner.getBalance() - WASH_PRICE);
        }else {
            throw new RuntimeException( "Balance is less than required");
        }
        int dirtness = car.getDirtness()-CLEAN_FACTOR ;
        car.setDirtness(Math.max(dirtness, 0));

    }

    @Override
    public void WashAndDry(Car car, CarOwner carOwner) {
        if (carOwner.getBalance()-WASH_PRICE >=0) {
            carOwner.setBalance(carOwner.getBalance() - WASH_AND_DRY_PRICE);
        }else {
            throw new RuntimeException( "Balance is less than required");
        }
        int dirtness = car.getDirtness()-CLEAN_FACTOR ;
        car.setDirtness(Math.max(dirtness, 0));

    }
    @Override
    public int getPrice(boolean isWashOrDry) {
        if(isWashOrDry) {
            return WASH_PRICE;
        }else {
            return  WASH_AND_DRY_PRICE;
        }
    }
}
