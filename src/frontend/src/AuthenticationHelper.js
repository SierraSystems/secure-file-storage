const jwt = require("jsonwebtoken");
let validator;

if (window.REACT_APP_JWT_VALIDATOR) {
  validator = window.REACT_APP_JWT_VALIDATOR;
} else if (process.env.REACT_APP_JWT_VALIDATOR) {
  validator = process.env.REACT_APP_JWT_VALIDATOR;
}

export function isAuthenticated() {
  const token = sessionStorage.getItem("jwt");
  let isAuthed = true;

  console.log("validator", validator);
  console.log(token);

  if (!token || !validator) return false;

  console.log(token);

  // verify a token symmetric
  jwt.verify(token, validator, err => {
    if (err) {
      console.log(err);
      isAuthed = false;
    }
  });

  return isAuthed;
}
