import React from "react";
import "./styles.css";
import { Link } from "react-router-dom";

const Login = () => {
  return (
    <div className="login-container-login">
      <div className="login-form-login">
        <div className="logo-login">
          <h1>
            Don Don <span>#</span>
          </h1>
        </div>
        <div className="input-container-login">
          <input type="text" placeholder="Matrícula" />
          <input type="password" placeholder="Senha" />
        </div>
        <nav className="register-container-login">
          <p>Não possui uma conta? <Link to="/register" className="register-link-login">Registrar empresa</Link></p>
        </nav>
        <button className="button-login">ENTRAR</button>
      </div>
      <div className="login-image-login"></div>
    </div>
  );
};

export default Login;
