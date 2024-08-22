import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import ContextProvider from "./contexts";
import Login from "./pages/Login";
import Register from "./pages/Register";
import RegistroPonto from "./pages/RegistroPonto";
import EspelhoDePonto from "./pages/EspelhoPonto";

function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <ContextProvider>
          <Routes>
            <Route path='/' element={ <Login />}/>
            <Route path='/register' element={ <Register />}/>
            <Route path='/registro' element={<RegistroPonto/>} />
            <Route path='/espelho' element={<EspelhoDePonto/>} />
          </Routes>
          </ContextProvider>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
