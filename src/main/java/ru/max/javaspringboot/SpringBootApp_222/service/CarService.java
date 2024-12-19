package ru.max.javaspringboot.SpringBootApp_222.service;

import ru.max.javaspringboot.SpringBootApp_222.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getCars(int count, String sortBy);
}
