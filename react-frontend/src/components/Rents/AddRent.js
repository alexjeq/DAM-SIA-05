import React, { Component } from 'react';
import Select from "react-select";
import DatePicker from "react-datepicker";
import RentsService from '../../services/RentsService';
import moment from 'moment';
import CustomersService from '../../services/CustomersService';
import CarsService from '../../services/CarsService';

class AddRent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            step: 1,
            start_date: null,
            customers: [],
            customer: null,
            add_new_customer: false,
            new_customer_name: "",
            new_customer_phone: "",
            new_customer_email: "",
            cars: [],
            car: null,
            end_date: null,
            id_car_rent: "",

            rent_view_customer_name: "",
            rent_view_car_model: "",
            rent_view_car_date_start: "",
            rent_view_car_date_end: "",
            rent_view_car_rent_cost: "",
        }
    }

    componentDidMount() {
        CustomersService.getCustomers().then(res => {
            let customers = [];
            res.data._embedded.clients.forEach(element => {
                customers.push({
                    value: element.idClient,
                    label: element.nume,
                })
            });
            this.setState({
                customers: customers
            });
        });
    }

    getCars = () => {
        RentsService.getCarsToDeleteFromSelect(this.state.id_car_rent).then(res => {
            let carIdsToDelete = res.data.toString();
            let carsArray = carIdsToDelete.toString().split("/");
            CarsService.getCars().then(res => {
                let cars = [];
                res.data._embedded.cars.forEach(element => {
                    if (!carsArray.includes(element.idAutovehicul.toString())) {
                        cars.push({
                            value: element.idAutovehicul,
                            label: element.model,
                        })
                    }
                });
                this.setState({
                    cars: cars
                });
            });
        })
    }

    inputChangeHandler = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        })
    }

    getStepComponent = (step) => {
        switch (step) {
            case 1: return (
                <div className="form-group">
                    <label>Start date</label>
                    <DatePicker
                        selected={this.state.start_date}
                        dateFormat="dd/MM/yyyy"
                        onChange={(date) => this.setState({
                            start_date: date
                        })}
                    />
                </div>
            );
            case 2: return (
                <div className="form-group">
                    <label>End date</label>
                    <DatePicker
                        selected={this.state.end_date}
                        dateFormat="dd/MM/yyyy"
                        onChange={(date) => this.setState({
                            end_date: date
                        })}
                    />
                </div>
            );
            case 3: return (
                <div className="form-group">
                    <label>Car</label>
                    <Select
                        options={this.state.cars}
                        placeholder={"Select car"}
                        value={this.state.car}
                        onChange={(e) => this.setState({
                            car: e
                        })}
                    />
                </div>
            );
            case 4: return (
                <>
                    <div className="form-group">
                        <label>Customer</label>
                        <Select
                            options={this.state.customers}
                            placeholder={"Select customer"}
                            value={this.state.customer}
                            onChange={(e) => this.setState({
                                customer: e
                            })}
                            isDisabled={this.state.add_new_customer}
                        />
                    </div>
                    {!this.state.add_new_customer ?
                        <button
                            className="btn btn-primary"
                            onClick={() => this.setState({
                                add_new_customer: true,
                            })}
                        >Add new customer</button>
                        : <>
                            <div className="form-group">
                                <label>Add new customer</label>
                                <div className="form-group">
                                    <input
                                        placeholder="Name"
                                        name="new_customer_name"
                                        className="form-control"
                                        value={this.state.new_customer_name}
                                        onChange={this.inputChangeHandler}
                                    />
                                </div>
                                <div className="form-group">
                                    <input
                                        placeholder="Phone number"
                                        name="new_customer_phone"
                                        className="form-control"
                                        value={this.state.new_customer_phone}
                                        onChange={this.inputChangeHandler}
                                    />
                                </div>
                                <div className="form-group">
                                    <input
                                        placeholder="Email"
                                        name="new_customer_email"
                                        className="form-control"
                                        value={this.state.new_customer_email}
                                        onChange={this.inputChangeHandler}
                                    />
                                </div>
                                <button
                                    className="btn btn-danger"
                                    onClick={() => this.setState({
                                        add_new_customer: false,
                                    })}
                                >Cancel new customer</button>
                            </div>
                        </>
                    }
                </>
            );
            case 5: return (
                <div className="form-group">
                    <label>Car rent summary</label>
                    <div className="jeq-table">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>{"Parameter"}</th>
                                    <th>{"Value"}</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    viewParams.map(param =>
                                        <tr key={param.var}>
                                            <td>{param.name}</td>
                                            <td>{this.state[param.var]}</td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                    </div>
                </div>
            );
            default: return null;
        }
    }

    handleStep = (step) => {
        switch (step) {
            case 1:
                RentsService.initiateRent(moment(this.state.start_date).format("DD-MM-yyyy")).then(res => {
                    this.setState({
                        id_car_rent: res.data
                    });
                    this.goNext();
                });
                break;
            case 2:
                RentsService.setEndDate(this.state.id_car_rent, moment(this.state.end_date).format("DD-MM-yyyy")).then(res => {
                    if (res.data > 0) {
                        this.goNext();
                        this.getCars();
                    }
                });
                break;
            case 3:
                RentsService.selectCar(this.state.id_car_rent, this.state.car.value).then(res => {
                    if (res.data > 0) {
                        this.goNext();
                    }
                });
                break;
            case 4:
                if (this.state.add_new_customer) {
                    RentsService.addCustomer(this.state.id_car_rent, this.state.new_customer_name, this.state.new_customer_phone, this.state.new_customer_email).then(res => {
                        if (res.data > 0) {
                            this.goNext();
                            RentsService.getCarRentSummary(this.state.id_car_rent).then(res => {
                                this.setState({
                                    rent_view_customer_name: res.data.numeClient,
                                    rent_view_car_model: res.data.modelAutovehicul,
                                    rent_view_car_date_start: res.data.dataInchiriere != null ? moment(new Date(res.data.dataInchiriere)).format("DD/MM/YYYY") : "",
                                    rent_view_car_date_end: res.data.dataReturnare != null ? moment(new Date(res.data.dataReturnare)).format("DD/MM/YYYY") : "",
                                    rent_view_car_rent_cost: res.data.costInchiriere + " $",
                                })
                            })
                        }
                    })
                } else {
                    RentsService.selectCustomer(this.state.id_car_rent, this.state.customer?.value).then(res => {
                        if (res.data > 0) {
                            this.goNext();
                            RentsService.getCarRentSummary(this.state.id_car_rent).then(res => {
                                this.setState({
                                    rent_view_customer_name: res.data.numeClient,
                                    rent_view_car_model: res.data.modelAutovehicul,
                                    rent_view_car_date_start: res.data.dataInchiriere != null ? moment(new Date(res.data.dataInchiriere)).format("DD/MM/YYYY") : "",
                                    rent_view_car_date_end: res.data.dataReturnare != null ? moment(new Date(res.data.dataReturnare)).format("DD/MM/YYYY") : "",
                                    rent_view_car_rent_cost: res.data.costInchiriere + " $",
                                })
                            })
                        }
                    })
                }
                break;
            default: return null;
        }
    }

    goBack = () => {
        if (this.state.step == 2) {
            RentsService.deleteRent(this.state.id_car_rent);
        }
        this.setState({
            step: this.state.step - 1
        })
    }

    goNext = () => {
        this.setState({
            step: this.state.step + 1
        })
    }

    deleteRent = () => {
        if (this.state.id_car_rent != "") {
            RentsService.deleteRent(this.state.id_car_rent);
        }
        this.props.history.push("/rents/rents-list");
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>Add Rent</h1>
                    <div className="row">
                        <div className="card col-md-6">
                            <div className="card-body">
                                <form onSubmit={(e) => e.preventDefault()}>
                                    <div className="jeq-btns-back-next">
                                        <button
                                            className="btn btn-danger"
                                            onClick={this.goBack}
                                            disabled={this.state.step < 2}
                                        >Back</button>
                                        <button
                                            className="btn btn-success"
                                            onClick={() => this.handleStep(this.state.step)}
                                            style={{ marginLeft: "10px" }}
                                            disabled={this.state.step > 4}
                                        >
                                            Next
                                        </button>
                                    </div>
                                    {this.getStepComponent(this.state.step)}
                                    <div className='jeq-form-btns'>
                                        <button
                                            className="btn btn-success"
                                            onClick={() => this.props.history.push("/rents/rents-list")}
                                            disabled={this.state.step < 5}
                                        >Save</button>
                                        <button className="btn btn-danger"
                                            onClick={this.deleteRent}
                                            style={{ marginLeft: "10px" }}>
                                            Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default AddRent;

const viewParams = [
    {
        name: "Customer name",
        var: "rent_view_customer_name",
    },
    {
        name: "Car",
        var: "rent_view_car_model",
    },
    {
        name: "Date start",
        var: "rent_view_car_date_start",
    },
    {
        name: "Date end",
        var: "rent_view_car_date_end",
    },
    {
        name: "Total rent cost",
        var: "rent_view_car_rent_cost",
    },
]
