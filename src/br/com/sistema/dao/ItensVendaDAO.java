package br.com.sistema.dao;


import br.com.sistema.model.Conexao;
import br.com.sistema.model.ItensVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author beatr
 */
public class ItensVendaDAO {
    private Conexao conexao;
    private Connection conn;

    public ItensVendaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void salvar(ItensVendas obj){
        try{
            String sql = "insert into itensvendas(venda_id, produto_id, qtd, subtotal)values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getVendas().getId());
            stmt.setInt(2, obj.getProdutos().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setFloat(4, obj.getSubtotal());
            
            stmt.execute();
            stmt.close();
        }catch(Exception e){
            throw new RuntimeException("Erro");
        }
    }
}
