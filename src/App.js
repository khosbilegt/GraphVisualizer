import './App.css';
import React, { useEffect } from 'react';
import ChartLink from './components/ChartLink';
import Navbar from './components/Navbar';
import CompareBarImage from "./assets/images/compare_bar.png";
import StackedBarImage from "./assets/images/stacked_bar.png";
import PieImage from "./assets/images/pie.png";
import DonutImage from "./assets/images/donut.png";
import LineImage from "./assets/images/line.png";
import SplineImage from "./assets/images/spline.png";

function App() {
  useEffect(() => {
    document.title = 'Home'; // Set the document title
  }, []);

  return (
    <div className="App">
      <Navbar />
      <ul>
        <ChartLink img={CompareBarImage} label="Bar Chart (Compare)" link="/compare-bar"/>
        <ChartLink img={StackedBarImage} label="Bar Chart (Stacked)" link="/stacked-bar"/>
        <ChartLink img={PieImage} label="Pie Chart" link="/pie"/>
        <ChartLink img={DonutImage} label="Donut Chart" link="/donut"/>
        <ChartLink img={LineImage} label="Line Chart" link="/line"/>
        <ChartLink img={SplineImage} label="Spline Chart" link="/spline"/>
      </ul>
    </div>
  );
}

export default App;
