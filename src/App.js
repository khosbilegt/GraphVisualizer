import './App.css';
import React, { useState } from 'react';
import ChartLink from './components/ChartLink';
import TextArea from './components/TextArea';
import Navbar from './components/Navbar';
import BarChartImage from "./assets/images/bar.jpg";

function App() {

  const [fileContents, setFileContents] = useState("");

    const handleFileSelect = async (event) => {
        const file = event.target.files[0];
        const text = await file.text();
        setFileContents(text);
    };

  return (
    <div className="App">
      <Navbar />
      <div className='FileReader'>
          <TextArea key={fileContents} contents={fileContents}/>
          <label for="file-upload" class="custom-file-upload">
          Choose File
          </label>
          <input id="file-upload" type="file" accept=".csv" onChange={handleFileSelect}/>
      </div>
      <ul>
        <ChartLink img={BarChartImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={BarChartImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={BarChartImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={BarChartImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={BarChartImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={BarChartImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={BarChartImage} label="Bar Chart" link="/bar"/>
      </ul>
    </div>
  );
}

export default App;
