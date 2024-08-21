import React from "react";
import { BrowserRouter, Routes, Link, Route } from "react-router-dom";

import ContextProvider from "./contexts";
import Login from "./pages/login";
import Register from "./pages/register";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <ContextProvider>
          <Routes>
            <Route path='/' element={ <Login />}/>
            <Route path='/register' element={ <Register />}/>
            <Route path='/about' />
          </Routes>
          </ContextProvider>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
