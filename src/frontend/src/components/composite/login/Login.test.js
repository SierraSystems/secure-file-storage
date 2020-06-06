import { redirectUser } from "./Login";

window.open = jest.fn();

describe("Login", () => {
  test("Redirect user function opens the redirect login url", () => {
    redirectUser();

    expect(window.open).toHaveBeenCalledTimes(1);
  });
});
