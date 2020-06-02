import React from "react";
import PropTypes from "prop-types";
import "../page.css";
import Header from "../../base/header/Header";

export default function Home({ page: { header } }) {
  return (
    <main>
      <Header header={header} />
      <div className="page">
        <div className="content col-md-10">
          <p>Welcome to the NTT Data Secure File Upload App!</p>
        </div>
      </div>
    </main>
  );
}

Home.propTypes = {
  page: PropTypes.shape({
    header: PropTypes.shape({
      name: PropTypes.string.isRequired
    }).isRequired
  }).isRequired
};
