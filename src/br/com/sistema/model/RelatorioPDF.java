
package br.com.sistema.model;

import br.com.sistema.dao.VendasDAO;
import br.com.sistema.model.Vendas;
 import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author beatr
 */
public class RelatorioPDF {


    public void gerarRelatorioVendas(List<Vendas> listaVendas) {
        Document documento = new Document();
        try {
            // caminho para salvar o arquivo
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Salvar relatório PDF");
            chooser.setSelectedFile(new java.io.File("relatorio_vendas.pdf"));
            int userSelection = chooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String caminho = chooser.getSelectedFile().getAbsolutePath();
                    PdfWriter.getInstance(documento, new FileOutputStream(caminho));
                }

            documento.open();

            
            Paragraph titulo = new Paragraph("Relatório de Vendas",
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.ORANGE));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            documento.add(new Paragraph(" ")); // espaço
            documento.add(new Paragraph("Data de geração: " + new java.util.Date()));
            documento.add(new Paragraph(" "));

            // colunas da tabela
            PdfPTable tabela = new PdfPTable(4);
            tabela.addCell("ID");
            tabela.addCell("Cliente");
            tabela.addCell("Data");
            tabela.addCell("Total");
 
            for (Vendas v : listaVendas) {
                tabela.addCell(String.valueOf(v.getId()));
                tabela.addCell(v.getClientes().getNome());
                tabela.addCell(v.getDataVenda());
                tabela.addCell(String.format("R$ %.2f", v.getTotalVenda()));
            }

            documento.add(tabela);
            documento.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
