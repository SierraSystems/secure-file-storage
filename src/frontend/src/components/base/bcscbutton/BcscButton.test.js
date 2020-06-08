import React from "react";
import { BcscButton } from "./BcscButton";
import testBasicSnapshot from "../../../TestHelper";

const onClick = jest.fn();

describe("BcscButton", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<BcscButton onClick={onClick} />);
  });
});
