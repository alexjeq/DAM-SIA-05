import axios from 'axios';

const RENTS_API_BASE_URL = "http://localhost:8080/scrum/data/rents";
const RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW = "http://localhost:8080/scrum/data/workflow/registerCarRent";

class RentsService {

    getRents() {
        return axios.get(RENTS_API_BASE_URL + "/" + "?page=0&size=100");
    }

    getRentById(rentId) {
        return axios.get(RENTS_API_BASE_URL + '/' + rentId);
    }

    updateRent(rent, idCarRent) {
        return axios.put(RENTS_API_BASE_URL + '/' + idCarRent, rent);
    }

    deleteRent(idCarRent) {
        return axios.delete(RENTS_API_BASE_URL + '/' + idCarRent);
    }

    initiateRent(startDate) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "initiereInchiriere" + "/" + startDate);
    }

    addCustomer(idCarRent, customerName, customerPhoneNumber, customerEmail) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "adaugareClient" + "/" + idCarRent + "/" + customerName + "/" + customerPhoneNumber + "/" + customerEmail);
    }

    selectCustomer(idCarRent, idCustomer) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "alegereClient" + "/" + idCarRent + "/" + idCustomer);
    }

    selectCar(idCarRent, idCar) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "alegereAutovehicul" + "/" + idCarRent + "/" + idCar);
    }

    setEndDate(idCarRent, endDate) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "setareDataRetur" + "/" + idCarRent + "/" + endDate);
    }

    getCarRentSummary(idCarRent) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "generareInchiriereSumar" + "/" + idCarRent);
    }

    getCarsToDeleteFromSelect(idCarRent) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "getListaAutovehicul" + "/" + idCarRent);
    }

    returnCar(idCarRent, date) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "returnareAutovehicul" + "/" + idCarRent + "/" + date);
    }

    returnCarName(idCarRent) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "getAutovehiculName" + "/" + idCarRent);
    }

    returnCustomerName(idCarRent) {
        return axios.get(RENTS_API_BASE_URL_REGISTER_RENT_WORKFLOW + '/' + "getClientName" + "/" + idCarRent);
    }

}

export default new RentsService()