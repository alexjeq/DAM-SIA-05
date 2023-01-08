import moment from 'moment/moment';
import React, { Component } from 'react';
import RentsService from '../../services/RentsService';
// import "./../style/Header.scss";

class RentsList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            rents: []
        }
    }

    componentDidMount() {
        RentsService.getRents().then(res => this.setState({
            rents: res.data._embedded.rents
        }))
    }

    render() {
        return (
            <div>
                <div className="jeq-page-header">
                    <h2>Rents list</h2>
                    <button
                        className="btn btn-primary"
                    // onClick={this.addEmployee}
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
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.rents.map(rent =>
                                    <tr key={rent.idInchiriere}>
                                        <td>{rent.idInchiriere}</td>
                                        <td></td>
                                        <td></td>
                                        <td>{moment(rent.dataInchiriere).format("DD/MM/yyyy")}</td>
                                        <td>{rent.dataReturnare != null ? moment(rent.dataReturnare).format("DD/MM/yyyy") : ""}</td>
                                        <td>{rent.zileInchiriere}</td>
                                        <td>{rent.costInchiriere}</td>
                                        <td>
                                            <button
                                                // onClick={() => this.editEmployee(employee.id)} 
                                                className="btn btn-info">Update </button>
                                            <button style={{ marginLeft: "10px" }}
                                                // onClick={() => this.deleteEmployee(employee.id)} 
                                                className="btn btn-danger"
                                            >Delete </button>
                                            <button style={{ marginLeft: "10px" }}
                                                // onClick={() => this.viewEmployee(employee.id)} 
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

export default RentsList
