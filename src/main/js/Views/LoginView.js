import React from 'react';

function LoginView()
{

        return (
            <html>
    <body>
            <div class = "container">
                <label htmlFor="uname"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="uname" required></input>

                <label htmlFor="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="psw" required></input>

                    <button type="submit">Login</button>

                <button type="button" className="register">Register</button>
                <span className="forgotpsw"><a href="#">Forgot password?</a></span>
            </div>
    </body>
            </html>


        );
}
export default LoginView