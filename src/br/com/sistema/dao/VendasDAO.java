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


public class VendasDAO {
      private Conexao conexao;
    private Connection conn;

    public VendasDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void salvarVenda(Vendas obj){
        try{
            String sql = "insert into vendas (ven_id, ven_cliente_id, ven_data_venda, ven_total_Venda) values (?,?,?,?)";
            
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
    
    public int retornaUltimoIdVenda() {
    try {
        int ultimoId = 0;
        String sql = "SELECT MAX(ven_id) AS id FROM vendas";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            ultimoId = rs.getInt("id"); // nome da coluna do alias
        }

        return ultimoId;
    } catch (Exception e) {
        e.printStackTrace(); // 🔥 Mostra o erro original no console
        throw new RuntimeException("Erro ao buscar último ID da venda: " + e.getMessage());
    }
}
    
    public List<Vendas> listarVendas() {
    List<Vendas> lista = new ArrayList<>();
    try {
        String sql = "SELECT v.ven_id, v.ven_data_venda, v.ven_total_venda, c.cli_nome " +
                     "FROM vendas AS v INNER JOIN cliente AS c ON v.ven_cliente_id = c.cli_id";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Vendas venda = new Vendas();
            ClienteC cliente = new ClienteC();

            venda.setId(rs.getInt("ven_id"));
            venda.setDataVenda(rs.getString("ven_data_venda"));
            venda.setTotalVenda(rs.getDouble("ven_total_venda"));

            cliente.setNome(rs.getString("cli_nome"));
            venda.setClientes(cliente);

            lista.add(venda);
        }

        return lista;

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    
    
    
}
