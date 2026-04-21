package ro.mycode.car.service;

import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarQueryService {
    List<CarResponse> findAllCars();
    CarResponse getCarById(Long id);
    Optional<CarResponse> findById(Long id);
    List<CarResponse> findByModelandMarca(String model,String marca);
}
