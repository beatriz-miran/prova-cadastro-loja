
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FornecedorDAO {
    private Conexao conexao;
    private Connection conn;

    public FornecedorDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(FornecedorC fornecedor) {
        String sql = "INSERT INTO Forcedor(for_nome, for_nome_fantasia, for_cnpj, for_email, for_tel, for_rua, for_cep, for_bairro, for_uf, for_numero, for_cidade) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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
        
}
