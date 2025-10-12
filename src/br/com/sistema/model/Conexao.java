package br.com.sistema.model;


import java.sql.Connection;
import java.sql.DriverManager;
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
public class Conexao {
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compras?useTimezone=true&serverTimezone=UTC", "root", "root");
            System.out.println("Conexao gerada com sucesso!");
            return conn;
        }
        catch(SQLException e){
            System.out.println("Erro ao conectar no BD" + e.getMessage());
            return null;
        }
    }
}
