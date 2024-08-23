import React, { useState } from 'react';
import { FaBars } from 'react-icons/fa';
import Drawer from '../../components/Drawer';
import './styles.css';

const AlterarSenha = () => {
  const [formData, setFormData] = useState({
    nome: '',
    telefone: '',
    email: '',
    senha: '',
  });
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const toggleDrawer = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(JSON.stringify(formData)); // Aqui você pode substituir para enviar o JSON ao backend
  };

  return (
    <div className="alterar-senha-page">
      <Drawer isOpen={isDrawerOpen} onClose={() => setIsDrawerOpen(false)} />
      <div className="content">
      {!isDrawerOpen && (
          <FaBars
            className="menu-icon"
            onClick={toggleDrawer}
          />
        )}
        <h1>Alterar Senha</h1>
        <form onSubmit={handleSubmit} className="alterar-senha-form">
          <label htmlFor="nome">Nome</label>
          <input
            type="text"
            id="nome"
            name="nome"
            value={formData.nome}
            onChange={handleChange}
            required
          />

          <label htmlFor="telefone">Telefone</label>
          <input
            type="tel"
            id="telefone"
            name="telefone"
            value={formData.telefone}
            onChange={handleChange}
            required    
          />

          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <label htmlFor="senha">Senha</label>
          <input
            type="password"
            id="senha"
            name="senha"
            value={formData.senha}
            onChange={handleChange}
            required
          />

          <button type="submit" className="botao-alterar-senha">Alterar Senha</button>
        </form>
      </div>
    </div>
  );
};

export default AlterarSenha;
