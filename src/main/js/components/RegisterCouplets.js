import React, { Component } from 'react'

class RegisterCouplets extends Component{
    onChangeHandler;


    render(){

        const name = this.props.name;
        return(
            <div>
                <h2>{this.props.name.toUpperCase()}</h2>
                <input className='col-12 row' type = 'text' id={name} name={name} value={this.props.myValue} onChange={this.props.myOnChange}/>
            </div>

        );
    }

}

export default RegisterCouplets;