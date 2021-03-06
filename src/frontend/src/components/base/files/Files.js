import React, { useState, useEffect } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.css";
import "./Files.css";

const fp = require("lodash/fp");

const sourceArr = [];

export const areDuplicates = (arr, obj) => {
  let duplicate = false;

  arr.forEach(element => {
    if (fp.isEqual(element, obj)) duplicate = true;
  });

  return duplicate;
};

export const getFile = (file, setSource) => {
  axios
    .get(`/files/${file}`, {
      responseType: "arraybuffer"
    })
    .then(response => {
      const base64 = btoa(
        new Uint8Array(response.data).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          ""
        )
      );

      const sourceObj = {};
      const key = file;
      sourceObj[key] = `data:;base64,${base64}`;

      if (!areDuplicates(sourceArr, sourceObj)) sourceArr.push(sourceObj);

      const newSourceArr = [...sourceArr];
      setSource(newSourceArr);
    })
    .catch(() => {
      throw new Error(`Error getting file ${file}`);
    });
};

export const imageComponent = (source, file) => {
  return source.map(sourceObj => (
    <>
      {sourceObj[`${file}`] && (
        <>
          <img
            src={sourceObj[`${file}`]}
            width="100%"
            height="100%"
            alt={file}
          />
          <br />
        </>
      )}
    </>
  ));
};

export const fileNameComponent = (file, getFileFunc, setSource) => {
  return (
    <div className="box">
      <p className="floatleft">
        {file.length > 20 ? `${file.slice(0, 20)}...` : file}
      </p>
      <button
        className="floatright"
        onClick={() => getFileFunc(file, setSource)}
        type="button"
      >
        Get
      </button>
    </div>
  );
};

export const Files = () => {
  const [files, setFiles] = useState([]);
  const [source, setSource] = useState(null);

  useEffect(() => {
    if (files.length > 0) return;

    axios
      .get(`/files`)
      .then(response => setFiles(response.data))
      .catch(err => {
        throw new Error("Error getting all files", err);
      });
  }, [files]);

  return (
    <div className="head">
      {files.map(file => (
        <div key={file}>
          {fileNameComponent(file, getFile, setSource)}
          {source && <>{imageComponent(source, file)}</>}
          <br />
        </div>
      ))}
    </div>
  );
};
