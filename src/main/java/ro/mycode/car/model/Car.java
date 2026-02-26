package ro.mycode.car.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "masini")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;


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
