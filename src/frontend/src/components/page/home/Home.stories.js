import React from "react";
import { MemoryRouter } from "react-router-dom";

import Home from "./Home";

export default {
  title: "Home",
  component: Home
};

const header = {
  name: "NTT Data Secure File Upload"
};

const page = {
  header
};

export const Default = () => (
  <MemoryRouter>
    <Home page={page} />
  </MemoryRouter>
);

export const Mobile = () => (
  <MemoryRouter>
    <Home page={page} />
  </MemoryRouter>
);

Mobile.story = {
  parameters: {
    viewport: {
      defaultViewport: "mobile2"
    }
  }
};
