import React, { Component } from 'react';
import Select from "react-select";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import CarsService from '../../services/CarsService';

class AddCar extends Component {
    constructor(props) {
        super(props)

        this.state = {
            model: "",
            fab_year: "",
            cil_capacity: "",
            fuel_type: null,
            register_date: null,
            color: "",
            seats_nr: "",
            rent_cost: "",
            description: "",
        }
    }

    inputChangeHandler = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        })
    }

    addCar = () => {
        const payload = {
            model: this.state.model,
            anFabricatie: this.state.fab_year,
            capacitateCilindrica: this.state.cil_capacity,
            combustibil: this.state.fuel_type.label.toUpperCase(),
            dataInmatriculare: new Date(this.state.register_date),
            culoare: this.state.color,
            descriere: this.state.description,
            nrPasageri: this.state.seats_nr,
            costInchiriere: this.state.rent_cost,
            status: "0",
        }
        CarsService.createCar(payload).then(res => {
            if (res.data.idAutovehicul > 0) {
                this.props.history.push("/cars/cars-list");
            }
        }).catch(e => {
            console.log(e);
        })
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>Add Car</h1>
                    <div className="row">
                        <div className="card col-md-6">
                            <div className="card-body">
                                <form onSubmit={(e) => e.preventDefault()}>
                                    <div className="form-group">
                                        <label>Model</label>
                                        <input
                                            placeholder="Model"
                                            name="model"
                                            className="form-control"
                                            value={this.state.model}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Fabrication year</label>
                                        <input
                                            placeholder="Fabrication year"
                                            name="fab_year"
                                            className="form-control"
                                            value={this.state.fab_year}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Cilindrical capacity</label>
                                        <input
                                            placeholder="Cilindrical capacity"
                                            name="cil_capacity"
                                            className="form-control"
                                            value={this.state.cil_capacity}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Fuel type</label>
                                        <Select
                                            options={fuelTypes}
                                            placeholder={"Fuel type"}
                                            value={this.state.fuel_type}
                                            onChange={(e) => this.setState({
                                                fuel_type: e
                                            })}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Registration date</label>
                                        <DatePicker
                                            selected={this.state.register_date}
                                            dateFormat="dd/MM/yyyy"
                                            onChange={(date) => this.setState({
                                                register_date: date
                                            })}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Color</label>
                                        <input
                                            placeholder="Color"
                                            name="color"
                                            className="form-control"
                                            value={this.state.color}
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Seats number</label>
                                        <input
                                            placeholder="Seats number"
                                            name="seats_nr"
                                            className="form-control"
                                            value={this.state.seats_nr}
                                            type="number"
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Rent cost (per day in $)</label>
                                        <input
                                            placeholder="Rent cost"
                                            name="rent_cost"
                                            className="form-control"
                                            value={this.state.rent_cost}
                                            type="number"
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Description</label>
                                        <textarea
                                            placeholder="Description"
                                            name="description"
                                            className="form-control"
                                            value={this.state.description}
                                            type="number"
                                            onChange={this.inputChangeHandler}
                                        />
                                    </div>
                                    <button
                                        className="btn btn-success"
                                        onClick={this.addCar}
                                    >Save</button>
                                    <button className="btn btn-danger"
                                        onClick={() => this.props.history.push("/cars/cars-list")}
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

const fuelTypes = [
    {
        value: 1,
        label: "Benzina",
    },
    {
        value: 2,
        label: "Motorina",
    },
    {
        value: 3,
        label: "Gaz",
    },
    {
        value: 4,
        label: "Electric",
    },
]

export default AddCar;
