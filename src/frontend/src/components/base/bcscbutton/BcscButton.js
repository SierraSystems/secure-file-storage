import React from "react";
import "./BcscButton.css";

export const BcscButton = ({ onClick }) => (
  <div className="outline">
    <div className="center-align">
      <button className="login_bcsc btn" onClick={onClick} type="button">
        Login with a BC Services Card
      </button>
    </div>
  </div>
);
