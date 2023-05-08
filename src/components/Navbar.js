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
                <p>Contact me at <Link to="https://www.linkedin.com/in/khosbilegt-bilegsaikhan-82929424b/">Khosbilegt Bilegsaikhan</Link></p>
          </ul>
    </div>
  );
}

export default Table;