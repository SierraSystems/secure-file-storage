import React from "react";
import axios from "axios";
import {
  render,
  fireEvent,
  getAllByRole,
  getByText,
  wait
} from "@testing-library/react";
import Uploader, { uploadFile } from "./Uploader";
import testBasicSnapshot from "../../../TestHelper";

const MockAdapter = require("axios-mock-adapter");

// This sets the mock adapter on the default instance
const mock = new MockAdapter(axios);

describe("Uploader", () => {
  const load = jest.fn();
  const error = jest.fn();
  const file = { name: "test.png" };

  test("Component renders as expected", () => {
    testBasicSnapshot(<Uploader />);
  });

  test("Upload file function throws an error when an axios request is unsuccessful", async () => {
    mock.onPut("/demo-bucket/test.png", file).reply(400);

    uploadFile(file, load, error);

    await wait(() => {
      expect(error).toHaveBeenCalledWith(
        "An error occurred with the upload. Please try again."
      );
    });
  });
});
