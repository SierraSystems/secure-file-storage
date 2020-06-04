import React from "react";
import { NavBar } from "./NavBar";
import testBasicSnapshot from "../../../TestHelper";

describe("NavBar", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<NavBar />);
  });
});
