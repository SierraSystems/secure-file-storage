import React from "react";
import { storiesOf } from "@storybook/react";
import "bootstrap/dist/css/bootstrap.css";
import NavBar from "./NavBar";

storiesOf("NavBar", module).add("default", () => <NavBar />);
