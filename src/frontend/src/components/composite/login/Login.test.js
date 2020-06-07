import { redirectUser, loginUser, generateRedirectUrl } from "./Login";
import { createMemoryHistory } from "history";
import axios from "axios";
import { wait } from "@testing-library/react";

const MockAdapter = require("axios-mock-adapter");

window.open = jest.fn();

describe("Login", () => {
  let mock;
  const code = "abc123";
  const setAuthed = jest.fn();
  const setMessage = jest.fn();
  const path = "/securefileupload/applicationform";
  const history = createMemoryHistory();

  beforeEach(() => {
    mock = new MockAdapter(axios);
  });

  test("Redirect user function opens the redirect login url", () => {
    redirectUser();

    expect(window.open).toHaveBeenCalledTimes(1);
  });

  test("Login user function clears session storage and takes you back to unauthed homepage on fail", async () => {
    sessionStorage.setItem("jwt", "token");

    expect(sessionStorage.getItem("jwt")).toBeTruthy();

    mock.onGet(`http://localhost:8080/oauth/login?code=${code}`).reply(400);

    loginUser(code, setAuthed, setMessage, path, history);

    await wait(() => {
      expect(sessionStorage.getItem("jwt")).toBeFalsy();
    });
  });

  test("Login user function sets jwt token on success", async () => {
    mock
      .onGet(`http://localhost:8080/oauth/login?code=${code}`)
      .reply(200, "token123");

    loginUser(code, setAuthed, setMessage, path, history);

    await wait(() => {
      expect(sessionStorage.getItem("jwt")).toEqual("token123");
    });
  });

  test("Generate redirect url function does not set loginUrl when unsuccessful", async () => {
    mock.onGet("http://localhost:8080/oauth/getBCSCUrl").reply(400);

    const result = generateRedirectUrl();

    await wait(() => {
      expect(result).toBeFalsy();
    });
  });
});
