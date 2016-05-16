/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Relatorio {
 
    public void expExcel(String nomeArquivo) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet firstSheet = workbook.createSheet("VENDAS");

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File(nomeArquivo));

// Este trecho obtem uma lista de objetos do tipo CD
// do banco de dados através de um DAO e itera sobre a lista
// criando linhas e colunas em um arquivo Excel com o conteúdo
// dos objetos.
            ArrayList<Produto> lista = new ArrayList<>();
            
            Produto prod = new Produto(1, 1, 1, 999999999, "ANDERSON", "GASOLINA", 3.100, null, 42.10, 152.0, "SAO PAULO");

            lista.add(prod);
            
            int i = 0;

            for (Produto pro : lista) {
                HSSFRow row = firstSheet.createRow(i);

                row.createCell(0).setCellValue(pro.getIdVenda());
                row.createCell(1).setCellValue(pro.getIdFilial());
                row.createCell(2).setCellValue(pro.getIdUsuario());
                row.createCell(3).setCellValue(pro.getIdProduto());
                row.createCell(4).setCellValue(pro.getNomeUsuario());
                row.createCell(5).setCellValue(pro.getNomeProduto());
                row.createCell(6).setCellValue(pro.getPreco());
                row.createCell(7).setCellValue("DATA");
                row.createCell(8).setCellValue(pro.getQuantidade());
                row.createCell(9).setCellValue(pro.getValorVenda());
                row.createCell(10).setCellValue(pro.getNomeFilial());

                i++;

            } // fim do for

            workbook.write(fos);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao exportar arquivo");
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } // fim do metodo exp

    public static void main(String[] args) {
        
        Relatorio re = new Relatorio();
        
        re.expExcel("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\PRODUTOS ESTOQUE.xls");
    }
   
 
}
