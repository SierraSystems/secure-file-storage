const jwt = require("jsonwebtoken");

let validator;

if (window.REACT_APP_JWT_VALIDATOR) {
  validator = window.REACT_APP_JWT_VALIDATOR;
} else if (process.env.REACT_APP_JWT_VALIDATOR) {
  validator = process.env.REACT_APP_JWT_VALIDATOR;
}

export function saveUserInfo(userInfo) {
  if (!userInfo) return;

  sessionStorage.setItem("displayName", userInfo.display_name);
  sessionStorage.setItem("birthdate", userInfo.birthdate);
  sessionStorage.setItem("gender", userInfo.gender);
  sessionStorage.setItem(
    "address",
    `${userInfo.address.street_address}, ${userInfo.address.locality}, ${userInfo.address.region}, ${userInfo.address.country}`
  );
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
      saveUserInfo(decoded.userInfo);
    }
  });

  return isAuthed;
}
