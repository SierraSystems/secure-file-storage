import React from "react";
import { storiesOf } from "@storybook/react";
import { action } from "@storybook/addon-actions";
import "bootstrap/dist/css/bootstrap.css";
import { BcscButton } from "./BcscButton";

const actionData = {
  onClick: action("onButtonClicked")
};

storiesOf("BcscButton", module).add("default", () => (
  <BcscButton {...actionData} />
));
