package ro.mycode.car.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.exceptions.CarAlreadyExistsException;
import ro.mycode.car.exceptions.CarNotFoundException;
import ro.mycode.car.exceptions.InvalidModelException;
import ro.mycode.car.exceptions.InvalidYearException;
import ro.mycode.car.mapper.CarMapper;
import ro.mycode.car.model.Car;
import ro.mycode.car.repository.CarRepository;

@Component
public class   CarCommandServicempl implements CarCommandService {
    CarRepository carRepository;
    public CarCommandServicempl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    @Transactional
    public CarResponse addCar( CarRequest carRequest) {
        carRepository.findFirstByModel(carRequest.getModel()).ifPresent((c)->{throw new CarAlreadyExistsException();});
        Car car = CarMapper.toEntity(carRequest);
        Car savedCar = carRepository.save(car);
        return CarMapper.toDto(savedCar);

    }



    @Override
    @Transactional
    public CarResponse updateCar(Long carId,CarRequest carRequest) {
        if(carRequest.getYear()<1999){
            throw new InvalidYearException();
        }

        Car car = carRepository.findById(carId)
                .orElseThrow(CarNotFoundException::new);
        car.setMarca(carRequest.getMarca());
        car.setModel(carRequest.getModel());
        car.setYear(carRequest.getYear());
        car.setColor(carRequest.getColor());
        Car updatedCar = carRepository.save(car);
        return CarMapper.toDto(updatedCar);
    }

    @Override
    @Transactional
    public void deleteCar(Long carId) {
        Car car=carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        carRepository.deleteById(carId);

    }

}
