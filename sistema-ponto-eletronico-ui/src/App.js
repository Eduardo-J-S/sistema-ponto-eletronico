import React from "react";
import { BrowserRouter } from "react-router-dom";

import ContextProvider from "./contexts";
import Perfil from "./routes";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <ContextProvider>
            <Perfil />
          </ContextProvider>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
