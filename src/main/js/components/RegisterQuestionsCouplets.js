import React, { Component } from 'react'

class RegisterQuestionsCouplets extends Component{
    onChangeHandler;


    render(){

        return(
            <div>
                <h2>{this.props.question.toLowerCase()}</h2>
                <input className='col-12 row' type = 'text' id={this.props.name} name={this.props.name}  placeholder={this.props.name} value={this.props.myValue} onChange={this.props.myOnChange}/>
            </div>

        );
    }

}

export default RegisterQuestionsCouplets;