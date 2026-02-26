package ro.mycode.car.service.queryService;

import ro.mycode.car.dtos.CarResponse;

import java.util.List;
import java.util.Optional;

public interface CarQueryService {
    List<CarResponse> findAllCars();
    Optional<CarResponse> findById(Long id);
    Optional<CarResponse> findByModelandMarca(String model,String marca);
}
