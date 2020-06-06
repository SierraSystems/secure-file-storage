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

  if (!token || !validator) return false;

  // verify a token symmetric
  jwt.verify(token, validator, (err, decoded) => {
    if (err) {
      isAuthed = false;
    } else {
      sessionStorage.setItem("userInfo", decoded.userInfo);
    }
  });

  return isAuthed;
}
