import React from "react";
import Heading from "./Heading";
import testBasicSnapshot from "../../../TestHelper";

describe("Heading", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<Heading />);
  });
});
