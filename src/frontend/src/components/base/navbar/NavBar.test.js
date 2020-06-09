import React from "react";
import { NavBar } from "./NavBar";
import testBasicSnapshot from "../../../TestHelper";

describe("NavBar", () => {
  const onGetClick = jest.fn();
  const onUploadClick = jest.fn();

  test("Component renders as expected in upload mode", () => {
    testBasicSnapshot(
      <NavBar
        isDownload={false}
        onGetClick={onGetClick}
        onUploadClick={onUploadClick}
      />
    );
  });

  test("Component renders as expected in download mode", () => {
    testBasicSnapshot(
      <NavBar
        isDownload
        onGetClick={onGetClick}
        onUploadClick={onUploadClick}
      />
    );
  });
});
