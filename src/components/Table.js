import React from 'react';
import './Table.css';

function Table({ data }) {
  return (
    <table className="Table">
      <tbody>
        {data.map((row, i) => (
          <tr key={i}>
            {row.map((cell, j) => (
              <td key={`${i}-${j}`}>{cell}</td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default Table;