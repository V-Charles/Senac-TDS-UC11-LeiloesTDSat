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
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        try{
            prep = conn.prepareStatement("INSERT INTO produtos VALUES (?, ?, ?, ?)");
            prep.setString(1, null);
            prep.setString(2, produto.getNome());
            prep.setInt(3, produto.getValor());
            prep.setString(4, produto.getStatus());
            
            prep.executeUpdate();
            return true;
            
        } catch(SQLException ex) {
            System.out.println("Erro no método: cadastrarProduto");
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try {
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro no método: listarProdutos");
            System.out.println(ex.getMessage());
        }
        return listagem;
    }
    
    public boolean venderProduto(ProdutosDTO p){
        try {
            prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
            prep.setString(1, "Vendido");
            prep.setInt(2, p.getId());
            
            int rowsAffected = prep.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException ex) {
            System.out.println("Erro no método: venderProduto");
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}

