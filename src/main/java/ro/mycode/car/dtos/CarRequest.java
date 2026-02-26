package ro.mycode.car.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequest {
    @NotBlank(message = "modelul este obligatoriu")
    @Size(min=1 , max=50)
    private String model;

    @NotBlank(message = "marca este obligatorie")
    @Size(min=1, max=50)
    private String marca;

    @NotBlank(message = "color este obligatorie")
    @Size(min=1,max=50)
    private String color;

    @Positive(message ="1999")
    private int year;


}
