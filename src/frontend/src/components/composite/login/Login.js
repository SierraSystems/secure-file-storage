import React, { useState, useEffect } from "react";
import { useLocation, useHistory } from "react-router-dom";
import axios from "axios";
import queryString from "query-string";
import "./Login.css";
import "bootstrap/dist/css/bootstrap.css";
import { BcscButton } from "../../base/bcscbutton/BcscButton";
import Loader from "../../base/loader/Loader";
import Uploader from "../../base/uploader/Uploader";
import { isAuthenticated } from "../../../modules/AuthenticationHelper";
import UserInfo from "../../base/userinfo/UserInfo";

let loginUrl;
const basicAuth = {
  auth: {
    username: sessionStorage.getItem("username"),
    password: sessionStorage.getItem("password")
  }
};

const authMessageAndComponent = (authed, redirectUser) => {
  const component = authed ? (
    <>
      <UserInfo />
      <br />
      <Uploader />
    </>
  ) : (
    <BcscButton onClick={redirectUser} />
  );

  return (
    <>
      <br />
      {component}
    </>
  );
};

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

export const loginUser = (code, setAuthed, path, history) => {
  if (isAuthenticated()) {
    setAuthed(true);
    return;
  }

  axios
    .get(`http://localhost:8080/oauth/login?code=${code}`, basicAuth)
    .then(response => {
      sessionStorage.setItem("jwt", response.data);
      if (isAuthenticated()) setAuthed(true);
    })
    .catch(() => {
      sessionStorage.clear();
      history.push(path);
    });
};

export const Login = () => {
  const [authed, setAuthed] = useState(false);
  const history = useHistory();
  const location = useLocation();
  const urlParam = queryString.parse(location.search);
  const { code } = urlParam;

  useEffect(() => {
    if (code && !authed) loginUser(code, setAuthed, location.pathname, history);
    else loginUrl = generateRedirectUrl();
  }, [authed, code, history, location.pathname]);

  return (
    <>
      {!code && authMessageAndComponent(authed, redirectUser)}
      {code && !authed && <Loader />}
      {authed && authMessageAndComponent(authed, redirectUser)}
    </>
  );
};
