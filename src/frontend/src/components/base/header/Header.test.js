import React from "react";
import { create } from "react-test-renderer";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";

import Header from "./Header";

describe("Header Component", () => {
  const header = {
    name: "NTT Data Secure File Upload"
  };

  window.confirm = jest.fn();

  test("Matches the snapshot", () => {
    const history = createMemoryHistory();

    const headerComponent = create(
      <Router history={history}>
        <Header header={header} />
      </Router>
    );
    expect(headerComponent.toJSON()).toMatchSnapshot();
  });
});
