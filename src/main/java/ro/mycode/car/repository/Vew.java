package ro.mycode.car.repository;

import org.springframework.stereotype.Component;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.mapper.CarMapper;
import ro.mycode.car.model.Car;
import ro.mycode.car.service.commandService.CarCommandService;
import ro.mycode.car.service.queryService.CarQueryService;


public class Vew {
    CarRepository carRepository;
    CarMapper carMapper;
    CarCommandService carCommandService;
    CarQueryService carQueryService;
    public Vew(CarRepository carRepository, CarMapper carMapper, CarCommandService carCommandService,CarQueryService carQueryService) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.carCommandService = carCommandService;
        this.carQueryService = carQueryService;


    }
    void testAddCar() {
        CarRequest carRequest = CarRequest.builder()
                .marca("Dacia")
                .model("Jogger")
                .color("Maro")
                .year(2024)
                .build();
//        CarResponse response = carCommandService.addCar(100L, carRequest);

    }
    void testUpdateCar() {
        CarRequest updateReq = CarRequest.builder()
                .marca("Audi")
                .model("RS7")
                .color("Verde")
                .year(2024)
                .build();
        CarResponse updatedCar = carCommandService.updateCar(1, updateReq);

    }
    void  testDeleteCar(Long carId) {
        if (carRepository.existsById(carId)) {
            carRepository.deleteById(carId);
        }
        else{
            System.out.println("Car not found");
        }

    }








}
