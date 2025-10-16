package br.com.sistema.dao;


import br.com.sistema.model.Conexao;
import br.com.sistema.model.ItensVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ItensVendaDAO {
    private Conexao conexao;
    private Connection conn;

    public ItensVendaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void salvar(ItensVendas obj){
        try{
            String sql = "insert into itensvendas(itm_venda_id, itm_produto_id, itm_qtd, itm_subtotal)values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getVendas().getId());
            stmt.setInt(2, obj.getProdutos().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setFloat(4, obj.getSubtotal());
            
            stmt.execute();
            stmt.close();
        }catch(Exception e){
           e.printStackTrace(); // mostra o erro no console
    throw new RuntimeException("Erro ao salvar item da venda: " + e.getMessage());
        }
    }
}
