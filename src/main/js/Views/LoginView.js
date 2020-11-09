import React from 'react';
import {Link} from "react-router-dom";

function LoginView()
{

        return (

            <div className = "container">
                <label htmlFor="uname"><b>Username</b></label>
                <br></br>
                <input type="text" placeholder="Enter Username" name="uname" required></input>
                    <br></br>

                <label htmlFor="psw"><b>Password</b></label>
                <br></br>
                <input type="password" placeholder="Enter Password" name="psw" required></input>
                <br></br>

                    <button type="button" className="submit">Login</button>


                <Link to="/register"><input type="submit" className="register text-default" value="Register"></input></Link>

                <span className="forgotpsw"><a href="#">Forgot password?</a></span>
            </div>



        );
}
//<button type="button" className="register">Register</button>
export default LoginView