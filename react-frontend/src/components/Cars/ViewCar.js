import React, { Component } from 'react';
import "react-datepicker/dist/react-datepicker.css";
import CarsService from '../../services/CarsService';
import moment from 'moment';

class ViewCar extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
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

    componentDidMount() {
        CarsService.getCarById(this.state.id).then(res => {
            this.setState({
                model: res.data.model,
                fab_year: res.data.anFabricatie,
                cil_capacity: res.data.capacitateCilindrica,
                fuel_type: res.data.combustibil,
                register_date: res.data.dataInmatriculare != null ? moment(new Date(res.data.dataInmatriculare)).format("DD/MM/YYYY") : null,
                color: res.data.culoare,
                description: res.data.descriere,
                seats_nr: res.data.nrPasageri,
                rent_cost: res.data.costInchiriere,
            })
        }).catch(e => console.log(e))
    }

    deleteCar = (id) => {
        CarsService.deleteCar(id).then(res => {
            this.props.history.push("/cars/cars-list");
        });
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>View Car</h1>
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
                    <button
                        className="btn btn-info"
                        onClick={() => this.props.history.push("/cars/update-car/" + this.state.id)}
                    >Update</button>
                    <button className="btn btn-danger"
                        onClick={() => this.deleteCar(this.state.id)}
                        style={{ marginLeft: "10px" }}>
                        Delete
                    </button>
                    <button className="btn btn-success"
                        onClick={() => this.props.history.push("/cars/cars-list")}
                        style={{ marginLeft: "10px" }}>
                        Cancel
                    </button>
                </div>
            </div>
        )
    }
}

const viewParams = [
    {
        name: "Model",
        var: "model",
    },
    {
        name: "Fabrication year",
        var: "fab_year",
    },
    {
        name: "Cilindrical capacity",
        var: "cil_capacity",
    },
    {
        name: "Fuel type",
        var: "fuel_type",
    },
    {
        name: "Registration date",
        var: "register_date",
    },
    {
        name: "Color",
        var: "color",
    },
    {
        name: "Seats number",
        var: "seats_nr",
    },
    {
        name: "Rent cost (per day in $)",
        var: "rent_cost",
    },
    {
        name: "Description",
        var: "description",
    },
]

export default ViewCar;
