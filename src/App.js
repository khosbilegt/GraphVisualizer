import './App.css';
import React, { useState } from 'react';
import ChartLink from './components/ChartLink';
import Navbar from './components/Navbar';
import CompareBarImage from "./assets/images/compare_bar.png";
import PieImage from "./assets/images/pie.png";

function App() {
  return (
    <div className="App">
      <Navbar />
      <ul>
        <ChartLink img={CompareBarImage} label="Bar Chart (Compare)" link="/compare-bar"/>
        <ChartLink img={PieImage} label="Pie Chart" link="/pie"/>
        <ChartLink img={CompareBarImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={CompareBarImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={CompareBarImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={CompareBarImage} label="Bar Chart" link="/bar"/>
        <ChartLink img={CompareBarImage} label="Bar Chart" link="/bar"/>
      </ul>
    </div>
  );
}

export default App;
