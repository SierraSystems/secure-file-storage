import React, { useState } from "react";
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

  return (
    <div className="App">
      <FilePond
        files={files}
        allowMultiple
        onupdatefiles={setFiles}
        labelIdle='Drag and Drop your files or <span class="filepond--label-action">Browse</span>'
      />
    </div>
  );
}
