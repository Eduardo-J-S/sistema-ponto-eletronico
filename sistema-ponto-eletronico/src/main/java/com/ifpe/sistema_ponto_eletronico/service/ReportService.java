package com.ifpe.sistema_ponto_eletronico.service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpe.sistema_ponto_eletronico.model.Ausencia;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.Horario;
import com.ifpe.sistema_ponto_eletronico.model.Permissao;
import com.ifpe.sistema_ponto_eletronico.model.RegistroPonto;
import com.ifpe.sistema_ponto_eletronico.repository.FuncionarioRepository;
import com.ifpe.sistema_ponto_eletronico.repository.RegistroPontoRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class ReportService {

    @Autowired
    private RegistroPontoRepository registroPontoRepo;

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    public void generateExcel(HttpServletResponse response, String cpf, LocalDate startDate, LocalDate endDate,
            String status) throws Exception {
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

        List<RegistroPonto> registros = registroPontoRepo.findByFuncionarioAndDataHoraBetween(funcionario,
                startDateTime, endDateTime);
        if (registros.isEmpty()) {
            System.out.println("Nenhum registro de ponto encontrado para o período especificado.");
        } else {
            System.out.println("Registros de ponto encontrados: " + registros.size());
        }

        Set<Horario> horarios = funcionario.getHorarios();
        System.out.println(horarios.size());
        horarios.forEach(horario -> System.out.println(horario + " Horarios"));

        Set<Permissao> permissoes = funcionario.getPermissoes();
        List<Permissao> listPermissoes = permissoes == null ? new ArrayList<>() : new ArrayList<>(permissoes);

        Set<Ausencia> ausencias = funcionario.getAusencias();
        List<Ausencia> listAusencias = ausencias == null ? new ArrayList<>() : new ArrayList<>(ausencias);

        // Ordena a lista de permissões por data de início (mais antiga primeiro)
        listPermissoes.sort((p1, p2) -> p1.getDataInicio().compareTo(p2.getDataInicio()));

        // Ordena a lista de ausências por data de início (mais antiga primeiro)
        listAusencias.sort((p1, p2) -> p1.getDataInicio().compareTo(p2.getDataInicio()));

        // Cria o workbook e a planilha
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Espelho de Ponto");

        // Configurar o cabeçalho geral
        createHeader(sheet, funcionario, startDate, endDate);

        // Configurar o cabeçalho fixo da tabela de registros
        createFixedTableHeader(sheet);

        int dataRowIndex = 7; // Índice da primeira linha de dados, após o cabeçalho e os campos fixos

        DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.forLanguageTag("pt-BR"));
        DateTimeFormatter dayOfMonthFormatter = DateTimeFormatter.ofPattern("dd");

        LocalDate currentDate = startDate;
        long totalHoraExtra = 0;
        long totalHoraFalta = 0;

        int permissaoCount = 0;
        Permissao currentPermissao = listPermissoes.isEmpty() ? null : listPermissoes.get(permissaoCount);

        int ausenciaCount = 0;
        Ausencia currentAusencia = listAusencias.isEmpty() ? null : listAusencias.get(ausenciaCount);
        while (!currentDate.isAfter(endDate)) {
            // Formatar o dia da semana e o dia do mês
            String dayOfWeek = currentDate.format(dayOfWeekFormatter);
            String dayOfMonth = currentDate.format(dayOfMonthFormatter);

            HSSFRow dataRow = sheet.createRow(dataRowIndex++);
            dataRow.createCell(0).setCellValue(dayOfWeek);
            dataRow.createCell(1).setCellValue(dayOfMonth);

            boolean horarioCorrespondenteEncontrado = false;

            long resultadoHoraExtra = 0;
            long resultadoHoraFalta = 0;


            // Adicionando permissoes no relatorio
            if (currentPermissao != null && !currentDate.isBefore(currentPermissao.getDataInicio())
                    && !currentDate.isAfter(currentPermissao.getDataFim())) {
                dataRow.createCell(7).setCellValue(currentPermissao.getTipoPermissao().toString());
                System.out.println("Entrou para colocar um registro na coluna de permissão do tipo "
                        + currentPermissao.getTipoPermissao());
            }

            if (currentPermissao != null && currentPermissao.getDataFim().equals(currentDate)) {
                System.out.println("Entrou para somar para a segunda permissão");
                if (listPermissoes.size() > (permissaoCount + 1)) {
                    System.out.println("Entrou para somar o permissaoCount");
                    permissaoCount++;
                    currentPermissao = listPermissoes.get(permissaoCount);
                } else {
                    currentPermissao = null;
                }
            }

            // Adicionando ausencias no relatorio
            if (currentAusencia != null && !currentDate.isBefore(currentAusencia.getDataInicio())
                    && !currentDate.isAfter(currentAusencia.getDataFim())) {
                dataRow.createCell(6).setCellValue(currentAusencia.getTipoAusencia().toString());
            }

            if (currentAusencia != null && currentAusencia.getDataFim().equals(currentDate)) {
                if (listAusencias.size() > (ausenciaCount + 1)) {
                    ausenciaCount++;
                    currentAusencia = listAusencias.get(ausenciaCount);
                } else {
                    currentAusencia = null;
                }
            }

            for (Horario horario : horarios) {
                if (horario.getDiaSemana().equals(currentDate.getDayOfWeek())) {

                    for (RegistroPonto registro : registros) {

                        if (registro.getDataHora().toLocalDate().equals(currentDate)) {

                            if (registro.getTipoRegistro().toString().equals("INICIO")) {
                                dataRow.createCell(2).setCellValue(registro.getDataHora().toLocalTime().toString());
                                if (registro.getDataHora().toLocalTime().isAfter(horario.getHoraInicio())) {
                                    Duration duration = Duration.between(
                                            horario.getHoraInicio(), registro.getDataHora().toLocalTime());

                                    long totalMinutes = duration.toMinutes();

                                    resultadoHoraFalta += totalMinutes;
                                    long hours = resultadoHoraFalta / 60; // Total de horas
                                    long minutes = resultadoHoraFalta % 60; // Minutos restantes

                                    String resultado = String.format("%02d:%02d", hours, minutes);

                                    totalHoraFalta += totalMinutes;

                                    dataRow.createCell(5).setCellValue(resultado);
                                } else if (registro.getDataHora().toLocalTime().isBefore(horario.getHoraInicio())) {
                                    Duration duration = Duration.between(
                                            registro.getDataHora().toLocalTime(), horario.getHoraInicio());

                                    long totalMinutes = duration.toMinutes();

                                    resultadoHoraExtra += totalMinutes;
                                    long hours = resultadoHoraExtra / 60; // Total de horas
                                    long minutes = resultadoHoraExtra % 60; // Minutos restantes

                                    String resultado = String.format("%02d:%02d", hours, minutes);

                                    totalHoraExtra += totalMinutes;

                                    dataRow.createCell(4).setCellValue(resultado);
                                }
                            } else if (registro.getTipoRegistro().toString().equals("FIM")) {
                                dataRow.createCell(3).setCellValue(registro.getDataHora().toLocalTime().toString());

                                if (registro.getDataHora().toLocalTime().isAfter(horario.getHoraFim())) {
                                    Duration duration = Duration.between(
                                            horario.getHoraFim(), registro.getDataHora().toLocalTime());

                                    long totalMinutes = duration.toMinutes();

                                    resultadoHoraExtra += totalMinutes;
                                    long hours = resultadoHoraExtra / 60; // Total de horas
                                    long minutes = resultadoHoraExtra % 60; // Minutos restantes

                                    String resultado = String.format("%02d:%02d", hours, minutes);

                                    totalHoraExtra += totalMinutes;

                                    dataRow.createCell(4).setCellValue(resultado);
                                } else if (registro.getDataHora().toLocalTime().isBefore(horario.getHoraFim())) {
                                    Duration duration = Duration.between(
                                            registro.getDataHora().toLocalTime(), horario.getHoraFim());

                                    long totalMinutes = duration.toMinutes();

                                    resultadoHoraFalta += totalMinutes;
                                    long hours = resultadoHoraFalta / 60; // Total de horas
                                    long minutes = resultadoHoraFalta % 60; // Minutos restantes

                                    String resultado = String.format("%02d:%02d", hours, minutes);

                                    totalHoraFalta += totalMinutes;

                                    dataRow.createCell(5).setCellValue(resultado);
                                }
                            }

                            horarioCorrespondenteEncontrado = true;
                        }
                    }
                }
            }

            if (!horarioCorrespondenteEncontrado) {
                System.out.println("Nenhum horário correspondente encontrado para a data: " + currentDate);
            }

            currentDate = currentDate.plusDays(1);
        }
        // Chama o método para adicionar a linha "Totais"
        adicionarTotais(sheet, dataRowIndex, totalHoraExtra, totalHoraFalta);

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    private void createHeader(HSSFSheet sheet, Funcionario funcionario, LocalDate startDate, LocalDate endDate) {
        HSSFCellStyle titleStyle = sheet.getWorkbook().createCellStyle();
        HSSFFont titleFont = sheet.getWorkbook().createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16); // Aumenta o tamanho da fonte
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // Centraliza o texto

        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("ESPELHO DE PONTO");
        titleCell.setCellStyle(titleStyle);
        // Estilize o título conforme desejado
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

        HSSFRow companyRow = sheet.createRow(1);
        companyRow.createCell(0).setCellValue("COMPANHIA:");
        companyRow.createCell(1).setCellValue("Nome da Empresa");
        companyRow.createCell(4).setCellValue("CNPJ/CPF:");
        companyRow.createCell(5).setCellValue(funcionario.getCpf());

        HSSFRow employeeRow = sheet.createRow(2);
        employeeRow.createCell(0).setCellValue("Nome:");
        employeeRow.createCell(1).setCellValue(funcionario.getNome());
        employeeRow.createCell(4).setCellValue("Matrícula:");
        employeeRow.createCell(5).setCellValue(funcionario.getMatricula());

        HSSFRow departmentRow = sheet.createRow(3);
        departmentRow.createCell(0).setCellValue("Departamento:");
        departmentRow.createCell(1).setCellValue("DEPARTAMENTO");
        departmentRow.createCell(4).setCellValue("Cargo:");
        departmentRow.createCell(5).setCellValue("CARGO");

        HSSFRow periodRow = sheet.createRow(4);
        periodRow.createCell(0).setCellValue("Período:");
        periodRow.createCell(1).setCellValue(startDate + " a " + endDate);
    }

    private void createFixedTableHeader(HSSFSheet sheet) {
        HSSFRow headerRow = sheet.createRow(6);

        headerRow.createCell(0).setCellValue("Dia da Semana");
        headerRow.createCell(1).setCellValue("Data");
        headerRow.createCell(2).setCellValue("INICIO");
        headerRow.createCell(3).setCellValue("FIM");
        headerRow.createCell(4).setCellValue("Hora Extra");
        headerRow.createCell(5).setCellValue("Hora Falta");
        headerRow.createCell(6).setCellValue("Ausência");
        headerRow.createCell(7).setCellValue("Permissão");

        // Definir largura específica para cada coluna (em unidades de 1/256 de largura
        // de caractere)
        sheet.setColumnWidth(0, 5000); // Dia da Semana
        sheet.setColumnWidth(1, 4000); // Data
        sheet.setColumnWidth(2, 3000); // INICIO
        sheet.setColumnWidth(3, 3000); // FIM
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4000);
    }

    private void adicionarTotais(HSSFSheet sheet, int dataRowIndex, long totalHoraExtra, long totalHoraFalta) {
        long hoursHoraExtra = totalHoraExtra / 60;
        long minutesHoraExtra = totalHoraExtra % 60;
        String resultadoHoraExtra = String.format("%02d:%02d", hoursHoraExtra, minutesHoraExtra);

        long hoursHoraFalta = totalHoraFalta / 60;
        long minutesHoraFalta = totalHoraFalta % 60;
        String resultadoHoraFalta = String.format("%02d:%02d", hoursHoraFalta, minutesHoraFalta);

        // Adiciona a linha "Totais" ao final
        HSSFRow totalRow = sheet.createRow(dataRowIndex++);

        // Crie a célula "Totais" na primeira coluna da nova linha
        HSSFCell totalCell = totalRow.createCell(0);
        totalCell.setCellValue("Totais");

        totalRow.createCell(4).setCellValue(resultadoHoraExtra);
        totalRow.createCell(5).setCellValue(resultadoHoraFalta);

        // Estilize a célula "Totais"
        HSSFCellStyle totalStyle = sheet.getWorkbook().createCellStyle();
        HSSFFont totalFont = sheet.getWorkbook().createFont();
        totalFont.setBold(true);
        totalStyle.setFont(totalFont);
        totalCell.setCellStyle(totalStyle);
    }

}