import React from "react";
import { BrowserRouter, Routes, Link, Route } from "react-router-dom";

import ContextProvider from "./contexts";
import Home from "./pages/login";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <ContextProvider>
          <Routes>
            <Route path='/login' element={ <Home />}/>
            <Route path='/products'/>
            <Route path='/about' />
          </Routes>
          </ContextProvider>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
