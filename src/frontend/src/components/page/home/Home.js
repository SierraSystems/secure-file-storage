import React, { useState } from "react";
import { useAlert } from "react-alert";
import { NavBar } from "../../base/navbar/NavBar";
import Heading from "../../base/heading/Heading";
import { Download } from "../../base/download/Download";
import { Login } from "../../composite/login/Login";
import { isAuthenticated } from "../../../modules/AuthenticationHelper";

export default function Home() {
  const alert = useAlert();
  const [shouldShowGetFiles, setShouldShowGetFiles] = useState(false);

  const showGetFiles = () => {
    console.log("in here?");
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
