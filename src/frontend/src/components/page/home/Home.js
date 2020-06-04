import React from "react";
import { NavBar } from "../../base/navbar/NavBar";
import Heading from "../../base/heading/Heading";
import Uploader from "../../base/uploader/Uploader";

export default function Home() {
  return (
    <>
      {/* Navigation */}
      <NavBar />

      {/* Heading */}
      <Heading />

      <br />

      {/* Uploader */}
      <Uploader />
    </>
  );
}
