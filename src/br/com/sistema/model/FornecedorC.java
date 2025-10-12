package br.com.sistema.model;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class FornecedorC {
    private int id;
    private String nome;
    private String nome_fantasia;
    private String cnpj;
    private String email;
    private String telefone;
    private String cep;
    private String rua;
    private String cidade;
    private int numero;
    private String bairro;
    private String estado;
    
//GETTERS
    public int getId(){
        return id;
    } 
    public String getNome(){
        return nome;
    }
    public String getNomeFantasia(){
        return nome_fantasia;
    }
    public String getCnpj(){
        return cnpj;
    }
    public String getEmail(){
        return email;
    }
    public String getTelefone(){
        return telefone;
    }
    public String getCep(){
        return cep;
    }
    public String getRua(){
        return rua;
    }
    public String getCidade(){
        return cidade;
    }
    public int getNumero(){
        return numero;
    }
    public String getBairro(){
        return bairro;
    }
      public String getEstado(){
        return estado;
    }
      //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
         public static JSONObject buscar(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuilder json = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                json.append(linha);
            }
            br.close();

            JSONObject obj = new JSONObject(json.toString());
            if (obj.has("erro")) {
                return null; // CEP não encontrado
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
     public Object[] obterDados(){
     return new Object[] {id, nome, nome_fantasia, cnpj, email, telefone, cep, rua, cidade, numero, bairro, estado};
    }
 @Override
 public String toString(){
     return this.getNome();
 }
}
