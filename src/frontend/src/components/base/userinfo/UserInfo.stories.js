import React from "react";
import { storiesOf } from "@storybook/react";
import "bootstrap/dist/css/bootstrap.css";
import UserInfo from "./UserInfo";

storiesOf("UserInfo", module).add("default", () => <UserInfo />);
