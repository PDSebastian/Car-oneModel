package ro.mycode.car.mapper;

import org.springframework.stereotype.Component;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.model.Car;


public class CarMapper {
    public static Car toEntity(CarRequest req) {
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
    public static CarResponse toDto(Car car) {
        return new CarResponse(
                 car.getId(),
                car.getModel(),
                car.getMarca(),
                car.getColor(),
                car.getYear()
        );
    }
}
