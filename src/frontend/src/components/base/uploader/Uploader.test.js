import React from "react";
import Uploader from "./Uploader";
import testBasicSnapshot from "../../../TestHelper";

describe("Uploader", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<Uploader />);
  });
});
