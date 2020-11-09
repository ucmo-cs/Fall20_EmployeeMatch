import React, { Component } from 'react'

class RegisterCouplets extends Component{
    onChangeHandler;


    render(){

        return(
            <div>
                <h2>{this.props.name.toUpperCase()}</h2>
                <input className='col-12 row' type = 'text' id={this.props.name} name={this.props.name}  placeholder={this.props.name}/>
            </div>

        );
    }

}

export default RegisterCouplets;