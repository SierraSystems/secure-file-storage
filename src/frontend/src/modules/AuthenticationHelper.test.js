import { isAuthenticated, saveUserInfo } from "./AuthenticationHelper";

const jwt = require("jsonwebtoken");

describe("AuthenticationHelper Module", () => {
  beforeEach(() => {
    sessionStorage.clear();
  });

  test("isAuthenticated returns true when we are authed", () => {
    const payload = { key: "value" };
    const validator = "secret";
    const token = jwt.sign(payload, validator);

    sessionStorage.setItem("validator", validator);
    sessionStorage.setItem("jwt", token);

    const result = isAuthenticated();

    expect(result).toEqual(true);
  });

  test("isAuthenticated returns false when token not present in session storage", () => {
    const result = isAuthenticated();

    expect(result).toEqual(false);
  });

  test("isAuthenticated returns false when validator not present in session storage", () => {
    sessionStorage.setItem("jwt", "token");
    sessionStorage.removeItem("validator");
    const result = isAuthenticated();

    expect(result).toEqual(false);
  });

  test("isAuthenticated returns false if jwt verify leads to an error", () => {
    const payload = { key: "value" };
    const wrongValidator = "wrongsecret";
    const token = jwt.sign(payload, wrongValidator);

    sessionStorage.setItem("validator", "correctsecret");
    sessionStorage.setItem("jwt", token);

    const result = isAuthenticated();

    expect(result).toEqual(false);
  });

  test("Save user info returns without saving to session storage if userinfo passed in is falsy", () => {
    saveUserInfo(null);

    expect(sessionStorage.getItem("displayName")).toBeFalsy();
  });

  test("Save user info saves to session storage if userinfo passed in is truthy", () => {
    saveUserInfo({
      display_name: "Test user",
      address: {
        street_address: "123 street"
      }
    });

    expect(sessionStorage.getItem("displayName")).toEqual("Test user");
  });
});
