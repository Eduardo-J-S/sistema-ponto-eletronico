package com.ifpe.sistema_ponto_eletronico.service;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.Horario;
import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;
import com.ifpe.sistema_ponto_eletronico.repository.RegistroPontoRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.time.LocalTime;

@Service
public class ReportService {

    @Autowired
    private RegistroPontoRepository registroPontoRepo;

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    public void generateExcel(HttpServletResponse response, String cpf, LocalDate startDate, LocalDate endDate, String status) throws Exception {
        // Converta LocalDate para LocalDateTime ao início e fim do dia
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        
        // Recupera o funcionário com base no CPF e status
        Funcionario funcionario = funcionarioRepo.findByCpfAndStatus(cpf, status);

        if (funcionario == null) {
            throw new Exception("Funcionário não encontrado ou não está com o status informado");
        } else {
            System.out.println("Funcionário encontrado: " + funcionario.getNome());
        }

        // Recupera os registros de ponto para o funcionário dentro do período especificado
        List<RegistroPonto> registros = registroPontoRepo.findByFuncionarioAndDataHoraBetween(funcionario, startDateTime, endDateTime);
        if (registros.isEmpty()) {
            System.out.println("Nenhum registro de ponto encontrado para o período especificado.");
        } else {
            System.out.println("Registros de ponto encontrados: " + registros.size());
        }

        // Recupera os horários do funcionário
        Set<Horario> horarios = funcionario.getHorarios();
        System.out.println(horarios.size());
        horarios.forEach(horario -> System.out.println(horario + " Horarios"));

        // Cria o workbook e a planilha
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Espelho de Ponto");
        HSSFRow headerRow = sheet.createRow(0);

        // Cria as colunas de cabeçalho
        headerRow.createCell(0).setCellValue("Dia da Semana");
        headerRow.createCell(1).setCellValue("Hora Início");
        headerRow.createCell(2).setCellValue("Hora Fim");
        headerRow.createCell(3).setCellValue("Data/Hora Registro");
        headerRow.createCell(4).setCellValue("Tipo Registro");
        headerRow.createCell(5).setCellValue("Nome");
        headerRow.createCell(6).setCellValue("Matricula");
        headerRow.createCell(7).setCellValue("CPF");

        int dataRowIndex = 1;

        // Preenche a planilha com os dados dos registros de ponto e horários correspondentes
        for (RegistroPonto registro : registros) {
            boolean horarioCorrespondenteEncontrado = false;
            
            for (Horario horario : horarios) {
                System.out.println("Entra no for de horarios");
                // Verifica se o dia da semana do horário corresponde ao dia da semana do registro de ponto
                if (horario.getDiaSemana().equals(registro.getDiaSemana())) {
                    // Verifica se a hora do registro está dentro do intervalo do horário
                    LocalTime horaRegistro = registro.getDataHora().toLocalTime();

                    System.out.println("Hora Registro: " + horaRegistro);
                    System.out.println("Hora Início: " + horario.getHoraInicio());
                    System.out.println("Hora Fim: " + horario.getHoraFim());
                    if (!horaRegistro.isBefore(horario.getHoraInicio()) && !horaRegistro.isAfter(horario.getHoraFim())) {
                        HSSFRow dataRow = sheet.createRow(dataRowIndex++);
                        dataRow.createCell(0).setCellValue(horario.getDiaSemana().toString());
                        dataRow.createCell(1).setCellValue(horario.getHoraInicio().toString());
                        dataRow.createCell(2).setCellValue(horario.getHoraFim().toString());
                        dataRow.createCell(3).setCellValue(registro.getDataHora().toString());
                        dataRow.createCell(4).setCellValue(registro.getTipoRegistro().toString());
                        dataRow.createCell(5).setCellValue(funcionario.getNome());
                        dataRow.createCell(6).setCellValue(funcionario.getMatricula());
                        dataRow.createCell(7).setCellValue(funcionario.getCpf());
                        horarioCorrespondenteEncontrado = true;
                        break;  // Encontrou o horário correspondente, não precisa verificar os outros
                    }
                }
            }
    
            if (!horarioCorrespondenteEncontrado) {
                System.out.println("Nenhum horário correspondente encontrado para o registro de ponto: " + registro.getDataHora());
            }
        }

        // Escreve o workbook no response output stream
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}
