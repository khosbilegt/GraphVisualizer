import './App.css';
import ChartLink from './components/ChartLink';
import FileReader from './components/FileReader';
import BarChartImage from "./assets/images/bar.jpg";

function App() {
  return (
    <div className="App">
      <FileReader />
      <ul>
        <ChartLink img={BarChartImage} label="Bar Chart"/>
        <ChartLink img={BarChartImage} label="Bar Chart"/>
        <ChartLink img={BarChartImage} label="Bar Chart"/>
        <ChartLink img={BarChartImage} label="Bar Chart"/>
        <ChartLink img={BarChartImage} label="Bar Chart"/>
        <ChartLink img={BarChartImage} label="Bar Chart"/>
        <ChartLink img={BarChartImage} label="Bar Chart"/>
      </ul>
    </div>
  );
}

export default App;
