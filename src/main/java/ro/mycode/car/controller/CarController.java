package ro.mycode.car.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.service.CarCommandService;
import ro.mycode.car.service.CarQueryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private CarQueryService carQueryService;
    private CarCommandService carCommandService;
    public CarController(CarQueryService carQueryService, CarCommandService carCommandService) {
        this.carQueryService = carQueryService;
        this.carCommandService = carCommandService;

    }


    @GetMapping("/all")

    ResponseEntity<List<CarResponse>> getAllCars(){
        return ResponseEntity.status(HttpStatus.OK).body(carQueryService.findAllCars());

    }
    @PostMapping("/add")
    ResponseEntity<CarResponse> addCar(@RequestBody CarRequest carRequest){
      CarResponse carResponse=  carCommandService.addCar(carRequest);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);

    }
    @DeleteMapping("/delete/{id}")
      ResponseEntity<Void> deleteCar(@PathVariable long id ){
      carCommandService.deleteCar(Long.parseLong(id+""));
      return ResponseEntity.status(HttpStatus.OK).body(null);

    }
    @PutMapping("/update/{id}")
    ResponseEntity<CarResponse> updateCar(@PathVariable long id , @Valid @RequestBody CarRequest carRequest){
        CarResponse c= carCommandService.updateCar(id, carRequest);
        return ResponseEntity.status(HttpStatus.OK).body(c);

    }
    @PatchMapping("/patch/{id}")
    ResponseEntity<CarResponse>  patchCar(@PathVariable long id , @Valid @RequestBody CarRequest carRequest){
        CarResponse carReponse=carCommandService.updateCar(id,carRequest);
        return ResponseEntity.status(HttpStatus.OK).body(carReponse);
    }
}
