/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2830482411044
 */
public class ProdutoC {
    private int id;
    private String nome;
    private String descr;
    private float preco;
    private int qtdEst;
    
    public int getId(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }
    public String getDescr(){
        return descr;
    }
    public float getPreco(){
        return preco;
    }
    public int getQtdEst(){
        return qtdEst;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDescr(String descr){
        this.descr = descr;
    }
    public void setPreco(float preco){
        this.preco = preco;
    }
    public void setQtdEst(int qtdEst){
        this.qtdEst = qtdEst;
    }
}
