import React from 'react';

function LoginView()
{

        return (
            <html>
    <body>
            <div class = "container">
                <label htmlFor="uname"><b>Username</b></label>
                <br></br>
                <input type="text" placeholder="Enter Username" name="uname" required></input>
                    <br></br>

                <label htmlFor="psw"><b>Password</b></label>
                <br></br>
                <input type="password" placeholder="Enter Password" name="psw" required></input>
                <br></br>

                    <button type="button" className="submit">Login</button>

                <button type="button" className="register">Register</button>
                <span className="forgotpsw"><a href="#">Forgot password?</a></span>
            </div>
    </body>
            </html>


        );
}
export default LoginView