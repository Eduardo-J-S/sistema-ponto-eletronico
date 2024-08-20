import React from 'react';
import './styles.css';

const Home = () => {

    return (
        <div className="login-container">
        <div className="login-form">
          <div className="logo">
            <h1>SuaLogo<span>#</span></h1>
          </div>
          <div className="input-container">
            <input type="text" placeholder="Usuário" />
            <input type="password" placeholder="Senha" />
          </div>
          <button>ENTRAR</button>
        </div>
        <div className="login-image">
          {/* A imagem do laptop vai aqui */}
        </div>
      </div>
    );
}

export default Home;