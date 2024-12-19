package ru.max.javaspringboot.SpringBootApp_222.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.max.javaspringboot.SpringBootApp_222.model.Car;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    private final EntityManager entityManager;

    @Autowired
    public CarDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Car> getCars(String sortBy) {
        String from = "SELECT c FROM Car c";

        if (sortBy != null) {
            from += " ORDER BY c." + sortBy;
        }
        return entityManager.createQuery(from, Car.class).getResultList();
    }
}
