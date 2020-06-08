import React from "react";
import "./Heading.css";
import "bootstrap/dist/css/bootstrap.css";

export default function Heading() {
  return (
    <div className="spacing">
      <h1 className="sub-heading head">
        Welcome to Secure File Upload & Storage
      </h1>
      <br />
      <p className="text head">
        The Secure File Upload & Storage allows you to securely upload and store
        files on an S3 compatible storage bucket.
      </p>
    </div>
  );
}
