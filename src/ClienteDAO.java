
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
public class ClienteDAO {
     private Conexao conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(ClienteC cliente) {
        String sql = "INSERT INTO Cliente(cli_id, cli_nome, cli_email, cli_tel, cli_cep, cli_rua, cli_cidade, cli_numero, cli_bairro, cli_uf) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, Integer.toString(cliente.getId()));
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getCep());
            stmt.setString(6, cliente.getRua());
            stmt.setString(7, cliente.getCidade());
            stmt.setString(8, Integer.toString(cliente.getNumero()));
            stmt.setString(9, cliente.getBairro());
            stmt.setString(10, cliente.getEstado());

            stmt.execute();

        }catch(SQLException ex){
        System.out.println("Erro ao insetir pessoa: " + ex.getMessage());
        }
    }     
        public ClienteC getPessoa(int id){
            String sql = "SELECT  * FROM Cliente WHERE id = ?";
            try{
                PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                ClienteC p = new ClienteC();
                
                rs.first();
                p.setId(id);
                p.setNome(rs.getString("nome"));
               // p.setDescr(rs.getString("descr"));
                //p.setPreco(Float.toString(rs.getString("preco")));
                //p.setQtdEst(Integer.toString(rs.getString("qtdEst")));
                
                return p;
                        
            }catch(SQLException ex){
                System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
                return null;
            }
        }
        
        public void editar(ClienteC cliente){
            try{
                String sql = "UPDATE Cliente SET nome = ?, sexo = ?, idioma = ? WHERE id = ?";
                
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,cliente.getNome());
                //stmt.setString(2,cliente.getDescr());
               // stmt.setString(3,produto.getPreco());
                stmt.setInt(4, cliente.getId());
                
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
