import React, { Component } from 'react'

import axios from "axios";
import { withRouter } from 'react-router-dom';
import ApiService from "../services/ApiService";
import RegisterQuestionsCouplets from "./RegisterQuestionsCouplets";


class RegisterQuestionsComponent extends Component{
    constructor(props) {
        super(props);


        this.state = {
            userid : null,
            eo1: null,
            eo2 : null,
            eo3 : null,
            eo4 : null,
            eo5 : null,
            ew1 : null,
            ew2 : null,
            ew3 : null,
            ew4 : null,
            ew5 : null,
            message: null
        };


        this.saveEmployeePreferences = this.saveEmployeePreferences.bind(this);
    }


    saveEmployeePreferences = (e) => {
        e.preventDefault();
        if(this.validate()) {
            axios.post(
                'http://localhost:8080/employeepreferences',
                {
                    userid: this.props.location.state.userid,
                    eo1: this.state.eo2,
                    eo2: this.state.eo2,
                    eo3: this.state.eo3,
                    eo4: this.state.eo4,
                    eo5: this.state.eo5,
                    ew1: this.state.ew1,
                    ew2: this.state.ew2,
                    ew3: this.state.ew3,
                    ew4: this.state.ew4,
                    ew5: this.state.ew5
                }
            ).then((res) => {
                console.log(res)
                this.props.history.push('/accountHome')
            });

        }
        else
            this.setState({message: "incorrect entry. Ranks must be between 1 and 5 and must contain no repeats"});
    }

    validate = () =>    {
        try {
            //validation for eos
            let eoSelections = [parseInt(this.state.eo1), parseInt(this.state.eo2), parseInt(this.state.eo3), parseInt(this.state.eo4), parseInt(this.state.eo5)];
            let eo = [false, false, false, false, false];
            for(let i =0; i < 5; i++)   {
                //check to see that i is not nan
                if(isNaN(eoSelections[i]))
                    return false;
                //check that the selection is in proper range
                if(eoSelections[i] < 1 || eoSelections[i] > 5)
                    return false;
                //check that rank has not been assigned already
                if(!eo[eoSelections[i-1]])
                    eo[eoSelections[i-1]] = true;
                else
                    return false;
            }

            //validation for ews
            let ewSelections = [parseInt(this.state.ew1), parseInt(this.state.ew2), parseInt(this.state.ew3), parseInt(this.state.ew4), parseInt(this.state.ew5)];
            let ew = [false, false, false, false, false];
            for(let i =0; i < 5; i++)   {
                //check to see that i is not nan
                if(isNaN(eoSelections[i]))
                    return false;
                //check that the selection is in proper range
                if(ewSelections[i] < 1 || ewSelections[i] > 5)
                    return false;
                //check that rank has not been assigned already
                if(!ew[ewSelections[i-1]])
                    ew[ewSelections[i-1]] = true;
                else
                    return false;
            }
        } catch(NumberFormatException)    {
            console.log(NumberFormatException);
            this.setState({message: "inputs must be numbers"});
            return false;
        }
        return true;
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
        console.log("state has been set for "+e.target.name);
    }


    render(){
        return(

            <div className = "container">
                <form onSubmit={this.saveEmployeePreferences}>
                    <h1>Rank your preferences from one to five for what you are looking for in a company</h1>
                    <h3>all entries must be between 1-5 and not contain any repeats</h3>
                    <RegisterQuestionsCouplets name = "ew1" myValue = {this.state.ew1} myOnChange = {this.onChange} question = "Distance"></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Time Off" name="ew2" myValue={this.state.ew2} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Company Culture" name="ew3" myValue={this.state.ew3} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Promotion Opportunity" name="ew4" myValue={this.state.ew4} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Work-life Balance" name="ew5"myValue={this.state.ew5} myOnChange={this.onChange}></RegisterQuestionsCouplets>

                    <h1>Rank your preferences from one to five for what you can offer to a company</h1>
                    <h3>all entries must be between 1-5 and not contain any repeats</h3>
                    <RegisterQuestionsCouplets question ="Willingness to work overtime" name="eo1" myValue={this.state.eo1} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 2" name="eo2" myValue={this.state.eo2} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 3" name="eo3" myValue={this.state.eo3} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 4" name="eo4" myValue={this.state.eo4} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <RegisterQuestionsCouplets question ="Question 5" name="eo5" myValue={this.state.eo5} myOnChange={this.onChange}></RegisterQuestionsCouplets>
                    <input type="submit" style={{float: 'right'}} value="Submit"/>
                    <h2 style={{color: 'red'}}>{this.state.message}</h2>
                </form>
            </div>

        );
    }

}

export default withRouter(RegisterQuestionsComponent);