package edu.ucmo.spring_example.controller;

import edu.ucmo.spring_example.dao.CarDao;
import edu.ucmo.spring_example.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CarControllerTest {
    @Autowired
    private CarController carController;

    @Autowired
    private CarDao carDao;

    @Autowired
    private JdbcTemplate template;

    @Test
    public void findOneThatDoesNotExist() {
        Car car = carController.getOne(99999);
        assertNull(car);
    }

    @Test
    public void findOneThatExists() {
        template.query("select id from car", (rs, num) -> rs.getInt("id"))
                .forEach(id -> {
                    Car car = carController.getOne(id);
                    assertNotNull(car);
                    assertEquals(id, car.getId());
                });
    }

    @Test
    public void findAll() {
        List<String> dbNames = StreamSupport.stream(carController.listCars().spliterator(), false)
                .map(Car::getMake)
                .collect(Collectors.toList());
        assertThat(dbNames, containsInAnyOrder("Ford", "Acura", "Honda"));
    }

    @Test
    public void testSaveDelete() {
        Car car = new Car("Rolls Royce", "Silver Shadow", 1965);
        long countBefore = carDao.count();
        car = carController.saveCar(car);
        long countAfter = carDao.count();
        assertNotNull(car.getId());
        assertEquals(countBefore + 1, countAfter);

        Car savedCar = carController.getOne(car.getId());
        assertEquals(car.getYear(), savedCar.getYear());
        assertTrue(car.getMake().equals(savedCar.getMake()));
        assertTrue(car.getModel().equals(savedCar.getModel()));

        carController.delete(car.getId());
        assertEquals(countBefore, carDao.count());
    }

    @Test
    public void testUpdate() {
        Car car = new Car("Rolls Royce", "Silver Ghost", 1906);
        car = carController.saveCar(car);
        assertNotNull(car.getId());

        car.setYear(1907);
        carController.update(car);

        Car savedCar = carController.getOne(car.getId());
        assertEquals(1907, savedCar.getYear());

        carController.delete(savedCar.getId());
    }

}
