import React, { useState } from "react";
import axios from "axios";

// Import React FilePond
import { FilePond, registerPlugin } from "react-filepond";

// Import FilePond styles
import "filepond/dist/filepond.min.css";

// Import the Image EXIF Orientation and Image Preview plugins
import FilePondPluginImageExifOrientation from "filepond-plugin-image-exif-orientation";
import FilePondPluginImagePreview from "filepond-plugin-image-preview";
import "filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css";

// Register the plugins
registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview);

export const uploadFile = (file, load, error) => {
  axios
    .put(`/demo-bucket/${file.name}`, file, {
      headers: {
        "Content-Type": file.type
      }
    })
    .then(response => {
      if (response.status >= 200 && response.status < 300) {
        load(response.config.data.name);
      }
    })
    .catch(() => {
      error("An error occurred with the upload. Please try again.");
    });
};

export const deleteFile = (uniqueFileId, error) => {
  axios
    .delete(`/demo-bucket/${uniqueFileId}`)
    .then(() => {})
    .catch(() => {
      error("An error occurred with the delete. Please try again.");
    });
};

export default function Uploader() {
  const [files, setFiles] = useState([]);

  const server = {
    process: (_1, file, _3, load, error, _6, abort) => {
      uploadFile(file, load, error);

      return {
        abort: () => {
          abort();
        }
      };
    },
    revert: (uniqueFileId, _, error) => {
      deleteFile(uniqueFileId, error);
    }
  };

  return (
    <div className="spacing" style={{ width: "60%", margin: "auto" }}>
      <FilePond
        files={files}
        allowMultiple
        onupdatefiles={setFiles}
        labelIdle='Drag and Drop your files or <span class="filepond--label-action">Browse</span>'
        server={server}
      />
    </div>
  );
}
