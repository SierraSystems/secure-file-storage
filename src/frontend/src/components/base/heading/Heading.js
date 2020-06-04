import React from "react";
import "./Heading.css";
import "bootstrap/dist/css/bootstrap.css";

export default function Heading() {
  return (
    <div class="spacing">
      <h1 class="sub-heading head">Welcome to Secure File Upload & Storage</h1>
      <p class="text head">
        The Secure File Upload & Storage allows you to securely upload and store
        files on an S3 compatible storage bucket.
      </p>
    </div>
  );
}
