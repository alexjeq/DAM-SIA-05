import React, { Component } from 'react';
import CustomersService from '../../services/CustomersService';
// import "./../style/Header.scss";

class CustomersList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            customers: []
        }
    }

    componentDidMount() {
        this.getCustomersList();
    }

    getCustomersList = () => {
        CustomersService.getCustomers().then(res => this.setState({
            customers: res.data._embedded.clients
        }))
    }

    deleteCustomer = (id) => {
        CustomersService.deleteCustomer(id).then(res => {
            this.getCustomersList();
        })
    }

    render() {
        return (
            <div>
                <div className="jeq-page-header">
                    <h2>Customers list</h2>
                    <button
                        className="btn btn-primary"
                        onClick={() => this.props.history.push("/customers/add-customer")}
                    >
                        {"Add customer"}
                    </button>
                </div>
                <div className="jeq-table">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>{"ID"}</th>
                                <th>{"NAME"}</th>
                                <th>{"PHONE NUMBER"}</th>
                                <th>{"EMAIL"}</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.customers.map(customer =>
                                    <tr key={customer.idClient}>
                                        <td> {customer.idClient} </td>
                                        <td> {customer.nume} </td>
                                        <td> {customer.numarTelefon}</td>
                                        <td> {customer.email}</td>
                                        <td>
                                            <button
                                                onClick={() => this.props.history.push("/customers/update-customer/" + customer.idClient)}
                                                className="btn btn-info">Update</button>
                                            <button style={{ marginLeft: "10px" }}
                                                onClick={() => this.deleteCustomer(customer.idClient)}
                                                className="btn btn-danger"
                                            >Delete</button>
                                            <button style={{ marginLeft: "10px" }}
                                                onClick={() => this.props.history.push("/customers/view-customer/" + customer.idClient)}
                                                className="btn btn-success">View</button>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default CustomersList;
