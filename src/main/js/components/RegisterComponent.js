import React, { Component } from 'react'
import RegisterCouplets from "./RegisterCouplets";

class RegisterComponent extends Component{
    submitHandler;
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: ''
        }
    }



    render(){
        const {email, password} = this.state;
        this.submitHandler = (e) => {e.preventDefault; this.state.email = e.refs.email; this.state.password = e.refs.password; console.log(this.state)}
        return(
            <div className = "container">
                <form onSubmit={this.submitHandler}>
                    <RegisterCouplets name = "Email"/>
                    <h2>PASSWORD</h2>
                    <input type ='password' id ='password' name='password' placeholder='password'/>
                    <RegisterCouplets name="First Name"/>
                    <RegisterCouplets name="Last Name"/>

                    <br/>
                    <input type="submit" style={{float: 'right'}} value="Submit"/>
                    <br/>
                </form>
            </div>

        );
    }

}

export default RegisterComponent;