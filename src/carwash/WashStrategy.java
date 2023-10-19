package carwash;

import car.Car;
import car.CarOwner;

public interface WashStrategy {

    void wash(Car car, CarOwner carOwner);

    int getPrice(boolean isWashOrDry);

    void WashAndDry(Car car, CarOwner carOwner);

}
