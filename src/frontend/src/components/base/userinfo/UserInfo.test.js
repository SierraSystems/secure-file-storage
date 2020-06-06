import React from "react";
import UserInfo from "./UserInfo";
import testBasicSnapshot from "../../../TestHelper";

describe("UserInfo", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(<UserInfo />);
  });
});
