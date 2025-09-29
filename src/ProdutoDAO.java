
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2830482411044
 */
public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(ProdutoC produto) {
        String sql = "INSERT INTO Produto (prod_nome, prod_descr, prod_preco, prod_qtdEst) VALUES(?, ?, ?, ?);";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescr());
            stmt.setString(3, Float.toString(produto.getPreco()));
            stmt.setString(4, Integer.toString(produto.getQtdEst()));

            stmt.execute();

        }catch(SQLException ex){
        System.out.println("Erro ao insetir pessoa: " + ex.getMessage());
        }
    }     
        public ProdutoC getPessoa(int id){
            String sql = "SELECT  * FROM Produto WHERE id = ?";
            try{
                PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                ProdutoC p = new ProdutoC();
                
                rs.first();
                p.setId(id);
                p.setNome(rs.getString("nome"));
                p.setDescr(rs.getString("descr"));
                //p.setPreco(Float.toString(rs.getString("preco")));
                //p.setQtdEst(Integer.toString(rs.getString("qtdEst")));
                
                return p;
                        
            }catch(SQLException ex){
                System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
                return null;
            }
        }
        
        public void editar(ProdutoC produto){
            try{
                String sql = "UPDATE Produto SET nome = ?, sexo = ?, idioma = ? WHERE id = ?";
                
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,produto.getNome());
                stmt.setString(2,produto.getDescr());
               // stmt.setString(3,produto.getPreco());
                stmt.setInt(4, produto.getId());
                
                stmt.execute();
            }
            catch(SQLException ex){
                System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
            }
        }
        
        public void excluir (int id){
            try{
                String sql = "DELETE FROM Produto WHERE id = ?";
                
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.execute();
            }catch(SQLException ex){
                System.out.println("Erro ao excluir pessoa: " + ex.getMessage());
            }
        }
}
