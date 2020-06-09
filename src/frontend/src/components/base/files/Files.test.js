import React from "react";
import axios from "axios";
import { wait } from "@testing-library/react";
import {
  Files,
  areDuplicates,
  fileNameComponent,
  imageComponent
} from "./Files";
import testBasicSnapshot from "../../../TestHelper";

const MockAdapter = require("axios-mock-adapter");

describe("Files", () => {
  let mock;

  beforeEach(() => {
    mock = new MockAdapter(axios);
    mock.onGet("http://localhost:8085/files").reply(200, ["test.png"]);
  });

  const file = { name: "test.png" };
  const uniqueFileId = "test.png";
  const setSource = jest.fn();
  const getFile = jest.fn();
  const source = [{ "test.png": "byte" }];

  test("areDuplicates function returns true when two objects are same", async () => {
    const arr = [{ key1: "val1" }, { key2: "val2" }];
    const obj = { key1: "val1" };

    await wait(() => {
      expect(areDuplicates(arr, obj)).toEqual(true);
    });
  });

  test("areDuplicates function returns false when two objects are not the same", async () => {
    const arr = [{ key1: "val1" }, { key2: "val2" }];
    const obj = { key1: "val4" };

    await wait(() => {
      expect(areDuplicates(arr, obj)).toEqual(false);
    });
  });

  test("File name component matches the snapshot", async () => {
    testBasicSnapshot(fileNameComponent(uniqueFileId, getFile, setSource));
  });

  test("imageComponent matches the snapshot", async () => {
    testBasicSnapshot(imageComponent(source, uniqueFileId));
  });
});
