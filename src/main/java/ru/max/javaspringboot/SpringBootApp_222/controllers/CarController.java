package ru.max.javaspringboot.SpringBootApp_222.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.max.javaspringboot.SpringBootApp_222.model.Car;
import ru.max.javaspringboot.SpringBootApp_222.repositories.CarRepository;
import ru.max.javaspringboot.SpringBootApp_222.service.CarsService;

import java.util.List;

@Controller
public class CarController {

    private final CarsService carsService;

    @Autowired
    public CarController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count,
                          @RequestParam(value = "sortBy", required = false) String sortBy,
                          Model model) {

        int maxCars = carsService.getMaxCars();
        int requestCount = (count == null || count >= maxCars) ? maxCars : count;
        List<Car> cars = carsService.getCars(requestCount, sortBy);
        model.addAttribute("cars", cars);
        return "cars";
    }
}
