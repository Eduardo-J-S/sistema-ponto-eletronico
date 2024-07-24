# sistema-ponto-eletronico

```mermaid
classDiagram
    class Empresa {
        + int id_empresa
        + String nome
        + String cnpj
        + String telefone
        + String celular
        + String nome_dono
    }

    class Funcionario {
        + int id_funcionario
        + String nome
        + String cpf
        + Date aniversario
        + String email
        + String celular
        + String setor
        + boolean status
        + String senha
        + int empresa_id_empresa
    }

    class UsuarioAdmin {
        + int id_usuario_admin
        + String nome_admin
        + String senha_admin
        + Timestamp data_alteracao
        + String usuario_admincol
        + int empresa_id_empresa
    }

    class Feriado {
        + int id_feriado
        + String descricao
        + Date data
        + int empresa_id_empresa
    }

    class RegistroPonto {
        + int id_registro
        + DateTime data_hora_registro
        + String tipo_registro
        + int funcionario_id_funcionario
    }

    class Permissao {
        + int id_permissao
        + String descricao
        + Date data_inicio
        + Date data_fim
        + int funcionario_id_funcionario
    }

    class HorarioTrabalho {
        + int id_horario_trabalho
        + Enum dia_semana
        + Date hora_inicio
        + Date hora_fim
    }

    class Turno {
        + int id_turno
        + String descricao
        + Date hora_inicio
        + Date hora_fim
        + int funcionario_id_funcionario
    }

    class Ausencia {
        + int id_ausencia
        + String tipo_ausencia
        + Date data_inicio
        + Date data_fim
        + int funcionario_id_funcionario
    }

    class FuncionarioHorario {
        + int id_funcionario_horario
        + int funcionario_id_funcionario
        + int horario_trabalho_id_horario_trabalho
    }

    Empresa "1" --> "*" Funcionario : "tem"
    Empresa "1" --> "*" UsuarioAdmin : "tem"
    Empresa "1" --> "*" Feriado : "tem"
    Funcionario "1" --> "*" RegistroPonto : "faz"
    Funcionario "1" --> "*" Permissao : "tem"
    Funcionario "1" --> "*" Turno : "está em"
    Funcionario "1" --> "*" Ausencia : "tem"
    Funcionario "1" --> "*" FuncionarioHorario : "tem"
    HorarioTrabalho "1" --> "*" FuncionarioHorario : "tem"

```