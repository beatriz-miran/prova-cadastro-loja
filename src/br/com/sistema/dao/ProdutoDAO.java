package br.com.sistema.dao;

    
import br.com.sistema.model.Conexao;
import br.com.sistema.model.ProdutoC;
import br.com.sistema.model.FornecedorC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(ProdutoC produto) {
        String sql = "INSERT INTO Produto(pro_id, pro_nome, pro_descricao, pro_preco, pro_estoque, for_id) VALUES(?, ?, ?, ?, ?, ?);";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, Integer.toString(produto.getId()));
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescr());
            stmt.setString(4, Float.toString(produto.getPreco()));
            stmt.setString(5, Integer.toString(produto.getQtdEst()));
            stmt.setInt(6, produto.getFornecedor().getId());

            stmt.execute();

        }catch(SQLException ex){System.out.println("Erro ao insetir produto: " + ex.getMessage());
        }
    }    
        
        public List<ProdutoC> listar(){
        List<ProdutoC> lista = new ArrayList<>();
            try{
                String sql = "select p.pro_id, p.pro_nome, p.pro_descricao, p.pro_preco, p.pro_estoque,f.for_nome from produto as p inner join fornecedor as f on(p.for_id = f.for_id);";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    ProdutoC obj = new ProdutoC();
                    FornecedorC f = new FornecedorC();
                    
                    obj.setId(rs.getInt("p.pro_id"));
                    obj.setNome(rs.getString("p.pro_nome"));
                    obj.setDescr(rs.getString("p.pro_descricao"));
                    obj.setQtdEst(rs.getInt("p.pro_estoque"));
                    obj.setPreco(rs.getFloat("p.pro_preco"));
                    f.setNome(rs.getString("f.for_nome"));
                    obj.setFornecedor(f);
                    lista.add(obj);
                }
                return lista;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "erro ao criar a lista" + e);
            }
            return null;
        }
        
        public ProdutoC buscarProdutoCodigo(int id){
            String sql = "SELECT  * FROM Produto WHERE pro_id = ?";
            try{
                PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                ProdutoC p = new ProdutoC();
                
                rs.first();
                p.setId(id);
                p.setNome(rs.getString("pro_nome"));
                p.setDescr(rs.getString("pro_descricao"));
                p.setPreco(rs.getFloat("pro_preco"));
                p.setQtdEst(rs.getInt("pro_estoque"));
                
                return p;
                        
            }catch(SQLException ex){
                System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
                return null;
            }
        }
        
        public ProdutoC buscarProdutoNome(String nome){
            String sql = "SELECT  * FROM Produto WHERE pro_nome = ?";
            try{
                PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.setString(1, nome);
                ResultSet rs = stmt.executeQuery();
                
                ProdutoC p = new ProdutoC();
                
                rs.first();
                p.setNome(rs.getString("pro_nome"));
                p.setId(rs.getInt("pro_id"));
                p.setDescr(rs.getString("pro_descricao"));
                p.setPreco(rs.getFloat("pro_preco"));
                p.setQtdEst(rs.getInt("pro_estoque"));
                
                
                return p;
                        
            }catch(SQLException ex){
                System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
                return null;
            }
        }
        
        public int retornaQtdEstoque(int id){
            try{
                int qtdAtual = 0;
                String sql = "select pro_estoque from produto where pro_id = ?";
                
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                if(rs.next()){
                    qtdAtual = (rs.getInt("pro_estoque"));
                }return qtdAtual;
            }catch(Exception e){
                throw new RuntimeException("Erro" + e);
            }
        }
        
        public void baixaEstoque(int id, int qtdAtl) {
            try {
                String sql = "UPDATE produto SET pro_estoque = ? WHERE pro_id = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, qtdAtl);
                stmt.setInt(2, id);

                stmt.executeUpdate();

                System.out.println("Estoque atualizado com sucesso para o produto ID: " + id);

            } catch (SQLException e) {
                System.err.println("Erro ao atualizar o estoque: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao atualizar o estoque: " + e.getMessage());

            }
}

}
