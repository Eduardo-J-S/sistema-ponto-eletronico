import React, { useState } from 'react';
import { FaQuestionCircle, FaPhoneAlt, FaEnvelope, FaBook, FaBars } from 'react-icons/fa';
import './styles.css';
import Drawer from '../../components/Drawer';

const Ajuda = () => {
    const [isDrawerOpen, setIsDrawerOpen] = useState(false);

    const toggleDrawer = () => {
      setIsDrawerOpen(!isDrawerOpen);
    };
  return (
    <div className="ajuda-container">
        <div className="drawer-container">
        <Drawer isOpen={isDrawerOpen} onClose={() => setIsDrawerOpen(false)} />
        {!isDrawerOpen && (
          <FaBars className="menu-icon" onClick={toggleDrawer} />
        )}
      <header className="ajuda-header">
        <h1>Central de Ajuda</h1>
        <p>Encontre respostas e suporte para utilizar o sistema de forma eficiente.</p>
      </header>

      <section className="ajuda-section">
        <h2><FaBook className="ajuda-icon" /> Como Usar o Sistema</h2>
        <ol>
          <li><strong>Cadastro:</strong> Clique em "Registrar" no canto superior direito e preencha suas informações.</li>
          <li><strong>Login:</strong> Use seu e-mail e senha cadastrados para acessar o sistema.</li>
          <li><strong>Dashboard:</strong> Após o login, você será direcionado ao painel de controle, onde poderá gerenciar suas atividades.</li>
          <li><strong>Suporte:</strong> Se precisar de ajuda, acesse a seção de suporte através do menu principal.</li>
        </ol>
      </section>

      <section className="ajuda-section">
        <h2><FaQuestionCircle className="ajuda-icon" /> Perguntas Frequentes (FAQ)</h2>
        <div className="faq-item">
          <h3>Como posso redefinir minha senha?</h3>
          <p>Para redefinir sua senha, clique em "Esqueci minha senha" na página de login e siga as instruções enviadas para seu e-mail.</p>
        </div>
        <div className="faq-item">
          <h3>Como atualizo minhas informações de perfil?</h3>
          <p>Acesse o seu perfil através do menu superior e clique em "Editar Perfil" para atualizar suas informações pessoais.</p>
        </div>
        <div className="faq-item">
          <h3>Como posso entrar em contato com o suporte?</h3>
          <p>Você pode entrar em contato com nossa equipe de suporte através dos canais listados na seção de Contato abaixo.</p>
        </div>
      </section>

      <section className="ajuda-section">
        <h2><FaPhoneAlt className="ajuda-icon" /> Suporte e Contato</h2>
        <p>Se você não encontrou a resposta que procurava, entre em contato conosco através dos seguintes canais:</p>
        <ul>
          <li><FaPhoneAlt className="contact-icon" /> <strong>Telefone:</strong> (11) 1234-5678</li>
          <li><FaEnvelope className="contact-icon" /> <strong>E-mail:</strong> suporte@empresa.com</li>
        </ul>
      </section>

      <footer className="ajuda-footer">
        <p>&copy; {new Date().getFullYear()} Sua Empresa. Todos os direitos reservados.</p>
      </footer>
      </div>
    </div>
  );
};

export default Ajuda;
