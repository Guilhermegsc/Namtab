/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.ProdutoDAO;
import dao.UsuarioDAO;
import dao.VendaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Anderson
 */
public class Relatorio {

    public HSSFWorkbook relatorioVenda(Date dtInicio, Date dtFim, int idFilial) {

        //cria planilha
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet firstSheet = workbook.createSheet("VENDAS");

        try {

            //Formatando a fonte
            HSSFFont fonte = workbook.createFont();
            fonte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle estilo = workbook.createCellStyle();
            estilo.setFillBackgroundColor(HSSFColor.BLACK.index);
            estilo.setFont(fonte);
            HSSFRow row = firstSheet.createRow(0);

            // lista para criar cabecalho
            String[] cabecalho = {"ID_VENDA", "NOME_FILIAL", "NOME_USUARIO", "NOME_PRODUTO",
                "PRECO", "QUANTIDADE", "VALOR_VENDA", "DATA", "ID_FILIAL", "ID_USUARIO", "ID_PRODUTO"};

            // popula cabecalho
            for (int i = 0; i < cabecalho.length; i++) {

                firstSheet.setColumnWidth((short) (i), (short) (4600));
                Cell cell = row.createCell(i);
                cell.setCellStyle(estilo);
                cell.setCellValue(cabecalho[i]);

            }

            // Este trecho obtem uma lista de objetos
            // do banco de dados através de um DAO e itera sobre a lista
            // criando linhas e colunas em um arquivo Excel com o conteúdo
            // dos objetos.
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            ArrayList<Produto> lista;

            VendaDAO bd = new VendaDAO();
            
            // verifica filial
            if (idFilial == 1) {
            lista = bd.listarTodasVendas(dtInicio, dtFim);}
            else {
            lista = bd.listarVendasFilial(dtInicio, dtFim, idFilial);
            }

            int i = 1;

            for (Produto pro : lista) {
                // nova linha na planilha
                row = firstSheet.createRow(i);
                // altera data para string
                String data = sdf.format(pro.getDataVenda());

                row.createCell(0).setCellValue(pro.getIdVenda());
                row.createCell(1).setCellValue(pro.getNomeFilial());
                row.createCell(2).setCellValue(pro.getNomeUsuario());
                row.createCell(3).setCellValue(pro.getNomeProduto());
                row.createCell(4).setCellValue(pro.getPreco());
                row.createCell(5).setCellValue(pro.getQuantidade());
                row.createCell(6).setCellValue(pro.getValorVenda());
                row.createCell(7).setCellValue(data);
                row.createCell(8).setCellValue(pro.getIdFilial());
                row.createCell(9).setCellValue(pro.getIdUsuario());
                row.createCell(10).setCellValue(pro.getIdProduto());

                i++;

            } // fim do for

            firstSheet.setAutoFilter(CellRangeAddress.valueOf("A1:K" + i));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao exportar arquivo");
        }

        return workbook;
    }

    public HSSFWorkbook relatorioUsuario() {

        //cria planilha
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet firstSheet = workbook.createSheet("USUARIO");

        try {

            //Formatando a fonte
            HSSFFont fonte = workbook.createFont();
            fonte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle estilo = workbook.createCellStyle();
            estilo.setFillBackgroundColor(HSSFColor.BLACK.index);
            estilo.setFont(fonte);
            HSSFRow row = firstSheet.createRow(0);

            // lista para criar cabecalho
            String[] cabecalho = {"ID_USUARIO", "NOME_USUARIO", "ID_FILIAL", "FUNCAO", "TIPO_PERFIL"};

            // popula cabecalho
            for (int i = 0; i < cabecalho.length; i++) {

                firstSheet.setColumnWidth((short) (i), (short) (4600));
                Cell cell = row.createCell(i);
                cell.setCellStyle(estilo);
                cell.setCellValue(cabecalho[i]);

            }

            // Este trecho obtem uma lista de objetos
            // do banco de dados através de um DAO e itera sobre a lista
            // criando linhas e colunas em um arquivo Excel com o conteúdo
            // dos objetos.
            ArrayList<Usuario> lista;

            UsuarioDAO bd = new UsuarioDAO();
            lista = bd.listarUsuarios();

            int i = 1;

            for (Usuario user : lista) {
                // nova linha na planilha
                row = firstSheet.createRow(i);
                // altera data para string

                row.createCell(0).setCellValue(user.getIdUsuario());
                row.createCell(1).setCellValue(user.getNome());
                row.createCell(2).setCellValue(user.getIdFilial());
                row.createCell(3).setCellValue(user.getFuncao());
                row.createCell(4).setCellValue(user.getTipoPerfil());

                i++;

            } // fim do for

            firstSheet.setAutoFilter(CellRangeAddress.valueOf("A1:E" + i));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao exportar arquivo");
        }
        return workbook;
    }

    public HSSFWorkbook relatorioProduto() {

        //cria planilha
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet firstSheet = workbook.createSheet("PRODUTO");

        try {

            //Formatando a fonte
            HSSFFont fonte = workbook.createFont();
            fonte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle estilo = workbook.createCellStyle();
            estilo.setFillBackgroundColor(HSSFColor.BLACK.index);
            estilo.setFont(fonte);
            HSSFRow row = firstSheet.createRow(0);

            // lista para criar cabecalho
            String[] cabecalho = {"ID_PRODUTO", "NOME_PROODUTO", "PRECO", "CATEGORIA", "TAMANHO", "TIPO"};

            // popula cabecalho
            for (int i = 0; i < cabecalho.length; i++) {

                firstSheet.setColumnWidth((short) (i), (short) (4600));
                Cell cell = row.createCell(i);
                cell.setCellStyle(estilo);
                cell.setCellValue(cabecalho[i]);

            }

            // Este trecho obtem uma lista de objetos
            // do banco de dados através de um DAO e itera sobre a lista
            // criando linhas e colunas em um arquivo Excel com o conteúdo
            // dos objetos.
            ArrayList<Produto> lista;
            int a = 0;
            ProdutoDAO bd = new ProdutoDAO();
            lista = bd.listaProduto();

            int i = 1;

            for (Produto prod : lista) {
                // nova linha na planilha
                row = firstSheet.createRow(i);

                row.createCell(0).setCellValue(prod.getIdProduto());
                row.createCell(1).setCellValue(prod.getNomeProduto());
                row.createCell(2).setCellValue(prod.getPreco());
                
                if (prod instanceof Combustivel) {
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue("");
                    row.createCell(5).setCellValue(((Combustivel) prod).getTipo());
                }else if(prod instanceof OleoLubrificante){
                    row.createCell(3).setCellValue("");
                    row.createCell(4).setCellValue(((OleoLubrificante) prod).getTamanho());
                    row.createCell(5).setCellValue(((OleoLubrificante) prod).getTipo());
                }else if(prod instanceof Extintor){
                    row.createCell(3).setCellValue(((Extintor) prod).getCategoria());
                    row.createCell(4).setCellValue(((Extintor) prod).getTamanho());
                    row.createCell(5).setCellValue(((Extintor) prod).getTipo());
                }

                /*switch (prod.getTipo()) {
                    // lista comb
                    case "COMB":
                        Combustivel com = new Combustivel();
                        row.createCell(3).setCellValue("");
                        row.createCell(4).setCellValue("");
                        row.createCell(5).setCellValue(com.getTipo());

                        break;
                    // lista prod    
                    case "PROD":
                        if (prod.getIdProduto() == 5) {  // lista oleo 
                            OleoLubrificante ol = new OleoLubrificante();
                            row.createCell(3).setCellValue("");
                            row.createCell(4).setCellValue(ol.getTamanho());
                            row.createCell(5).setCellValue("");
                            break;
                        } else if (prod.getIdProduto() == 6) {  // lista extintor
                            Extintor ext = new Extintor();
                            row.createCell(3).setCellValue(ext.getCategoria());
                            row.createCell(4).setCellValue(ext.getTamanho());
                            row.createCell(5).setCellValue("");
                            break;
                        }
                    default:
                        break;

                }*/

                i++;

            } // fim do for

            firstSheet.setAutoFilter(CellRangeAddress.valueOf("A1:F" + i));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao exportar arquivo");
        }

        return workbook;
    }

}
