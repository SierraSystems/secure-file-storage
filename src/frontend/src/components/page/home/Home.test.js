import React from "react";
import renderer from "react-test-renderer";
import Home from "./Home";

describe("Home", () => {
  test("Component renders as expected", () => {
    const component = renderer.create(<Home />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
  });
});
