package ro.mycode.car.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.mycode.car.model.Car;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("select count(c) > 0 from Car c where c.model = :model and c.marca = :marca")
    boolean findCarByModelAndMarca(@Param("model") String model, @Param("marca") String marca);
    @Query("select c from Car c where c.model = :model and c.marca = :marca")

    Optional<Car> findByModelAndMarca(@Param("model") String model, @Param("marca") String marca);
    @Query("select c from Car c where c.year = :year")
    Optional<Car> findByYear(@Param("year") Integer year);

    @Query("select c from Car c where c.color = :color")
    Optional<Car> findByColor(@Param("color") Integer color);


    Optional<Car> findFirstByModel( String model);
    Optional<Car>findById(Long id);

    boolean getCarByYear(int year);
}
