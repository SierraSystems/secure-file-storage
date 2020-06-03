import React from "react";
import NavBar from "./NavBar";
import { generateSnapshotTree } from "../../../TestHelper";

describe("NavBar", () => {
  test("Component renders as expected", () => {
    expect(generateSnapshotTree(<NavBar />)).toMatchSnapshot();
  });
});
