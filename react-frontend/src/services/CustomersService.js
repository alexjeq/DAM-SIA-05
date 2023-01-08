import axios from 'axios';

const CUSTOMERS_API_BASE_URL = "http://localhost:8080/scrum/data/clients";

class CustomersService {

    getCustomers() {
        return axios.get(CUSTOMERS_API_BASE_URL);
    }

    getCustomerById(customerId) {
        return axios.get(CUSTOMERS_API_BASE_URL + '/' + customerId);
    }

    createCustomer(customer) {
        return axios.post(CUSTOMERS_API_BASE_URL, customer);
    }

    updateCustomer(customer, customerId) {
        return axios.put(CUSTOMERS_API_BASE_URL + '/' + customerId, customer);
    }

    deleteCustomer(customerId) {
        return axios.delete(CUSTOMERS_API_BASE_URL + '/' + customerId);
    }

}

export default new CustomersService()