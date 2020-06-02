import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";
import Home from "./components/page/home/Home";

export default function App() {
  return (
    <div>
      <Switch>
        <Redirect exact from="/" to="/securefileupload" />
        <Route exact path="/securefileupload">
          <Home />
        </Route>
      </Switch>
    </div>
  );
}
