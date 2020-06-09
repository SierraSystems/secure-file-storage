import axios from "axios";
import { wait } from "@testing-library/react";
import { areDuplicates, fileNameComponent, imageComponent } from "./Files";
import testBasicSnapshot from "../../../TestHelper";

const MockAdapter = require("axios-mock-adapter");

describe("Files", () => {
  let mock;

  beforeEach(() => {
    mock = new MockAdapter(axios);
    mock.onGet("http://localhost:8085/files").reply(200, ["test.png"]);
  });

  const file = "test.png";
  const setSource = jest.fn();
  const getFile = jest.fn();
  const source = [{ "test.png": "byte" }];
  const arr = [{ key1: "val1" }, { key2: "val2" }];
  const obj = { key1: "val1" };

  test("areDuplicates function returns true when two objects are same", async () => {
    await wait(() => {
      expect(areDuplicates(arr, obj)).toEqual(true);
    });
  });

  test("areDuplicates function returns false when two objects are not the same", async () => {
    const newObj = { ...obj, key1: "val4" };

    await wait(() => {
      expect(areDuplicates(arr, newObj)).toEqual(false);
    });
  });

  test("File name component matches the snapshot", async () => {
    testBasicSnapshot(fileNameComponent(file, getFile, setSource));
  });

  test("imageComponent matches the snapshot", async () => {
    testBasicSnapshot(imageComponent(source, file));
  });
});
