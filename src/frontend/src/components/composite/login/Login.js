import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import queryString from "query-string";
import "./Login.css";
import "bootstrap/dist/css/bootstrap.css";
import { Button } from "../../base/button/Button";
import Uploader from "../../base/uploader/Uploader";
import { isAuthenticated } from "../../../AuthenticationHelper";

let loginUrl;
const basicAuth = {
  auth: {
    username: sessionStorage.getItem("username"),
    password: sessionStorage.getItem("password")
  }
};

export const redirectUser = loginUrl => {
  window.open(loginUrl, "_self");
};

export const generateRedirectUrl = () => {
  axios
    .get("http://localhost:8080/oauth/getBCSCUrl", basicAuth)
    .then(response => {
      loginUrl = response.data;
    })
    .catch(err => {
      throw new Error(
        "An error occurred while getting BCSC redirect url.",
        err
      );
    });

  return loginUrl;
};

export const loginUser = (code, setAuthed) => {
  axios
    .get(`http://localhost:8080/oauth/login?code=${code}`, basicAuth)
    .then(response => {
      sessionStorage.setItem("jwt", response.data);
      if (isAuthenticated()) setAuthed(true);
    })
    .catch(err => {
      throw new Error("An error occurred while logging in.", err);
    });
};

export const Login = () => {
  const [authed, setAuthed] = useState(false);
  const location = useLocation();
  const urlParam = queryString.parse(location.search);
  const { code } = urlParam;

  useEffect(() => {
    if (code && !authed) {
      loginUser(code, setAuthed);
    } else {
      loginUrl = generateRedirectUrl();
    }
  });

  return (
    <>
      {!code && (
        <div className="outline">
          <div className="center-align">
            <Button
              onClick={() => redirectUser(loginUrl)}
              label="Login with a BC Services Card"
              style="login_bcsc"
            />
          </div>
        </div>
      )}
      {authed && <Uploader />}
    </>
  );
};
