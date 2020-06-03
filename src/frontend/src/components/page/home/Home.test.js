import React from "react";
import Home from "./Home";
import generateSnapshotTree from "../../../TestHelper";

describe("Home", () => {
  test("Component renders as expected", () => {
    expect(generateSnapshotTree(<Home />)).toMatchSnapshot();
  });
});
