import React from "react";
import Loader from "./Loader";
import testBasicSnapshot from "../../../TestHelper";

describe("Loader", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<Loader />);
  });
});
