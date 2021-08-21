package com.cliente.microrestaurante.utils.jasperreports;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class RelatorioPdf {

    public static void gerarRelatorioPedido(Long id) {
        String urlDb="jdbc:mysql://localhost:3306/restaurantedb?useTimeZone=true&serverTimezone=America/Sao_Paulo";
        String usuarioDb="root";
        String senhaDb="3885";

        try(Connection conn = DriverManager.getConnection(urlDb, usuarioDb, senhaDb)){
            //InputStream jasperStream = Main.class.getResourceAsStream("relatorio_teste.jasper");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("ID_PEDIDO",id);

            InputStream inputStream = new FileInputStream("src/main/java/com/cliente/microrestaurante/utils/jasperreports/RelatorioPedido.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput("relatorios_gerados/relatorio_pedido_" + id + ".pdf"));
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdfExporter.exportReport();

            System.out.println("Relatório gerado.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
