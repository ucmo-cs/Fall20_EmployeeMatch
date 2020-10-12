package edu.ucmo.spring_example.controller;

import edu.ucmo.spring_example.model.Car;
import edu.ucmo.spring_example.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarDao carDao;

    @PostMapping
    public Car saveCar(@RequestBody Car car){
        Car newCar = new Car(car.getMake(), car.getModel(), car.getYear());
        return carDao.save(newCar);
    }

    @GetMapping
    public List<Car> listCars(){
        List<Car> list = new ArrayList<>();
        carDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public Car getOne(@PathVariable int id){
        Optional<Car> optionalCar = carDao.findById(id);
        return optionalCar.isPresent() ? optionalCar.get() : null;
    }

    @GetMapping("/make/{make}")
    public List<Car> getMake(@PathVariable String make){
        return carDao.findByMake(make);
    }

    @PutMapping("/{id}")
    public Car update(@RequestBody Car carUpdate) {
        Optional<Car> optionalCar = carDao.findById(carUpdate.getId());
        if (optionalCar.isPresent()) {
            carDao.save(carUpdate);
        }
        return carUpdate;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        carDao.deleteById(id);
     }
}
