import React, { Component } from 'react';
import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';
import CustomersService from '../../services/CustomersService';
import RentsService from '../../services/RentsService';

class ViewRent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            start_date: "",
            customer: "",
            car: "",
            end_date: "",
            total_cost: "",
        }
    }

    componentDidMount() {
        RentsService.getCarRentSummary(this.state.id).then(res => {
            this.setState({
                start_date: res.data.dataInchiriere != null ? moment(new Date(res.data.dataInchiriere)).format("DD/MM/YYYY") : null,
                customer: res.data.numeClient,
                car: res.data.modelAutovehicul,
                end_date: res.data.cnp,
                end_date: res.data.dataReturnare != null ? moment(new Date(res.data.dataReturnare)).format("DD/MM/YYYY") : null,
                total_cost: res.data.costInchiriere + " $",
            })
        }).catch(e => console.log(e))
    }


    deleteRent = (id) => {
        RentsService.deleteRent(id).then(res => {
            this.props.history.push("/rents/rents-list");
        });
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>View Rent</h1>
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
                        onClick={() => this.props.history.push("/rents/update-rent/" + this.state.id)}
                    >Update</button>
                    <button className="btn btn-danger"
                        onClick={() => this.deleteRent(this.state.id)}
                        style={{ marginLeft: "10px" }}>
                        Delete
                    </button>
                    <button className="btn btn-success"
                        onClick={() => this.props.history.push("/rents/rents-list")}
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
        name: "Customer name",
        var: "customer",
    },
    {
        name: "Car",
        var: "car",
    },
    {
        name: "Date start",
        var: "start_date",
    },
    {
        name: "Date end",
        var: "end_date",
    },
    {
        name: "Total rent cost",
        var: "total_cost",
    },
]

export default ViewRent;
