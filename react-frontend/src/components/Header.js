import React, { Component } from 'react';
import "./../style/Header.scss";

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div className="navbar-brand">{"Car Rent App Admin"}</div>
                    </nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent
