import React from "react";
import { Download } from "./Download";
import testBasicSnapshot from "../../../TestHelper";

describe("Download", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<Download />);
  });
});
