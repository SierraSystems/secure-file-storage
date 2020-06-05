import React from "react";
import "./Login.css";
import "bootstrap/dist/css/bootstrap.css";
import { Button } from "../../base/button/Button";

export const loginUser = () => {
  console.log("login user function is called");
};

export const Login = () => (
  <div className="outline">
    <div className="center-align">
      <Button
        onClick={loginUser}
        label="Login with a BC Services Card"
        style="login_bcsc"
      />
    </div>
  </div>
);
