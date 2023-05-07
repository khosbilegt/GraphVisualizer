import './TextArea.css';
import Table from './Table';
import React, { useEffect, useState } from 'react';

function TextArea(props) {
     const [data, setData] = useState([]);
     const [refresh, setRefresh] = useState("");
     // const data = [
     //      ['Apple', 'Banana', 'Cherry'],
     //      ['Doughnut', 'Eclair', 'Fritter'],
     //      ['Grape', 'Honeydew', 'Iced tea'],
     //    ];

     const csvToTable = (csv) => {
          const rows = csv.split('\n');
          const table = rows.map((row) => row.split(','));
          return table;
     };

     useEffect(() => {
          setData(csvToTable(props.contents));
          console.log(data);
          setRefresh("");
     }, []);
     
     return (
          <div className='TextArea'>
               <Table data={data}/>
               <p>{refresh}</p>
          </div>
     )
}

export default TextArea;