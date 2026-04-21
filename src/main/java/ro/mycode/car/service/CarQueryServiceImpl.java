package ro.mycode.car.service;

import org.springframework.stereotype.Component;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.exceptions.CarNotFoundException;
import ro.mycode.car.mapper.CarMapper;
import ro.mycode.car.model.Car;
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
    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()->new CarNotFoundException());
        return CarMapper.toDto(car);
    }

    @Override
    public Optional<CarResponse> findById(Long id) {
        return carRepository.findById(id).map(CarMapper::toDto);
    }

    @Override
    public List<CarResponse> findByModelandMarca(String model, String marca) {
        return carRepository.findByModelAndMarca(model, marca).stream().map(CarMapper::toDto).toList();
    }
}
