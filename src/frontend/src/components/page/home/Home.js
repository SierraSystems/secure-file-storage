import React from "react";
import NavBar from "../../base/navbar/NavBar";
import Uploader from "../../base/uploader/Uploader";

export default function Home() {
  return (
    <>
      <NavBar />
      <br />
      <h2>NTT Data Secure File Upload</h2>

      {/* file uploader */}
      <div>
        <Uploader />
      </div>
    </>
  );
}
