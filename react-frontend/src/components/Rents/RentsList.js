import moment from 'moment/moment';
import React, { Component } from 'react';
import RentsService from '../../services/RentsService';
import CarsService from '../../services/CarsService';
import axios from 'axios';
import { Label } from 'semantic-ui-react';
// import "./../style/Header.scss";

class RentsList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            rents: []
        }
    }

    componentDidMount() {
        this.getRentsList();
    }

    getRentsList = () => {
        let rents = [];
        RentsService.getRents().then(res => {
            res.data._embedded.rents.forEach((element, i) => {
                let rent = JSON.parse(JSON.stringify(element));
                RentsService.returnCarName(element.idInchiriere).then(res => {
                    rent.car = res.data;
                })
                RentsService.returnCustomerName(element.idInchiriere).then(res => {
                    rent.customer = res.data;
                })
                rents.push(rent);
            });
            this.setState({
                rents: rents
            })
        })
    }

    deleteRent = (id) => {
        RentsService.deleteRent(id).then(res => {
            this.getRentsList();
        })
    }

    returnCar = (id) => {
        RentsService.returnCar(id, moment(new Date()).format("DD-MM-yyyy")).then(res => {
            this.getRentsList();
        })
    }

    render() {
        return (
            <div>
                <div className="jeq-page-header">
                    <h2>Rents list</h2>
                    <button
                        className="btn btn-primary"
                        onClick={() => this.props.history.push("/rents/add-rent")}
                    >
                        {"Add rent"}
                    </button>
                </div>
                <div className="jeq-table">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>{"ID"}</th>
                                <th>{"CUSTOMER"}</th>
                                <th>{"CAR"}</th>
                                <th>{"DATE START"}</th>
                                <th>{"DATE END"}</th>
                                <th>{"DAYS DURATION"}</th>
                                <th>{"TOTAL COST"}</th>
                                <th>{"STATUS"}</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.rents.map(rent => {
                                    console.log(rent?.car ?? "not found");
                                    return (
                                        <tr key={rent.idInchiriere}>
                                            <td>{rent.idInchiriere}</td>
                                            <td>{rent.customer}</td>
                                            <td>{rent.car}</td>
                                            <td>{moment(rent.dataInchiriere).format("DD/MM/yyyy")}</td>
                                            <td>{rent.dataReturnare != null ? moment(rent.dataReturnare).format("DD/MM/yyyy") : ""}</td>
                                            <td>{rent.zileInchiriere}</td>
                                            <td>{rent.costInchiriere}</td>
                                            <td >
                                                <div style={{ display: "flex", justifyContent: "center" }}>
                                                    <Label color={rent.status == 1 ? "green" : "grey"}>{rent.status == 1 ? "In process" : "Not in process"}</Label>
                                                </div>
                                            </td>
                                            <td>
                                                <button
                                                    onClick={() => this.props.history.push("/rents/update-rent/" + rent.idInchiriere)}
                                                    className="btn btn-info">Update </button>
                                                <button style={{ marginLeft: "10px" }}
                                                    onClick={() => this.deleteRent(rent.idInchiriere)}
                                                    className="btn btn-danger"
                                                >Delete </button>
                                                <button style={{ marginLeft: "10px" }}
                                                    onClick={() => this.props.history.push("/rents/view-rent/" + rent.idInchiriere)}
                                                    className="btn btn-success">View </button>
                                                {rent.status == 1 &&
                                                    <button style={{ marginLeft: "10px" }}
                                                        onClick={() => this.returnCar(rent.idInchiriere)}
                                                        className="btn btn-warning">Return car</button>
                                                }
                                            </td>
                                        </tr>
                                    )
                                }
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default RentsList
