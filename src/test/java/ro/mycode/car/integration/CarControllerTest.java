package ro.mycode.car.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.model.Car;
import ro.mycode.car.repository.CarRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
    }
    @Test
    void getAllCarsReturnsOk() throws Exception {
        String model = "Logan";
        String marca = "Dacia";
        String color = "White";
        int year = 2022;

        carRepository.save(Car.builder()
                .model(model)
                .marca(marca)
                .color(color)
                .year(year)
                .build());

        mockMvc.perform(get("/api/v1/cars/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].model").value(model));
    }
    @Test
    void addCarReturnsOk() throws Exception {
        String model = "Tesla";
        String marca = "Model 3";
        String color = "White";
        int year = 2023;

        CarRequest request = CarRequest.builder()
                .model(model)
                .marca(marca)
                .color(color)
                .year(year)
                .build();

        mockMvc.perform(post("/api/v1/cars/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value(model));
    }
    @Test
    void deleteCarReturnsOk() throws Exception {
        String model = "Audi";
        String marca = "A4";
        String color = "White";
        int year = 2020;

        Car savedCar = carRepository.save(Car.builder()
                .model(model)
                .marca(marca)
                .color(color)
                .year(year)
                .build());

        mockMvc.perform(delete("/api/v1/cars/delete/{id}", savedCar.getId()))
                .andExpect(status().isOk());


    }
    @Test
    void updateCarReturnsOk() throws Exception {
        String model = "Audi";
        String marca = "A4";
        String color = "White";
        int year = 2020;

        Car car = carRepository.save(Car.builder()
                .model(model)
                .marca(marca)
                .color(color)
                .year(year)
                .build());

        CarRequest updateRequest = CarRequest.builder()
                .model(model)
                .marca(marca)
                .color(color)
                .year(year)
                .build();

        mockMvc.perform(put("/api/v1/cars/update/{id}", car.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value(marca))
                .andExpect(jsonPath("$.year").value(year));
    }




}
