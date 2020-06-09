import React, { useState, useEffect } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.css";
import "./Files.css";

const sourceArr = [];

export const getFile = (file, setSource) => {
  axios
    .get(`http://localhost:8085/files/${file}`, {
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
      sourceObj[key] = "data:;base64," + base64;
      sourceArr.push(sourceObj);
      const newSourceArr = [...sourceArr];
      setSource(newSourceArr);
    })
    .catch(err => {
      console.log(err);
    });
};

export const Files = () => {
  const [files, setFiles] = useState([]);
  const [source, setSource] = useState(null);

  useEffect(() => {
    if (files.length > 0) return;

    axios
      .get(`http://localhost:8085/files`)
      .then(response => {
        setFiles(response.data);
      })
      .catch(err => {
        console.log(err);
      });
  }, [files]);

  return (
    <div className="head">
      {files.map(file => (
        <div key={file}>
          <div className="box">
            <p className="floatleft">{file}</p>
            <button
              className="floatright"
              onClick={() => getFile(file, setSource)}
            >
              Get
            </button>
          </div>
          {source &&
            source.map(sourceObj => (
              <>
                {sourceObj[`${file}`] && (
                  <>
                    <img
                      src={sourceObj[`${file}`]}
                      width="100%"
                      height="100%"
                    />
                    <br />
                  </>
                )}
              </>
            ))}
          <br />
        </div>
      ))}
    </div>
  );
};
