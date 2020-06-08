import React from "react";

export default function UserInfo() {
  return (
    <div className="spacing">
      <p className="head">
        <b>Name: </b>
        {sessionStorage.getItem("displayName")}
      </p>
      <p className="head">
        <b>Birthdate: </b>
        {sessionStorage.getItem("birthdate")}
      </p>
      <p className="head">
        <b>Gender: </b>
        {sessionStorage.getItem("gender")}
      </p>
      <p className="head">
        <b>Address: </b>
        {sessionStorage.getItem("address")}
      </p>
    </div>
  );
}
