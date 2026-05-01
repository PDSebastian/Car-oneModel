package ro.mycode.car.integration;

import org.junit.jupiter.api.Test;
import org.springdoc.core.customizers.ParameterObjectNamingStrategyCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.exceptions.CarAlreadyExistsException;
import ro.mycode.car.exceptions.CarNotFoundException;
import ro.mycode.car.exceptions.InvalidYearException;
import ro.mycode.car.model.Car;
import ro.mycode.car.repository.CarRepository;
import ro.mycode.car.service.CarCommandService;



import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CarCommandServiceTest {

    @Autowired
    private CarCommandService carCommandService;

    @Autowired
    CarRepository carRepository;


    @Test
    @Transactional
    public void createCarReturnOk() throws Exception {
        CarRequest carRequest =CarRequest.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2018)
                .build();

        CarResponse carResponse=carCommandService.addCar(carRequest);
        assertEquals("dwdw","www",carResponse.getMarca());
        assertEquals("dwdw","aaa",carResponse.getModel());




    }
    @Test
    @Transactional
    public void updateCarReturnOk() throws Exception {
        Car car= Car.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2018)
                .build();

        Car savedCar=carRepository.save(car);
        CarRequest carRequest=CarRequest.builder()
                .marca("wwwqsqsq")
                .model("aaasqsqs")
                .color("red")
                .year(2018)
                .build();

        CarResponse carResponse=carCommandService.addCar(carRequest);
        assertEquals("dwdw","wwwqsqsq",carResponse.getMarca());
        assertEquals("dwdw","aaasqsqs",carResponse.getModel());
        assertEquals("dwdw","red",carResponse.getColor());

    }
    @Test
    @Transactional
    public void deleteCarReturnsOk() throws Exception {
        Car car=Car.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2018)
                .build();
        Car savedCar=carRepository.save(car);
        carCommandService.deleteCar(savedCar.getId());


    }
    @Test
    @Transactional
    public void testAddCarAlreadyExists() throws Exception {
        CarRequest car=CarRequest.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2018)
                .build();

        CarRequest carRequest=CarRequest.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2018)
                .build();

        carCommandService.addCar(carRequest);

        assertThrows(CarAlreadyExistsException.class, ()->{
            carCommandService.addCar(carRequest);

        });

    }
    @Test
    @Transactional
    public void testUpdateCarThrowsInvalidYearException() throws Exception {
        Car car=Car.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(1997)
                .build();
       carRepository.save(car);
       CarRequest carRequest=CarRequest.builder()
               .marca("www")
               .model("aaa")
               .color("red")
               .year(1997)
               .build();

       assertThrows(InvalidYearException.class, ()->{
            carCommandService.updateCar(car.getId(), carRequest);
       });




    }
    @Test
    @Transactional
    public void testUpdateCarWhenCarNotFound() throws Exception {
        Long carId=99L;
        CarRequest carRequest=CarRequest.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2000)
                .build();
        assertThrows(CarNotFoundException.class, ()->{
            carCommandService.updateCar(carId, carRequest);
        });
    }








}
