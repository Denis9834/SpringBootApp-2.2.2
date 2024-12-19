package ru.max.javaspringboot.SpringBootApp_222.dao;

import ru.max.javaspringboot.SpringBootApp_222.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getCars(String sortBy);
}
