import axios from 'axios';

const CARS_API_BASE_URL = "http://localhost:8080/scrum/data/cars";

class CarsService {

    getCars() {
        return axios.get(CARS_API_BASE_URL);
    }

    getCarById(carId) {
        return axios.get(CARS_API_BASE_URL + '/' + carId);
    }

    createCar(car) {
        return axios.post(CARS_API_BASE_URL, car);
    }

    updateCar(car, carId) {
        return axios.put(CARS_API_BASE_URL + '/' + carId, car);
    }

    deleteCar(carId) {
        return axios.delete(CARS_API_BASE_URL + '/' + carId);
    }
}

export default new CarsService()