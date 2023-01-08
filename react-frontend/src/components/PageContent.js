import React, { Component } from 'react';
import "./../style/PageContent.scss";
import SideBarMenu from './SideBarMenu';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import CustomersList from './Customers/CustomersList';
import CarsList from './Cars/CarsList';
import AddCar from './Cars/AddCar';
import AddCustomer from './Customers/AddCustomer';
import RentsList from './Rents/RentsList';
import AddRent from './Rents/AddRent';
import UpdateCar from './Cars/UpdateCar';
import ViewCar from './Cars/ViewCar';
import UpdateCustomer from './Customers/UpdateCustomer';
import ViewCustomer from './Customers/ViewCustomer';

class PageContent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <div className="row jeq-row-side-menu">
                    <div className="col col-lg-2 fixed bg-dark jeq-no-right-padding">
                        <SideBarMenu />
                    </div>
                    <div className="col col-lg-10 jeq-content-page-container">
                        <Switch>
                            <Route path="/" exact component={CarsList}></Route>
                            <Route path="/cars/cars-list" component={CarsList}></Route>
                            <Route path="/cars/add-car" component={AddCar}></Route>
                            <Route path="/cars/update-car/:id" component={UpdateCar}></Route>
                            <Route path="/cars/view-car/:id" component={ViewCar}></Route>
                            <Route path="/customers/customers-list" component={CustomersList}></Route>
                            <Route path="/customers/add-customer" component={AddCustomer}></Route>
                            <Route path="/customers/update-customer/:id" component={UpdateCustomer}></Route>
                            <Route path="/customers/view-customer/:id" component={ViewCustomer}></Route>
                            <Route path="/rents/rents-list" component={RentsList}></Route>
                            <Route path="/rents/add-rent" component={AddRent}></Route>
                        </Switch>
                    </div>
                </div>
            </div>
        )
    }
}

export default PageContent
