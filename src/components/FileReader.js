import React, { useState } from 'react';

function FileReader() {
     const [fileContents, setFileContents] = useState("");

     const handleFileSelect = async (event) => {
          const file = event.target.files[0];
          const text = await file.text();
          setFileContents(text);
     };

     return (
       <div className="FileReader">
          <input type="file" onChange={handleFileSelect} />
          <p>{fileContents}</p>
       </div>
     );
}

export default FileReader;