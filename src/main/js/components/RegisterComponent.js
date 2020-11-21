import React, { Component } from 'react'
import RegisterCouplets from "./RegisterCouplets";
import {Link} from "react-router-dom";
import axios from "axios";
import ApiService from "../services/ApiService";

class RegisterComponent extends Component{
    submitHandler;
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            passhash: '',
            firstn: '',
            lastn: ''
        }
        this.saveEmployee = this.saveEmployee.bind(this);
    }
    /*saveEmployee = (e) => {
        e.preventDefault();
        let employee = {email: this.state.email, password: this.state.password, firstn: this.state.firstn, lastn: this.state.lastn};
        ApiService.addEmployee(employee)
            .then(res => {
                this.setState({message : 'Employee added successfully.'});
                this.props.history.push('/');
            });
    }*/

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });



    render(){
        function saveCar()  {
            axios.post(
                'http://localhost:8080/employee',
                {

                }
            )
        }

        //const {email, password} = this.state;
        //this.submitHandler = (e) => {e.preventDefault; this.state.email = e.refs.email; this.state.password = e.refs.password; console.log(this.state)}
        return(
            <div className = "container">
                <form onSubmit={this.saveEmployee}>
                    <h2>Email</h2>
                    <input className='col-12 row' type = 'text' id='email' name='email' value={this.state.email} onChange={this.onChange}/>
                    <h2>PASSWORD</h2>
                    <input type ='password' id ='passhash' name='passhash' placeholder='password' value={this.state.passhash} onChange={this.onChange}/>
                    <h2>First Name</h2>
                    <input className='col-12 row' type = 'text' id='firstn' name='firstn' value={this.state.firstn} onChange={this.onChange}/>
                    <h2>Last Name</h2>
                    <input className='col-12 row' type = 'text' id='lastn' name='lastn' value={this.state.lastn} onChange={this.onChange}/>

                    <br/>
                    <Link to="/registerquestions"><input type="submit" style={{float: 'right'}} value="Submit"/></Link>
                    <br/>
                </form>
            </div>

        );
    }

}

export default RegisterComponent;