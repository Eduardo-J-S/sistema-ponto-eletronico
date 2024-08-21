import React, { useState } from "react";
import "./styles.css";
import { Link } from "react-router-dom";

const Register = () => {
  const [formData, setFormData] = useState({
    nomeEmpresa: "",
    cnpj: "",
    telefone: "",
    nomeAdmin: "",
    matriculaAdmin: "",
    senhaAdmin: "",
    cpfAdmin: "",
    emailAdmin: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    // Aqui você pode adicionar a lógica para enviar os dados para o backend.
  };

  return (
    <div className="login-container-register">
      <div className="login-form-register">
        <div className="logo-register">
          <h1>
            Don Don <span>#</span>
          </h1>
        </div>
        <form onSubmit={handleSubmit}>
          <div className="input-container-register">
            <div className="column-register">
              <input
                type="text"
                name="nomeEmpresa"
                placeholder="Nome da Empresa"
                value={formData.nomeEmpresa}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="cnpj"
                placeholder="CNPJ"
                value={formData.cnpj}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="telefone"
                placeholder="Telefone"
                value={formData.telefone}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="nomeAdmin"
                placeholder="Nome do Admin"
                value={formData.nomeAdmin}
                onChange={handleChange}
                required
              />
            </div>
            <div className="column-register">
              <input
                type="text"
                name="matriculaAdmin"
                placeholder="Matrícula do Admin"
                value={formData.matriculaAdmin}
                onChange={handleChange}
                required
              />
              <input
                type="password"
                name="senhaAdmin"
                placeholder="Senha do Admin"
                value={formData.senhaAdmin}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="cpfAdmin"
                placeholder="CPF do Admin"
                value={formData.cpfAdmin}
                onChange={handleChange}
                required
              />
              <input
                type="email"
                name="emailAdmin"
                placeholder="Email do Admin"
                value={formData.emailAdmin}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="button-container-register">
            <button type="submit" className="button-register">Registrar</button>
          </div>
        </form>
        <nav className="register-container-register">
          <p>
            Já possui uma conta? <Link to="/" className="register-link-register">Entrar</Link>
          </p>
        </nav>
      </div>
      <div className="login-image-register"></div>
    </div>
  );
};

export default Register;