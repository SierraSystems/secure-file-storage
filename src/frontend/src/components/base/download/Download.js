import React, { useState, useEffect } from "react";
import axios from "axios";
import { Files } from "../files/Files";
import "bootstrap/dist/css/bootstrap.css";

export const getFile = setSource => {
  axios
    .get(`http://localhost:8085/files/naramata.jpg`, {
      responseType: "arraybuffer"
    })
    .then(response => {
      const base64 = btoa(
        new Uint8Array(response.data).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          ""
        )
      );
      setSource("data:;base64," + base64);
    })
    .catch(err => {
      console.log(err);
    });
};

export const Download = () => {
  const [files, setFiles] = useState([]);
  const [source, setSource] = useState(null);

  useEffect(() => {
    if (files) return;

    axios
      .get(`http://localhost:8085/files`)
      .then(response => {
        console.log(response);
        setFiles(response.data);
      })
      .catch(err => {
        console.log(err);
      });
  }, [files]);

  return (
    <div className="spacing">
      <p className="text head">Select a file to retrieve from S3:</p>
      <br />
      <Files />
    </div>
  );
};
