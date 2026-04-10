package ro.mycode.car.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import org.junit.jupiter.api.extension.ExtendWith;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.exceptions.CarAlreadyExistsException;
import ro.mycode.car.exceptions.CarNotFoundException;
import ro.mycode.car.exceptions.InvalidYearException;
import ro.mycode.car.model.Car;
import ro.mycode.car.repository.CarRepository;
import ro.mycode.car.service.CarCommandService;
import ro.mycode.car.service.CarCommandServicempl;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandServiceImplTest {

    @Mock
    CarRepository carRepository;

    CarCommandService carCommandService;



   @BeforeEach
    void setUp() {
       carCommandService= new CarCommandServicempl(carRepository);

   }



   @Test
   void createThrowsWhenModelDuplicate(){//PRECONDITIE ACTIUNE REZULTAT
       CarRequest carRequest = CarRequest.builder()
               .model("Audi")
               .marca("a6")
               .color("Rosu")
               .year(2006)
               .build();
       Car  car = Car.builder()
               .id(1L)
               .model("Audi")
               .marca("a6")
               .color("Rosu")
               .year(2006)
               .build();
       when(carRepository.findFirstByModel(carRequest.getModel())).thenReturn(Optional.of(car));
       assertThrows(CarAlreadyExistsException.class,()->{carCommandService.addCar(carRequest);});
   }
   @Test
    void createCarReturnsCarResponseOk(){
       CarRequest carRequest=CarRequest.builder()
               .model("bmw")
               .marca("logan")
               .color("alba")
               .year(2006)
               .build();
       Car car=Car.builder()
               .model("bmw")
               .marca("logan")
               .color("alba")
               .year(2006)
               .build();
       Car savedCar=Car.builder()
               .model("bmw")
               .marca("logan")
               .color("alba")
               .year(2006)
               .id(1L)
               .build();
       CarResponse expectedResponse = CarResponse.builder()
               .model("bmw")
               .marca("logan")
               .color("alba")
               .year(2006)
               .id(1L)
               .build();
       when(carRepository.findFirstByModel(carRequest.getModel())).thenReturn(Optional.empty());
       when(carRepository.save(car)).thenReturn(savedCar);
       CarResponse result=carCommandService.addCar(carRequest);
       assertEquals(result,expectedResponse);

   }
   @Test
   void updateWhenCarNotFound() {
       CarRequest carRequest = CarRequest.builder()
               .marca("Dacia")
               .model("Logan")
               .color("rosu")
               .year(2006)
               .build();

       when(carRepository.findById(1l)).thenReturn(Optional.empty());

       assertThrows(CarNotFoundException.class, () -> {
           carCommandService.updateCar(1L, carRequest);
       });

   }
   @Test
    void deleteWhenCarNotFound() {
       when(carRepository.findById(1l)).thenReturn(Optional.empty());
       assertThrows(CarNotFoundException.class, () -> {
           carCommandService.deleteCar(1l);

       });

   }
   @Test
    void testWhenYearIsInvalid() {
       CarRequest carRequest=CarRequest.builder()
               .year(1900)
               .build();

       assertThrows(InvalidYearException.class, () -> {
           carCommandService.updateCar(1L, carRequest);
       });

   }
   @Test
    void testDeleteCar(){
       when(carRepository.findById(3L)).thenReturn(Optional.of(new Car()));
       carCommandService.deleteCar(3L);

   }
//   @Test
//   void updateWhenModelIsValid(){
//       CarRequest carRequest=CarRequest
//               .builder()
//               .marca("Dacia")
//               .model("1300")
//               .color("verde")
//               .year(2009)
//               .carId(5L)
//               .build();
//       Car updatedCar=Car.builder().model( ).color("rosu").year(2009).build();
//
//
//       when(carRepository.findById(carRequest.getCarId())).thenReturn(Optional.of(updatedCar));
//
//       assertThrows(InvalidModelException.class, () -> {
//           carCommandService.updateCar(carRequest.getCarId(), carRequest);
//       });
//
//
//   }
   @Test
    void testUpdateCar(){
       CarRequest carRequest=CarRequest.builder()
               .model("Audi")
               .marca("a4")
               .color("Rosu")
               .year(2021)
               .build();

       Car carfromDb= Car.builder()
               .model("Logan")
               .marca("dacia")
               .color("verde")
               .year(2020)
               .id(7L)
               .build();

       CarResponse expectedResult= CarResponse.builder().id(7L).model("Audi")
               .marca("a4")
               .color("Rosu")
               .year(2021)
               .build();
       when(carRepository.findById(7L)).thenReturn(Optional.of(carfromDb));
       when(carRepository.save(carfromDb)).thenReturn(carfromDb);
       CarResponse actualResult= carCommandService.updateCar(7L, carRequest);
       assertEquals(expectedResult,actualResult);




    }











}
