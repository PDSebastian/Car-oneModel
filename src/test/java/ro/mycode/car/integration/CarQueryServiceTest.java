package ro.mycode.car.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.model.Car;
import ro.mycode.car.repository.CarRepository;
import ro.mycode.car.service.CarQueryService;




import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CarQueryServiceTest {

    @Autowired
    private CarQueryService carQueryService;

    @Autowired
    private CarRepository carRepository;

    @Test
    void testFindCarById() {
        Car car= Car.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2018)
                .build();

      Car savedCar=  carRepository.save(car);

     CarResponse carResponse =carQueryService.getCarById(savedCar.getId());

        assertEquals("dwdwdw","www",carResponse.getMarca());
        assertEquals("aaa","aaa",carResponse.getModel());

    }

    @Test
    void testFindCarByModelAndMarca(){
        Car car=Car.builder()
                .marca("www")
                .model("aaa")
                .color("red")
                .year(2018)
                .build();

        Car savdCar= carRepository.save(car);
        CarResponse carResponse =carQueryService.
                findByModelandMarca(savdCar.getModel(),savdCar.getMarca());


        assertEquals("dwdwdw","www",carResponse.getMarca()  );
         assertEquals("aaa","aaa",carResponse.getModel());


    }
}
