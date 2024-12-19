package ru.max.javaspringboot.SpringBootApp_222.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.max.javaspringboot.SpringBootApp_222.model.Car;
import ru.max.javaspringboot.SpringBootApp_222.service.CarService;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    @Value("${app.maxCars}")
    private int maxCars;

    @Value("${app.sortByField}")
    private List<String> sortByField;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count,
                                  @RequestParam(value = "sortBy", required = false) String sortBy, Model model) {

        if (sortBy != null && !sortByField.contains(sortBy)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        int max = (count == null || count >= maxCars) ? maxCars : count;
        List<Car> cars = carService.getCars(max, sortBy);
        model.addAttribute("cars", cars);
        return "cars";
    }
}
