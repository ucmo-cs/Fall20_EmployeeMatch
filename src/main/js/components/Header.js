import React, { Component } from 'react'
import ApiService from "../services/ApiService";

class Header extends Component{

    /*goToLogin()   {
        window.localStorage.removeItem("carId");
        this.props.history.push('/login');
    }*/
    render(){
        function goToLogin() {
            window.localStorage.removeItem("carId");
            this.props.history.push('/login');
        }

        return(

            <nav className="navbar navbar-expand-lg navbar-light bg-secondary">
                <a className="navbar-brand font-weight-bold " href="#">Home</a>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item font-weight-bold">
                            <a className="nav-link" href="#">Who We Are</a>
                        </li>
                        <li className="nav-item font-weight-bold">
                            <a className="nav-link" href="#">Who We Work With</a>
                        </li>
                        <li className="nav-item font-weight-bold">
                            <a className="nav-link" href="#">Who We Help</a>
                        </li>
                    </ul>
                </div>


                <button type="button" onClick={() => goToLogin()} className="btn btn-primary btn-lg">Login</button>

            </nav>
        );
    }

}
export default Header;