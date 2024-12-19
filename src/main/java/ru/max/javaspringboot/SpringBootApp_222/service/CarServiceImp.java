package ru.max.javaspringboot.SpringBootApp_222.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.max.javaspringboot.SpringBootApp_222.dao.CarDao;
import ru.max.javaspringboot.SpringBootApp_222.model.Car;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    private final CarDao carDao;

    @Autowired
    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> getCars(int count, String sortBy) {
        List<Car> cars = carDao.getCars(sortBy);
        return cars.subList(0, Math.min(count, cars.size()));
    }
}
