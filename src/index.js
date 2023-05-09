import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './index.css';
import App from './App';
import CompareBarChart from './charts/CompareBarChart';
import PieChart from './charts/PieChart';
import StackedBarChart from './charts/StackedBarChart';
import DonutChart from './charts/DonutChart';
import LineChart from './charts/LineChart';
import SplineChart from './charts/SplineChart';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/">
          <Route index element={<App />} />
          <Route path="compare-bar" element={<CompareBarChart />} />
          <Route path="stacked-bar" element={<StackedBarChart />} />
          <Route path="pie" element={<PieChart />} />
          <Route path="donut" element={<DonutChart />} />
          <Route path="line" element={<LineChart />} />
          <Route path="spline" element={<SplineChart />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
