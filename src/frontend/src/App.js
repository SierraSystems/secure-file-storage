import React from "react";
import { useAlert } from "react-alert";
import { Switch, Route, Redirect } from "react-router-dom";
import Home from "./components/page/home/Home";

export default function App() {
  const alert = useAlert();

  return (
    <div>
      <Switch>
        <Redirect exact from="/" to="/securefileupload/applicationform" />
        <Route
          exact
          path="/(criminalrecordcheck/applicationform|securefileupload/applicationform)"
        >
          <Home alert={alert} />
        </Route>
      </Switch>
    </div>
  );
}
