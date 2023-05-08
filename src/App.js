import './App.css';
import React, { useEffect } from 'react';
import ChartLink from './components/ChartLink';
import Navbar from './components/Navbar';
import CompareBarImage from "./assets/images/compare_bar.png";
import StackedBarImage from "./assets/images/stacked_bar.png";
import PieImage from "./assets/images/pie.png";

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
      </ul>
    </div>
  );
}

export default App;
