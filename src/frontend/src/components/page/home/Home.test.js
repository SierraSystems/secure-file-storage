import React from "react";
import Home from "./Home";
import testBasicSnapshot from "../../../TestHelper";

describe("Home", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<Home />);
  });
});
