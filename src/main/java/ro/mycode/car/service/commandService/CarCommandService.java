package ro.mycode.car.service.commandService;

import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;

public interface CarCommandService {
    CarResponse addCar( CarRequest carRequest);
    CarResponse updateCar(long carId, CarRequest carRequest);
    void deleteCar(Long carId);


}
