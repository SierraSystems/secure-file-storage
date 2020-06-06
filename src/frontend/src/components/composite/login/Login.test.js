import React from "react";
import { MemoryRouter } from "react-router-dom";
import Login from "./Login";
import testBasicSnapshot from "../../../TestHelper";

describe("Login", () => {
  test("Component renders as expected when there is no code in the url", () => {
    testBasicSnapshot(
      <MemoryRouter initialEntries={["/applicationform"]}>
        <Login />
      </MemoryRouter>
    );
  });
});
