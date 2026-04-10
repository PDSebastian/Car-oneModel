package ro.mycode.car.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ro.mycode.car.dtos.CarRequest;
import ro.mycode.car.dtos.CarResponse;
import ro.mycode.car.model.Car;
import ro.mycode.car.service.CarCommandService;
import ro.mycode.car.service.CarQueryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CarQueryService  carQueryService;
    @MockitoBean
    private CarCommandService carCommandService;

    @Test
    void testGetAllCars () throws Exception {
        CarResponse car1 = CarResponse.builder()
                .id(1)
                .model("A4")
                .marca("Audi")
                .color("Negru")
                .year(2018)
                .build();

        CarResponse car2 = CarResponse.builder()
                .id(2)
                .model("C-Class")
                .marca("Mercedes")
                .color("Alb")
                .year(2020)
                .build();


      List<CarResponse> carList =List.of(car1,car2);

        when(carQueryService.findAllCars()).thenReturn(carList);

        mockMvc.perform(get("/api/v1/cars/all")).
                  andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].model").value("A4"));



    }
    @Test
    void testaddCar () throws Exception {
        CarResponse carResponse = CarResponse.builder()
                .id(1L)
                .model("A4")
                .marca("Audi")
                .color("Negru")
                .year(2018)
                .build();

        CarRequest carRequest=CarRequest.builder()
                .model("C-Class")
                .marca("Mercedes")
                .color("Alb")
                .year(2020)
                .build();

        when(carCommandService.addCar(carRequest)).thenReturn(carResponse);
        mockMvc.perform(post("/api/v1/cars/add")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequest)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Audi"))
                .andExpect(jsonPath("$.model").value("A4"))
                .andExpect(jsonPath("$.id").value(1L));


    }
    @Test
    void deleteCarNoContent() throws   Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/v1/cars/delete/{id}",id)).andExpect(status().isOk());
    }
    @Test
    void updateReturnsOkWithBody() throws Exception {
        Long id = 5L;
        CarResponse carResponse = CarResponse.builder()
                .id(id)
                .marca("BMW")
                .model("X5")
                .color("Negru")
                .year(2022)
                .build();

        CarRequest carRequest = CarRequest.builder()
                .marca("Audi")
                .model("A4")
                .color("Negru")
                .year(2021)
                .build();

        when(carCommandService.updateCar(eq(id), any(CarRequest.class))).thenReturn(carResponse);

                mockMvc.perform(put("/api/v1/cars/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.marca").value("BMW"))
                .andExpect(jsonPath("$.model").value("X5"));
    }

}
