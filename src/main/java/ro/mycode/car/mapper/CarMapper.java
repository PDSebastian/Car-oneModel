package ro.mycode.car.mapper;

import org.springframework.stereotype.Component;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.model.Car;

@Component
public class CarMapper {
    public Car toEntity(CarRequest req) {
        if(req == null){
            return null;
        }
        return Car.builder()
                .marca(req.getMarca())
                .model(req.getModel())
                .year(req.getYear())
                .color(req.getColor())
                .build();
    }
    public CarResponse toDto(Car car) {
        return new CarResponse(
                 car.getId(),
                car.getMarca(),
                car.getModel(),
                car.getColor(),
                car.getYear()
        );
    }
}
