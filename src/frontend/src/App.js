import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";
import Home from "./components/page/home/Home";

export default function App() {
  const header = {
    name: "NTT Data Secure File Upload"
  };

  return (
    <div>
      <Switch>
        <Redirect exact from="/" to="/securefileupload" />
        <Route exact path="/securefileupload">
          <Home page={{ header }} />
        </Route>
      </Switch>
    </div>
  );
}
