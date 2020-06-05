import React from "react";
import "./Button.css";

export const Button = ({ onClick, style, label }) => (
  <button className={`${style} btn`} onClick={onClick} type="button">
    {label}
  </button>
);
