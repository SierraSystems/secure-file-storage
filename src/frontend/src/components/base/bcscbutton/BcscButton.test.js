import React from "react";
import { BcscButton } from "./BcscButton";
import testBasicSnapshot from "../../../TestHelper";

describe("BcscButton", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<BcscButton />);
  });
});
