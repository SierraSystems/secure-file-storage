import React from "react";
import axios from "axios";
import { wait } from "@testing-library/react";
import Uploader, { uploadFile, deleteFile } from "./Uploader";
import testBasicSnapshot from "../../../TestHelper";

const MockAdapter = require("axios-mock-adapter");

describe("Uploader", () => {
  let mock;

  beforeEach(() => {
    mock = new MockAdapter(axios);
  });

  const load = jest.fn();
  const error = jest.fn();
  const file = { name: "test.png" };
  const uniqueFileId = "test.png";

  test("Component renders as expected", () => {
    testBasicSnapshot(<Uploader />);
  });

  test("Upload file function throws an error when an axios request is unsuccessful", async () => {
    mock.onPost(`http://localhost:8085/files`).reply(400);

    uploadFile(file, load, error);

    await wait(() => {
      expect(error).toHaveBeenCalledTimes(1);
      expect(error).toHaveBeenCalledWith(
        "An error occurred with the upload. Please try again."
      );
    });
  });

  test("Upload file function calls load when axios call returns a status code in success range", async () => {
    mock.onPost(`http://localhost:8085/files`).reply(200);

    uploadFile(file, load, error);

    await wait(() => {
      expect(load).toHaveBeenCalled();
    });
  });

  test("Delete file function throws an error when an axios request is unsuccessful", async () => {
    mock.onDelete(`http://localhost:8085/files/${uniqueFileId}`).reply(400);

    deleteFile(uniqueFileId, error);

    await wait(() => {
      expect(error).toHaveBeenCalledTimes(2);
      expect(error).toHaveBeenCalledWith(
        "An error occurred with the delete. Please try again."
      );
    });
  });

  test("Delete file function successfully unuploads the file when an axios request is successful", async () => {
    mock.onDelete(`http://localhost:8085/files/${uniqueFileId}`).reply(200);

    deleteFile(uniqueFileId, error);

    await wait(() => {
      // make sure error is not called again
      expect(error).toHaveBeenCalledTimes(2);
    });
  });
});
