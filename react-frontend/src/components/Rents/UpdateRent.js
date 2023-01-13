import React, { Component } from 'react';
import Select from "react-select";
import DatePicker from "react-datepicker";
import RentsService from '../../services/RentsService';
import moment from 'moment';

class UpdateRent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            start_date: null,
            customer: null,
            car: null,
            end_date: null,
        }
    }

    componentDidMount() {
        RentsService.getCarRentSummary(this.state.id).then(res => {
            this.setState({
                start_date: res.data.dataInchiriere != null ? new Date(res.data.dataInchiriere) : null,
                customer: {
                    value: 0,
                    label: res.data.numeClient
                },
                car: {
                    value: 0,
                    label: res.data.modelAutovehicul
                },
                end_date: res.data.dataReturnare != null ? new Date(res.data.dataReturnare) : null,
            })
        }).catch(e => console.log(e))
    }

    updateRent = () => {
        RentsService.setEndDate(this.state.id, moment(this.state.end_date).format("DD-MM-yyyy")).then(res => {
            if (res.data > 0) {
                this.props.history.push("/rents/rents-list");
            }
        }).catch(e => console.log(e))
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
                                    <div className="form-group">
                                        <label>Start date</label>
                                        <DatePicker
                                            selected={this.state.start_date}
                                            dateFormat="dd/MM/yyyy"
                                            onChange={(date) => this.setState({
                                                start_date: date
                                            })}
                                            disabled={true}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Customer</label>
                                        <Select
                                            options={this.state.customers}
                                            value={this.state.customer}
                                            isDisabled={true}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Car</label>
                                        <Select
                                            options={this.state.cars}
                                            value={this.state.car}
                                            isDisabled={true}
                                        />
                                    </div>
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
                                    <button
                                        className="btn btn-success"
                                        onClick={this.updateRent}
                                    >Save</button>
                                    <button className="btn btn-danger"
                                        onClick={() => this.props.history.push("/rents/rents-list")}
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

export default UpdateRent;
