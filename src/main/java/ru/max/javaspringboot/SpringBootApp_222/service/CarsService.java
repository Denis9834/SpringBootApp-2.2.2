package ru.max.javaspringboot.SpringBootApp_222.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.max.javaspringboot.SpringBootApp_222.model.Car;
import ru.max.javaspringboot.SpringBootApp_222.repositories.CarRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarsService {

    private final CarRepository carRepository;

    @Autowired
    public CarsService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Value("${app.maxCars}")
    private int maxCars;

    @Value("${app.sortByField}")
    private List<String> sortByField;

    public List<Car> getCars(int count, String sortBy) {
        if (sortBy != null && !sortByField.contains(sortBy)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Sort sort = (sortBy != null) ? Sort.by(sortBy) : Sort.unsorted();
        List<Car> cars = carRepository.findAll(sort);
        return cars.subList(0, Math.min(count, cars.size()));
    }

    public int getMaxCars() {
        return maxCars;
    }
}
