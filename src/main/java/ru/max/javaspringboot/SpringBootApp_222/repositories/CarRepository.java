package ru.max.javaspringboot.SpringBootApp_222.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.max.javaspringboot.SpringBootApp_222.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
