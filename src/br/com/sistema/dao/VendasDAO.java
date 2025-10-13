package br.com.sistema.dao;


import br.com.sistema.model.ClienteC;
import br.com.sistema.model.Conexao;
import br.com.sistema.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author beatr
 */
public class VendasDAO {
      private Conexao conexao;
    private Connection conn;

    public VendasDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void salvarVenda(Vendas obj){
        try{
            String sql = "insert into vendas (id, cliente_id, data_venda, total_Venda) values (?,?,?,?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId());
            stmt.setInt(2, obj.getClientes().getId());
            stmt.setString(3, obj.getDataVenda());
            stmt.setDouble(4, obj.getTotalVenda());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso");
        }catch(Exception e){
            throw new RuntimeException("Erro ao realizar a venda" + e);
        }
    }
    
    public int retornaUltimoIdVenda(){
        try{
            int ultimoId = 0;
            String sql = "select max(id) id from vendas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Vendas v = new Vendas();
                v.setId(rs.getInt("id"));
                ultimoId = v.getId();
            }return ultimoId;
        }catch(Exception e){
            throw new RuntimeException("Erro");
        }
    }
    
    
    
}
