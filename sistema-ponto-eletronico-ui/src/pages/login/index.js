import React, { useContext, useState } from "react";
import "./styles.css";
import { Link, useNavigate } from "react-router-dom";
import { PerfilContext } from "../../contexts";

const Login = () => {
  const { login } = useContext(PerfilContext);
  const [matricula, setMatricula] = useState("");
  const [senha, setSenha] = useState("");
  const [matriculaValid, setMatriculavalid] = useState(true);
  const [senhaValid, setSenhaValid] = useState(true);
  const navigate = useNavigate();

  async function handleLogin() {
    if (matricula.trim() === "") {
      setMatriculavalid(false);
      return;
    } else {
      setSenhaValid(true);
    }

    if (senha.trim() === "") {
      setSenhaValid(false);
      return;
    } else {
      setSenhaValid(true);
    }

    const result = await login(matricula, senha);
    if (result.success) {
      navigate("/registro");
    }
  }

  return (
    <div className="login-container-login">
      <div className="login-form-login">
        <div className="logo-login">
          <h1>
            Don Don <span>#</span>
          </h1>
        </div>
        <div className="input-container-login">
          <input
            type="text"
            placeholder="Matrícula"
            required
            className={`fieldMatricula ${matriculaValid ? "valid" : "invalid"}`}
            value={matricula}
            onChange={(e) => setMatricula(e.target.value)}
          />
          <input
            type="password"
            placeholder="Senha"
            required
            className={`fieldSenha ${senhaValid ? "valid" : "invalid"}`}
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
          />
        </div>
        {/* Link para "Esqueceu sua senha?" */}
        <div className="forgot-password-container">
          <Link to="/forgot-password" className="forgot-password-link">
            Esqueceu sua senha?
          </Link>
        </div>
        <nav className="register-container-login">
          <p>
            Não possui uma conta?{" "}
            <Link to="/register" className="register-link-login">
              Registrar empresa
            </Link>
          </p>
        </nav>
        <button type="submit" className="button-login" onClick={handleLogin}>
          ENTRAR
        </button>
      </div>
      <div className="login-image-login"></div>
    </div>
  );
};

export default Login;
