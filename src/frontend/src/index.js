import "react-app-polyfill/ie11";
import "react-app-polyfill/stable";
import React from "react";
import ReactDOM from "react-dom";
import axios from "axios";
import { BrowserRouter } from "react-router-dom";
import { positions, Provider } from "react-alert";
import AlertTemplate from "react-alert-template-basic";
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";

if (window.REACT_APP_API_BASE_URL) {
  axios.defaults.baseURL = window.REACT_APP_API_BASE_URL;
} else if (process.env.REACT_APP_API_BASE_URL) {
  axios.defaults.baseURL = process.env.REACT_APP_API_BASE_URL;
}

if (window.REACT_APP_BASIC_AUTH_USERNAME) {
  sessionStorage.setItem("username", window.REACT_APP_BASIC_AUTH_USERNAME);
} else if (process.env.REACT_APP_BASIC_AUTH_USERNAME) {
  sessionStorage.setItem("username", process.env.REACT_APP_BASIC_AUTH_USERNAME);
}

if (window.REACT_APP_BASIC_AUTH_PASSWORD) {
  sessionStorage.setItem("password", window.REACT_APP_BASIC_AUTH_PASSWORD);
} else if (process.env.REACT_APP_BASIC_AUTH_PASSWORD) {
  sessionStorage.setItem("password", process.env.REACT_APP_BASIC_AUTH_PASSWORD);
}

const options = {
  timeout: 3000,
  position: positions.BOTTOM_CENTER
};

ReactDOM.render(
  <Provider template={AlertTemplate} {...options}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>,
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
