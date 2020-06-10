import React from "react";
import { storiesOf } from "@storybook/react";
import "bootstrap/dist/css/bootstrap.css";
import { NavBar } from "./NavBar";

const onGetClick = () => {};
const onUploadClick = () => {};

storiesOf("NavBar", module)
  .add("isUpload", () => (
    <NavBar
      isDownload={false}
      onGetClick={onGetClick}
      onUploadClick={onUploadClick}
    />
  ))
  .add("isDownload", () => (
    <NavBar isDownload onGetClick={onGetClick} onUploadClick={onUploadClick} />
  ));
