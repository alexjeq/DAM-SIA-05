import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import CustomersService from '../../services/CustomersService';
import { Store } from 'react-notifications-component';

class AddCustomer extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: "",
            phone_number: "",
            email: "",
            cnp: "",
            address: "",
            dob: null,
        }
    }

    inputChangeHandler = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        })
    }

    addCustomer = () => {
        const payload = {
            nume: this.state.name,
            numarTelefon: this.state.phone_number,
            email: this.state.email,
            adresa: this.state.address,
            dataNasterii: new Date(this.state.dob),
            cnp: this.state.cnp,
        }
        CustomersService.createCustomer(payload).then(res => {
            if (res.data.idClient > 0) {
                // Store.addNotification({
                //     title: "Customers",
                //     message: "Customer was successfuly added!",
                //     type: "success",
                //     insert: "top",
                //     container: "top-right",
                //     animationIn: ["animate__animated", "animate__fadeIn"],
                //     animationOut: ["animate__animated", "animate__fadeOut"],
                //     dismiss: {
                //         duration: 5000,
                //         onScreen: true
                //     }
                // });
                this.props.history.push("/customers/customers-list");
            }
        }).catch(e => {
            console.log(e);
            // Store.addNotification({
            //     title: "Customers",
            //     message: "Something went wrong!",
            //     type: "danger",
            //     insert: "top",
            //     container: "top-right",
            //     animationIn: ["animate__animated", "animate__fadeIn"],
            //     animationOut: ["animate__animated", "animate__fadeOut"],
            //     dismiss: {
            //         duration: 5000,
            //         onScreen: true
            //     }
            // });
        })
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>Add Customer</h1>
                    <div className="row">
                        <div className="card col-md-6">
                            <div className="card-body">
                                <form onSubmit={(e) => e.preventDefault()}>
                                    <div className="form-group">
                                        <label>Name</label>
                                        <input
                                            placeholder="Name"
                                            name="name"
                                            className="form-control"
                                            value={this.state.name}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Phone number</label>
                                        <input
                                            placeholder="Phone number"
                                            name="phone_number"
                                            className="form-control"
                                            value={this.state.phone_number}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Email</label>
                                        <input
                                            placeholder="Email"
                                            name="email"
                                            className="form-control"
                                            value={this.state.email}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>CNP</label>
                                        <input
                                            placeholder="CNP"
                                            name="cnp"
                                            className="form-control"
                                            value={this.state.cnp}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Adrress</label>
                                        <input
                                            placeholder="Adrress"
                                            name="address"
                                            className="form-control"
                                            value={this.state.address}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Date of birth</label>
                                        <DatePicker
                                            selected={this.state.dob}
                                            dateFormat="dd/MM/yyyy"
                                            onChange={(date) => this.setState({
                                                dob: date
                                            })}
                                        />
                                    </div>
                                    <button
                                        className="btn btn-success"
                                        onClick={this.addCustomer}
                                    >Save</button>
                                    <button className="btn btn-danger"
                                        onClick={() => this.props.history.push("/customers/customers-list")}
                                        style={{ marginLeft: "10px" }}>
                                        Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default AddCustomer;
