package ro.mycode.car.unitTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.model.Car;
import ro.mycode.car.repository.CarRepository;
import ro.mycode.car.service.CarQueryServiceImpl;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QueryServiceImplTests {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarQueryServiceImpl carQueryServiceImpl;

    @Test
    public void testFindAllCarsReturnOk(){
        Car car = Car.builder()
                .marca("A")
                .model("B")
                .year(2000)
                .color("Rosu")
                .build();

        Car car1= Car.builder()
                .marca("C")
                .model("D")
                .year(2020)
                .color("albastru")
                .build();
        List<Car> carList =List.of(car,car1);
        when(carRepository.findAll()).thenReturn(carList);

     List<CarResponse> carResponseList = carQueryServiceImpl.findAllCars();
        assertEquals(2, carResponseList.size());
        assertEquals("A",carResponseList.get(0).getMarca());
        assertEquals("C", carResponseList.get(1).getMarca());

    }
    @Test
    public void testFindCarByIdReturnsOk(){
        Long carId = 1L;
        String marca = "A";
        String model = "B";
        String color = "Rosu";
        Integer year = 2000;

        Car car = Car.builder()
                .id(carId)
                .marca(marca)
                .model(model)
                .year(year)
                .color(color)
                .build();
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        CarResponse carResponse=carQueryServiceImpl.getCarById(car.getId());

        assertEquals("A",carResponse.getMarca());
        assertEquals("B",carResponse.getModel());
        assertEquals(2000,carResponse.getYear());
        assertEquals("Rosu",carResponse.getColor());

    }


    @Test
    public void testFindCarByModelAndMarcaReturnsOk(){
        String marca = "A";
        String model = "B";
        String color = "Rosu";
        Integer year = 2000;

        Car car = Car.builder()
                .marca(marca)
                .model(model)
                .year(year)
                .color(color)
                .build();

        when(carRepository.findByModelAndMarca(model,marca)).thenReturn(List.of(car));
       CarResponse carResponse =carQueryServiceImpl.findByModelandMarca(model,marca);

       assertEquals(model,carResponse.getModel());
       assertEquals(marca,carResponse.getMarca());
       assertEquals(year,carResponse.getYear());
       assertEquals(color,carResponse.getColor());



    }

}
