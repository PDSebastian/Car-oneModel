package ro.mycode.car.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarUpdatePatchRequest {

    @NotBlank(message = "marca este obligatorie")
    @Size(min=1, max=50)
    private String marca;
}
