import React, { Component } from 'react'

import ApiService from "../services/ApiService";
import {Link} from "react-router-dom";
//import companyTitle from "../../resources/Images/CompanyTitle.png";

class Header extends Component{


    render(){

        return(

            <nav className="navbar navbar-expand-lg navbar-light bg-secondary">
                <h1 className="text-center align-self-center"  style={style}>Employee Match</h1>
                <a className="navbar-brand font-weight-bold align-self-center" href="/">Home</a>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className={navBarStyle}>
                            <Link className="nav-link" to="/about">Who We Are</Link>
                        </li>
                        <li className={navBarStyle}>
                            <Link className="nav-link" to="/WorkWith">Who We Work With</Link>
                        </li>
                        <li className={navBarStyle}>
                            <Link className="nav-link" to="/We_Help">Who We Help</Link>
                        </li>
                        <li>

                        </li>
                    </ul>

                </div>


                <a href= "http://localhost:8080/login"><button  type="button" className="btn btn-primary btn-lg">Login</button></a>

                <Link to="/register"><button type="button" className="btn btn-primary btn-lg">Register</button></Link>


            </nav>
        );
    }

}
const navBarStyle = " nav-item font-weight-bold";

/*
<li className={navBarStyle}>
                    <Link className="nav-link" to="/Register">Register</Link>
                </li>
 */


const style = {
    color: 'red',
    position: 'absolute',
    right: '50vw',
}
//<img style={{left: '50%'}} height="30px" width="50px" src = {companyTitle} ></img>
export default Header;