import React from "react";
import { Collapse, Navbar, Nav, NavbarText } from "reactstrap";
import "./NavBar.css";
import "bootstrap/dist/css/bootstrap.css";

export default function NavBar() {
  return (
    <div className="nav-div">
      <Navbar expand="md">
        <Collapse isOpen navbar>
          <Nav className="mr-auto" navbar>
            <NavbarText>Secure File Upload</NavbarText>
          </Nav>
        </Collapse>
      </Navbar>
    </div>
  );
}
