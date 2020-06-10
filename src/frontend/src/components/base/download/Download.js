import React from "react";
import { Files } from "../files/Files";
import "bootstrap/dist/css/bootstrap.css";

export const Download = () => {
  return (
    <div className="spacing">
      <p className="text head">Select a file to retrieve from S3:</p>
      <br />
      <Files />
    </div>
  );
};
