import React, { useState, useEffect } from "react";
import { useLocation, useHistory } from "react-router-dom";
import axios from "axios";
import queryString from "query-string";
import "./Login.css";
import "bootstrap/dist/css/bootstrap.css";
import { BcscButton } from "../../base/bcscbutton/BcscButton";
import Loader from "../../base/loader/Loader";
import Uploader from "../../base/uploader/Uploader";
import { isAuthenticated } from "../../../AuthenticationHelper";
import UserInfo from "../../base/userinfo/UserInfo";

let loginUrl;
const basicAuth = {
  auth: {
    username: sessionStorage.getItem("username"),
    password: sessionStorage.getItem("password")
  }
};

const authMessage = message => (
  <div className="spacing">
    <p className="text head">{message}</p>
  </div>
);

export const redirectUser = () => {
  window.open(loginUrl, "_self");
};

export const generateRedirectUrl = () => {
  axios
    .get("http://localhost:8080/oauth/getBCSCUrl", basicAuth)
    .then(response => {
      loginUrl = response.data;
    })
    .catch(() => {});

  return loginUrl;
};

export const loginUser = (code, setAuthed, setMessage, path, history) => {
  axios
    .get(`http://localhost:8080/oauth/login?code=${code}`, basicAuth)
    .then(response => {
      sessionStorage.setItem("jwt", response.data);
      if (isAuthenticated()) {
        setAuthed(true);
        setMessage(
          "You have been securely authenticated. Your information is presented below. Enjoy using the service!"
        );
      }
    })
    .catch(() => {
      sessionStorage.clear();
      history.push(path);
    });
};

export const Login = () => {
  const [authed, setAuthed] = useState(false);
  const [message, setMessage] = useState(
    "Please login using your BC Services Card to use the service. Click the button below to log in."
  );
  const history = useHistory();
  const location = useLocation();
  const urlParam = queryString.parse(location.search);
  const { code } = urlParam;

  useEffect(() => {
    if (code && !authed) {
      loginUser(code, setAuthed, setMessage, location.pathname, history);
    } else {
      loginUrl = generateRedirectUrl();
    }
  });

  return (
    <>
      {!code && (
        <>
          {authMessage(message)}
          <br />
          <BcscButton onClick={redirectUser} />
        </>
      )}
      {code && !authed && <Loader />}
      {authed && (
        <>
          {authMessage(message)}
          <br />
          <UserInfo />
          <br />
          <Uploader />
        </>
      )}
    </>
  );
};
