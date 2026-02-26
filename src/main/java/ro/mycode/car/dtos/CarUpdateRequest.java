package ro.mycode.car.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarUpdateRequest {
    @NotBlank(message = "modelul este obligatoriu")
    @Size(min=1 , max=50)
    private String model;



}
