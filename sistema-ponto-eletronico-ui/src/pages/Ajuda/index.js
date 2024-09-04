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
            </div>
            <header className="ajuda-header">
                <h1>Ajuda do Sistema de Ponto Eletrônico</h1>
                <p>Encontre respostas e suporte para utilizar o sistema de forma eficiente.</p>
            </header>

            <section className="ajuda-section">
                <h2><FaBook className="ajuda-icon" /> Como Usar o Sistema</h2>
                <ol>
                    <li><strong>Registro de Ponto:</strong> Acesse a página de registro e insira seu CPF e senha para registrar seu ponto.</li>
                    <li><strong>Espelho de Ponto:</strong> Consulte seus registros de ponto e verifique o total de horas trabalhadas.</li>
                    <li><strong>Justificativas:</strong> Submeta justificativas para alterações nos horários de trabalho, caso necessário.</li>
                    <li><strong>Alterar Senha:</strong> Atualize sua senha regularmente através da página de alteração de senha.</li>
                </ol>
            </section>

            <section className="ajuda-section">
                <h2><FaQuestionCircle className="ajuda-icon" /> Perguntas Frequentes (FAQ)</h2>
                <div className="faq-item">
                    <h3>Como posso registrar meu ponto?</h3>
                    <p>Para registrar seu ponto, acesse a página de registro, insira seu CPF e senha, e clique em "Registrar Ponto".</p>
                </div>
                <div className="faq-item">
                    <h3>O que faço se esquecer de registrar meu ponto?</h3>
                    <p>Se esquecer de registrar seu ponto, entre em contato com o administrador para ajustar o registro.</p>
                </div>
                <div className="faq-item">
                    <h3>Como acesso meu espelho de ponto?</h3>
                    <p>Você pode acessar seu espelho de ponto através da seção "Espelho de Ponto" no menu principal.</p>
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
    );
};

export default Ajuda;
