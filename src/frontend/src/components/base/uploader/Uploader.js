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

export default function Uploader() {
  const [files, setFiles] = useState([]);

  let server = {
    process: (fieldName, file, metadata, load, error, progress, abort) => {
      axios
        .put("http://localhost:4572/demo-bucket/test.png", file, {
          headers: {
            "Content-Type": file.type,
            Authorization:
              "AWS4-HMAC-SHA256 Credential=123/20200603/us-east-1/execute-api/aws4_request, SignedHeaders=host;x-amz-date, Signature=66e07c453bb659e4a43b2a2f17bc6b656100e7c65925ab72a520aa9702f56056"
          }
        })
        .then(response => {
          if (response.status >= 200 && response.status < 300) {
            load(response.config.data.name);
          } else {
            error("An error occurred with the upload. Please try again.");
          }
        })
        .catch(error => {
          console.log(error);
        });

      return {
        abort: () => {
          abort();
        }
      };
    },
    revert: (uniqueFileId, load, error) => {
      // need an endpoint to delete file from s3 storage
    }
  };

  return (
    <div className="App">
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
