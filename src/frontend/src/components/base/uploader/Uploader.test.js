import React from "react";
import Uploader from "./Uploader";
import generateSnapshotTree from "../../../TestHelper";

describe("Uploader", () => {
  test("Component renders as expected", () => {
    expect(generateSnapshotTree(<Uploader />)).toMatchSnapshot();
  });
});
