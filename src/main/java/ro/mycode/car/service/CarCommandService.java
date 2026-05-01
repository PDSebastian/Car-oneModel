package ro.mycode.car.service;

import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;

public interface CarCommandService {
    CarResponse addCar( CarRequest carRequest);
    CarResponse updateCar(Long cardId,CarRequest carRequest);
    CarResponse updatePatchCar(Long carId,CarRequest carRequest);
    void deleteCar(Long carId);


}
