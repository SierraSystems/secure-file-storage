import React, { useState } from "react";
import PropTypes from "prop-types";
import { NavBar } from "../../base/navbar/NavBar";
import Heading from "../../base/heading/Heading";
import { Download } from "../../base/download/Download";
import { Login } from "../../composite/login/Login";
import { isAuthenticated } from "../../../modules/AuthenticationHelper";

export default function Home({ alert }) {
  const [shouldShowGetFiles, setShouldShowGetFiles] = useState(false);

  const showGetFiles = () => {
    if (isAuthenticated()) setShouldShowGetFiles(true);
    else alert.error("Please authenticate to use the service.");
  };

  const showUploader = () => {
    setShouldShowGetFiles(false);
  };

  return (
    <>
      <NavBar
        onGetClick={showGetFiles}
        onUploadClick={showUploader}
        isDownload={shouldShowGetFiles}
      />
      <Heading />
      {!shouldShowGetFiles && <Login />}
      {shouldShowGetFiles && <Download />}
    </>
  );
}

Home.propTypes = {
  alert: PropTypes.shape({
    error: PropTypes.func.isRequired
  }).isRequired
};
