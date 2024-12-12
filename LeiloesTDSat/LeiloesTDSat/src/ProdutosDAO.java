/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO(){
        conectaDAO conecta = new conectaDAO();
        conn = conecta.connectDB();
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        try{
            prep = conn.prepareStatement("INSERT INTO produtos VALUES (?, ?, ?, ?)");
            prep.setString(1, null);
            prep.setString(2, produto.getNome());
            prep.setInt(3, produto.getValor());
            prep.setString(4, produto.getStatus());
            
            prep.executeUpdate();
            
        } catch(SQLException ex) {
            System.out.println("Erro no método: cadastrarProduto");
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

