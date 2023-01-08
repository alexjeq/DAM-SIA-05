import axios from 'axios';

const RENTS_API_BASE_URL = "http://localhost:8080/scrum/data/rents";

class RentsService {

    getRents() {
        return axios.get(RENTS_API_BASE_URL);
    }

    getRentById(rentId) {
        return axios.get(RENTS_API_BASE_URL + '/' + rentId);
    }

}

export default new RentsService()