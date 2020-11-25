import React, { Component } from 'react'
import axios from "axios";
import { withRouter } from 'react-router-dom';

import ApiService from "../services/ApiService";

class RegisterComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {
            email: "",
            passhash: "",
            firstn: "",
            lastn: "",
            message: ""
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

    saveEmployee = (e) => {
        e.preventDefault();
        //if all fields are filled out post to the server
        if(this.validate()) {
            axios.post(
                'http://localhost:8080/employee',
                {
                    firstn: this.state.firstn,
                    lastn: this.state.lastn,
                    email: this.state.email,
                    passhash: this.state.passhash
                }
            ).then((res) => {console.log(res);
                this.props.history.push('/registerQuestions');});

        }
        //one or more fields are blank
        else    {
            this.setState({message: "one or more fields are not filled out. All fields are required"})
        }
    }

    validate = () => {
        if(this.state.firstn.length == 0)
            return false;
        if(this.state.lastn == 0)
            return false;
        if(this.state.email == 0)
            return false;
        if(this.state.passhash == 0)
            return false;
        return true;
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
        console.log("state has been set for "+e.target.name);
    }

    render(){
        /*function saveEmployee(e)  {
            e.preventDefault();
            axios.post(
                'http://localhost:8080/employee',
                {
                    firstn: this.state.firstn,
                    lastn: this.state.lastn,
                    email: this.state.email,
                    passhash: this.state.passhash
                }
            ).then((res) => console.log(res));
            //this.props.history.push('/registerQuestions');
        }*/

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
                    <input type="submit" style={{float: 'right'}} value="Submit"/>
                    <br/>
                    <div>
                        <h2 style={{color: 'red'}}>{this.state.message}</h2>
                    </div>
                </form>
            </div>

        );
    }

}

export default withRouter(RegisterComponent);