import React, { Component } from 'react';
import CarsService from '../../services/CarsService';

class CarsList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            cars: []
        }
    }

    componentDidMount() {
        this.getCarsList();
    }

    getCarsList = () => {
        CarsService.getCars().then(res => this.setState({
            cars: res.data._embedded.cars
        }))
    }

    deleteCar = (id) => {
        CarsService.deleteCar(id).then(res => {
            this.getCarsList();
        });
    }

    render() {
        return (
            <div>
                <div className="jeq-page-header">
                    <h2>Cars list</h2>
                    <button
                        className="btn btn-primary"
                        onClick={() => this.props.history.push("/cars/add-car")}
                    >
                        {"Add car"}
                    </button>
                </div>
                <div className="jeq-table">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>{"ID"}</th>
                                <th>{"MODEL"}</th>
                                <th>{"FABRICATION YEAR"}</th>
                                <th>{"FUEL TYPE"}</th>
                                <th>{"COLOR"}</th>
                                <th>{"RENT COST PER DAY"}</th>
                                <th>{"STATUS"}</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.cars.map(car =>
                                    <tr key={car.idAutovehicul}>
                                        <td> {car.idAutovehicul} </td>
                                        <td> {car.model} </td>
                                        <td> {car.anFabricatie} </td>
                                        <td> {car.combustibil} </td>
                                        <td> {car.culoare} </td>
                                        <td> {car.costInchiriere}</td>
                                        <td> {car.status}</td>
                                        <td>
                                            <button
                                                onClick={() => this.props.history.push("/cars/update-car/" + car.idAutovehicul)}
                                                className="btn btn-info">Update </button>
                                            <button style={{ marginLeft: "10px" }}
                                                onClick={() => this.deleteCar(car.idAutovehicul)}
                                                className="btn btn-danger"
                                            >Delete </button>
                                            <button style={{ marginLeft: "10px" }}
                                                onClick={() => this.props.history.push("/cars/view-car/" + car.idAutovehicul)}
                                                className="btn btn-success">View </button>
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

export default CarsList;
