package ro.mycode.car.service.queryService;

import org.springframework.stereotype.Component;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.mapper.CarMapper;
import ro.mycode.car.repository.CarRepository;
import ro.mycode.car.service.commandService.CarCommandService;

import java.util.List;
import java.util.Optional;

@Component
public class CarQueryServiceImpl implements CarQueryService {
    CarRepository carRepository;
    CarMapper carMapper;
    CarQueryServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    @Override
    public List<CarResponse> findAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto).toList();
    }

    @Override
    public Optional<CarResponse> findById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::toDto);
    }

    @Override
    public Optional<CarResponse> findByModelandMarca(String model, String marca) {
        return carRepository.findByModelAndMarca(model, marca)
                .map(carMapper::toDto);
    }
}
