import React, { Component } from 'react';
import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';
import CustomersService from '../../services/CustomersService';

class ViewCustomer extends Component {
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
                dob: res.data.dataNasterii != null ? moment(new Date(res.data.dataNasterii)).format("DD/MM/YYYY") : null,
            })
        }).catch(e => console.log(e))
    }


    deleteCustomer = (id) => {
        CustomersService.deleteCustomer(id).then(res => {
            this.props.history.push("/customers/customers-list");
        });
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h1>View Customer</h1>
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
                        onClick={() => this.props.history.push("/customers/update-customer/" + this.state.id)}
                    >Update</button>
                    <button className="btn btn-danger"
                        onClick={() => this.deleteCustomer(this.state.id)}
                        style={{ marginLeft: "10px" }}>
                        Delete
                    </button>
                    <button className="btn btn-success"
                        onClick={() => this.props.history.push("/customers/customers-list")}
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
        name: "Name",
        var: "name",
    },
    {
        name: "Phone number",
        var: "phone_number",
    },
    {
        name: "Email",
        var: "email",
    },
    {
        name: "CNP",
        var: "cnp",
    },
    {
        name: "Adrress",
        var: "address",
    },
    {
        name: "Date of birth",
        var: "dob",
    },
]

export default ViewCustomer;
