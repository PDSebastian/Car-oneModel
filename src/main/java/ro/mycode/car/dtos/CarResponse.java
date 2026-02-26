package ro.mycode.car.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarResponse {
    private long id;
    private String model;
    private String marca;
    private String color;
    private int year;
}
