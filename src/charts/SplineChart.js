import { useEffect, useState } from 'react';
import Navbar from "../components/Navbar";
import TextArea from '../components/TextArea';
import c3 from 'c3';
import 'c3/c3.css';

function SplineChart() {
  const [fileContents, setFileContents] = useState("");
  const [data, setData] = useState([]);
  const [formattedData, setFormattedData] = useState([]);
  const [refresh, setRefresh] = useState("");

  const csvToTable = (csv) => {
     const rows = csv.split('\n');
     const table = rows.map((row) => row.split(','));
     return table;
  };

  const handleFileSelect = async (event) => {
     const file = event.target.files[0];
     const text = await file.text();
     setFileContents(text);
     const convertedData = csvToTable(text);
     setData(convertedData);
     const formatted = convertedData.reduce((acc, row, rowIndex) => {
          row.forEach((cell, cellIndex) => {
          if (rowIndex === 0) {
               acc[cellIndex] = [cell];
          } else {
               acc[cellIndex].push(cell);
          }
          });
          return acc;
     }, []);
     const filteredArray = formatted.filter(row => {
          return row.some(cell => cell !== ""); // Keep the row if at least one cell is not empty
     });
     setFormattedData(filteredArray);
  };

  const showChart = () => {
     var chart = c3.generate({
          data: {
              columns: formattedData,
              type: 'spline'
          },
          bar: {
              width: {
                  ratio: 0.5
              }
          }
      });
      setRefresh("");
  }
  
  useEffect(() => {
     if (formattedData.length > 0) {
       showChart();
     }
   }, [formattedData]);
 
   useEffect(() => {
     setFormattedData([]);
     document.title = 'Spline Chart';
   }, [])

     return (
       <div className="SplineChart" style={{backgroundColor: "#F5F7FE", height: "100vh"}}>
          <Navbar />
          <div className='FileReader'>
               <TextArea key={fileContents} contents={fileContents}/>
               <label for="file-upload" class="custom-file-upload">
               Choose File
               </label>
               <input id="file-upload" type="file" accept=".csv" onChange={handleFileSelect}/>
          </div>
          <div id="chart" />
          <p>{refresh}</p>
       </div>
     );
   }
   
export default SplineChart;
   