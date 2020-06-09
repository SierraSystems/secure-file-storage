import React from "react";
import "./NavBar.css";
import "bootstrap/dist/css/bootstrap.css";

export const NavBar = ({ onGetClick, onUploadClick, isDownload }) => (
  <div className="blue spacing">
    <nav className="navbar navbar-expand-md sticky-top">
      <div className="container-fluid">
        <a className="navbar-brand" href="https://ca.nttdata.com/en/">
          <img
            src={`${process.env.PUBLIC_URL}/images/s3.svg`}
            height="40px"
            width="55px"
            alt="s3 logo"
          />
        </a>
        {!isDownload && (
          <span className="links" onClick={onGetClick}>
            Get Files
          </span>
        )}
        {isDownload && (
          <span className="links" onClick={onUploadClick}>
            Upload
          </span>
        )}
      </div>
    </nav>
    <p className="main-heading">Secure File Upload & Storage</p>
  </div>
);
