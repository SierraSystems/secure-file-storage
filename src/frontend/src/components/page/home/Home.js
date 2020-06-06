import React from "react";
import { NavBar } from "../../base/navbar/NavBar";
import Heading from "../../base/heading/Heading";
import { Login } from "../../composite/login/Login";

export default function Home() {
  return (
    <>
      {/* Navigation */}
      <NavBar />

      {/* Heading */}
      <Heading />

      <br />

      <Login />
    </>
  );
}
