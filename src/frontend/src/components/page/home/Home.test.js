import React from "react";
import { MemoryRouter } from "react-router-dom";
import Home from "./Home";
import testBasicSnapshot from "../../../TestHelper";

describe("Home", () => {
  test("Component renders as expected", () => {
    testBasicSnapshot(
      <MemoryRouter initialEntries={["/applicationform"]}>
        <Home />
      </MemoryRouter>
    );
  });
});
