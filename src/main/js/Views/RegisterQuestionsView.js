import React from 'react';
import RegisterQuestionsCouplets from "../components/RegisterQuestionsCouplets";

function RegisterQuestionsView()
{
    return (

        <div>
            <form>
                <h1>What do you value most in a company</h1>
                <h3>rank each on a scale from 1-5 with 1 being the least important, and 5 being the most important</h3>
                <RegisterQuestionsCouplets question ="Distance" name="ew1"></RegisterQuestionsCouplets>
                <RegisterQuestionsCouplets question ="Time Off" name="ew2"></RegisterQuestionsCouplets>
                <RegisterQuestionsCouplets question ="Company Culture" name="ew3"></RegisterQuestionsCouplets>
                <RegisterQuestionsCouplets question ="Promotion Opportunity" name="ew4"></RegisterQuestionsCouplets>
                <RegisterQuestionsCouplets question ="Work-life Balance" name="ew5"></RegisterQuestionsCouplets>

            </form>
        </div>



    );
}
export default RegisterQuestionsView