import React from "react";
import renderer from "react-test-renderer";
import Uploader from "./Uploader";

describe("Uploader", () => {
  test("Component renders as expected", () => {
    const component = renderer.create(<Uploader />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
  });
});
