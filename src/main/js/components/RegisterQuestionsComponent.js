import React, { Component } from 'react'

import axios from "axios";
import { withRouter } from 'react-router-dom';
import ApiService from "../services/ApiService";
import RegisterQuestionsCouplets from "./RegisterQuestionsCouplets";


class RegisterQuestionsComponent extends Component{
    constructor(props) {
        super(props);
        this.state = {
            userid: null,
            eo1: null,
            eo2 : null,
            eo3 : null,
            eo4 : null,
            eo5 : null,
            ew1: null,
            ew2 : null,
            ew3 : null,
            ew4 : null,
            ew5 : null
        }
        this.saveEmployeePreferences = this.saveEmployeePreferences.bind(this);
    }


    saveEmployeePreferences = (e) => {
        e.preventDefault();
        axios.post(
            'http://localhost:8080/employeepreferences',
            {
                userid : this.state.userid,
                eo1: this.state.eo2,
                eo2 : this.state.eo2,
                eo3 : this.state.eo3,
                eo4 : this.state.eo4,
                eo5 : this.state.eo5,
                ew1 : this.state.ew1,
                ew2 : this.state.ew2,
                ew3 : this.state.ew3,
                ew4 : this.state.ew4,
                ew5 : this.state.ew5
            }
        ).then((res) => {console.log(res)
                this.props.history.push('/accountHome')});
        //this.props.history.push('/registerQuestions');
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
        console.log("state has been set for "+e.target);
    }

    render(){

        return(
            <div className = "container">
                <form onSubmit={this.saveEmployeePreferences}>
                    <RegisterQuestionsCouplets name = "Distance" myValue = {this.state.eo1} myOnChange = {this.onChange} question = "Distance"/>
                    <RegisterQuestionsCouplets question ="Time Off" name="ew2" myValue={this.state.ew2} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Company Culture" name="ew3" myValue={this.state.ew3} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Promotion Opportunity" name="ew4" myValue={this.state.ew4} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Work-life Balance" name="ew5"myValue={this.state.ew5} myOnChange={this.onChange}></RegisterQuestionsCouplets>

                    <RegisterQuestionsCouplets question ="Willingness to work overtime" name="eo1" myValue={this.state.eo1} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 2" name="eo2" myValue={this.state.eo2} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 3" name="eo3" myValue={this.state.eo3} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 4" name="eo4" myValue={this.state.eo4} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 5" name="eo5" myValue={this.state.eo5} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <input type="submit" style={{float: 'right'}} value="Submit"/>
                </form>
            </div>

        );
    }

}

export default withRouter(RegisterQuestionsComponent);