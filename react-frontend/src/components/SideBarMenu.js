import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Icon } from 'semantic-ui-react';
import "./../style/SideBarMenu.scss";

class SideBarMenu extends Component {
    constructor(props) {
        super(props)

        this.state = {
            active_elem: initialActiveElemList[window.location.pathname],
            active_sub_elem: initialActiveSubElemList[window.location.pathname],
            menuList: [
                {
                    name: "Cars",
                    icon: "car",
                    link: "/cars",
                    compenents: [
                        {
                            name: "Cars list",
                            link: "/cars-list",
                        },
                        {
                            name: "Add car",
                            link: "/add-car",
                        },
                    ]
                },
                {
                    name: "Customers",
                    icon: "user",
                    link: "/customers",
                    compenents: [
                        {
                            name: "Customers list",
                            link: "/customers-list",
                        },
                        {
                            name: "Add customer",
                            link: "/add-customer",
                        },
                    ]
                },
                {
                    name: "Rents",
                    icon: "list",
                    link: "/rents",
                    compenents: [
                        {
                            name: "Rents list",
                            link: "/rents-list",
                        },
                        {
                            name: "Add rent",
                            link: "/add-rent",
                        },
                    ]
                },
            ]
        }
    }

    render() {
        return (
            <div className="jeq-side-bar-container">
                {this.state.menuList.map((elem, index) => {
                    return (
                        <div
                            key={"elem-" + index}
                            className={`jeq-side-bar-elem${window.location.pathname.includes(elem.link) ? " jeq-side-bar-elem-active" : ""}`}
                        >
                            <div
                                className="jeq-side-bar-elem-name"
                            >
                                <NavLink
                                    to={elem.link + elem.compenents[0].link}
                                    onClick={() => {
                                        this.setState({
                                            // active_elem: elem.link,
                                            // active_sub_elem: elem.compenents[0].link,
                                        });
                                    }}
                                >
                                    <Icon name={elem.icon} />
                                    {elem.name}
                                </NavLink>
                            </div>
                            <div className="jeq-side-bar-elem-container" >
                                {elem.compenents.map((item, index2) => {
                                    return (
                                        <div
                                            key={"item-" + index + "-" + index2}
                                            className={`jeq-side-bar-elem-item-name${window.location.pathname.includes(item.link) ? " jeq-side-bar-elem-item-name-active" : ""}`}
                                        >
                                            <NavLink
                                                to={elem.link + item.link}
                                                onClick={() => {
                                                    this.setState({
                                                        // active_sub_elem: item.link,
                                                    })
                                                }}
                                            >
                                                {item.name}
                                            </NavLink>
                                        </div>
                                    )
                                })}
                            </div>
                        </div>
                    )
                })}
            </div>
        )
    }
}

const initialActiveElemList = {
    "/": "/cars",
    "/cars/cars-list": "/cars",
    "/cars/add-car": "/cars",
    "/customers/customers-list": "/customers",
    "/customers/add-customer": "/customers",
    "/rents/rents-list": "/rents",
    "/rents/add-rent": "/rents",
}

const initialActiveSubElemList = {
    "/": "/cars-list",
    "/cars/cars-list": "/cars-list",
    "/cars/add-car": "/add-car",
    "/customers/customers-list": "/customers-list",
    "/customers/add-customer": "/add-customer",
    "/rents/rents-list": "/rents-list",
    "/rents/add-rent": "/add-rent",
}

export default SideBarMenu
