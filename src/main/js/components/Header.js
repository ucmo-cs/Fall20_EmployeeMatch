import React, { Component } from 'react'

import ApiService from "../services/ApiService";
import {Link} from "react-router-dom";
//import companyTitle from "../../resources/Images/CompanyTitle.png";

class Header extends Component{


    render(){

        return(

            <nav className="navbar navbar-expand-lg navbar-light bg-secondary">
                <a className="navbar-brand font-weight-bold " href="/">Home</a>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item font-weight-bold">
                            <Link className="nav-link" to="/about">Who We Are</Link>
                        </li>
                        <li className="nav-item font-weight-bold">
                            <Link className="nav-link" to="/Work_With">Who We Work With</Link>
                        </li>
                        <li className="nav-item font-weight-bold">
                            <Link className="nav-link" to="/We_Help">Who We Help</Link>
                        </li>
                    </ul>
                </div>


                <Link className="text-light" to="/login"><button type="button" className="btn btn-primary btn-lg">Login</button></Link>


            </nav>
        );
    }

}
//<img style={{left: '50%'}} height="30px" width="50px" src = {companyTitle} ></img>
export default Header;