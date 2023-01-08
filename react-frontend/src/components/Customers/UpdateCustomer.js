import React, { Component } from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import CustomersService from '../../services/CustomersService';

class UpdateCustomer extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            name: "",
            phone_number: "",
            email: "",
            cnp: "",
            address: "",
            dob: null,
        }
    }

    componentDidMount() {
        CustomersService.getCustomerById(this.state.id).then(res => {
            this.setState({
                name: res.data.nume,
                phone_number: res.data.numarTelefon,
                email: res.data.email,
                cnp: res.data.cnp,
                address: res.data.adresa,
                dob: res.data.dataNasterii != null ? new Date(res.data.dataNasterii) : null,
            })
        }).catch(e => console.log(e))
    }

    inputChangeHandler = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        })
    }

    updateCustomer = () => {
        const payload = {
            nume: this.state.name,
            numarTelefon: this.state.phone_number,
            email: this.state.email,
            adresa: this.state.address,
            dataNasterii: new Date(this.state.dob),
            cnp: this.state.cnp,
        }
        CustomersService.updateCustomer(payload, this.state.id).then(res => {
            if (res.data.idClient > 0) {
                this.props.history.push("/customers/customers-list");
            }
        }).catch(e => console.log(e))
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>Update Customer</h1>
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
                                        onClick={this.updateCustomer}
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

export default UpdateCustomer;
