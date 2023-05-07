import React from 'react';
import './Navbar.css';
import { Link } from 'react-router-dom';
import Logo from "../assets/images/logo.png";

function Table({ data }) {
  return (
    <div className="Navbar">
          <ul>
               <Link to="/">
                    <img className="Logo" src={Logo}/>
               </Link>
          </ul>
    </div>
  );
}

export default Table;