package ro.mycode.car.service.commandService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.exceptions.CarAlreadyExistsException;
import ro.mycode.car.exceptions.CarNotFoundExceptiom;
import ro.mycode.car.mapper.CarMapper;
import ro.mycode.car.model.Car;
import ro.mycode.car.repository.CarRepository;

@Component
public class CarCommandServicempl implements CarCommandService {
    CarRepository carRepository;
    CarMapper carMapper;
    public CarCommandServicempl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    @Override
    @Transactional
    public CarResponse addCar( CarRequest carRequest) {
        Car car = carMapper.toEntity(carRequest);
        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);

    }

    @Override
    @Transactional
    public CarResponse updateCar(long carId, CarRequest carRequest) {
        Car car = carRepository.findById((carId)).orElseThrow(() -> new CarNotFoundExceptiom("Masina exista deja"));

        car.setMarca(carRequest.getMarca());
        car.setModel(carRequest.getModel());
        car.setYear(carRequest.getYear());
        car.setColor(carRequest.getColor());

        Car updatedCar = carRepository.save(car);
        return carMapper.toDto(updatedCar);



    }

    @Override
    @Transactional
    public void deleteCar(Long carId) {
        if(!carRepository.existsById(carId)) {
            throw new CarNotFoundExceptiom("Masina exista deja");
        }
        carRepository.deleteById(carId);

    }
}
