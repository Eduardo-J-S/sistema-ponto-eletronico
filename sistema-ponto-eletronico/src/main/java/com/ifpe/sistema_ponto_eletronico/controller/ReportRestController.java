package com.ifpe.sistema_ponto_eletronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifpe.sistema_ponto_eletronico.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

@RestController
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response,
                                    @RequestParam String cpf,
                                    @RequestParam LocalDate dataInicio,
                                    @RequestParam LocalDate dataFim,
                                    @RequestParam String status) throws Exception {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=espelho_ponto.xls";

        response.setHeader(headerKey, headerValue);

        reportService.generateExcel(response, cpf, dataInicio, dataFim, status);

        response.flushBuffer();
    }
}
