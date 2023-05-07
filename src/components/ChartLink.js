import './ChartLink.css';
import React from 'react';
import { Link } from 'react-router-dom';

function ChartLink(props) {
     return (
          <Link to="/" className='ChartLink'>
               <button>
                    <img src={props.img}/>
                    <p>{props.label}</p>
               </button>
          </Link>
     );
}

export default ChartLink;