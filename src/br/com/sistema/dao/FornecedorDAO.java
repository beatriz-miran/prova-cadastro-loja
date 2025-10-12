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


public class FornecedorDAO {
    private Conexao conexao;
    private Connection conn;

    public FornecedorDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(FornecedorC fornecedor) {
        String sql = "INSERT INTO Fornecedor(for_nome, for_nome_fantasia, for_cnpj, for_email, for_tel, for_rua, for_cep, for_bairro, for_uf, for_numero, for_cidade) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getNomeFantasia());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setString(5, fornecedor.getTelefone());
            stmt.setString(6, fornecedor.getRua());
            stmt.setString(7, fornecedor.getCep());
            stmt.setString(8, fornecedor.getBairro());
            stmt.setString(9, fornecedor.getEstado());
            stmt.setString(10, Integer.toString(fornecedor.getNumero()));
            stmt.setString(11, fornecedor.getCidade());

            stmt.execute();

        }catch(SQLException ex){System.out.println("Erro ao insetir fornecedor: " + ex.getMessage());
        }
    }     
        public ProdutoC getPessoa(int id){
            String sql = "SELECT  * FROM Fornecedor WHERE id = ?";
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
        
        
        public List<FornecedorC> listar(){
        List<FornecedorC> lista = new ArrayList<>();
            try{
                String sql = "select * from fornecedor";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    FornecedorC obj = new FornecedorC();
                    
                    obj.setId(rs.getInt("for_id"));
                    obj.setNome(rs.getString("for_nome"));
                    obj.setNome_fantasia(rs.getString("for_nome_fantasia"));
                    obj.setCnpj(rs.getString("for_cnpj"));
                    obj.setEmail(rs.getString("for_email"));
                    obj.setTelefone(rs.getString("for_tel"));
                    obj.setRua(rs.getString("for_rua"));
                    obj.setCep(rs.getString("for_cep"));
                    obj.setBairro(rs.getString("for_bairro"));
                    obj.setEstado(rs.getString("for_uf"));
                    obj.setNumero(rs.getInt("for_numero"));
                    obj.setCidade(rs.getString("for_cidade"));
                    
                    lista.add(obj);
                }
                return lista;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "erro ao criar a lista" + e);
            }
            return null;
        }
        
}
