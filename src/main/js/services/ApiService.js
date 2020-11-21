import axios from 'axios';

const CAR_API_BASE_URL = 'http://localhost:8080/cars';
const EMPLOYEE_BASE_URL = 'http://localhost:8080/employee';

class ApiService {

    fetchCars() {
        return axios.get(CAR_API_BASE_URL);
    }

    fetchCarById(carId) {
        return axios.get(CAR_API_BASE_URL + '/' + carId);
    }

    deleteCar(carId) {
        return axios.delete(CAR_API_BASE_URL + '/' + carId);
    }

    addCar(car) {
        return axios.post(""+CAR_API_BASE_URL, car);
    }

    editCar(car) {
        return axios.put(CAR_API_BASE_URL + '/' + car.id, car);
    }

    addEmployee(employee) {
        return axios.post(""+EMPLOYEE_BASE_URL,employee);
    }
}

export default new ApiService();