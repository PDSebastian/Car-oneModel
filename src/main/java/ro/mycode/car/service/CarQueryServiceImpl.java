package ro.mycode.car.service;

import org.springframework.stereotype.Component;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.mapper.CarMapper;
import ro.mycode.car.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CarQueryServiceImpl implements CarQueryService {
    CarRepository carRepository;

    CarQueryServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<CarResponse> findAllCars() {
        return carRepository.findAll().stream().map(CarMapper::toDto).toList();
    }

    @Override
    public Optional<CarResponse> findById(Long id) {
        return carRepository.findById(id).map(CarMapper::toDto);
    }

    @Override
    public Optional<CarResponse> findByModelandMarca(String model, String marca) {
        return carRepository.findByModelAndMarca(model, marca).map(CarMapper::toDto);
    }
}
