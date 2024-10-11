
package br.com.DAO;

import br.com.DAO.ConexaoDAO;
import br.com.DTO.UsuarioDTO;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs= null;
    
    
    
    
    
    public void inserirUsuario(UsuarioDTO objUsuarioDTO){
        String sql ="insert into tb_usuarios(id_usuario, usuario, login, senha ) values(?,?,?,?)";
        conexao = new ConexaoDAO().conector();
        
        try {
            pst = conexao.prepareStatement(sql); 
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            pst.setString(2, objUsuarioDTO.getNomeUsuario());
            pst.setString(3, objUsuarioDTO.getLoginUsuario());
            pst.setString(4, objUsuarioDTO.getSenhaUsuario());
            
          int add = pst.executeUpdate();
          
          if (objUsuarioDTO.getId_usuario() < 0) {
            JOptionPane.showMessageDialog(null, "Erro: O ID do usuário não pode ser menor que 0.");
            return;
        }
          
           if (objUsuarioDTO.getNomeUsuario().isEmpty() || 
            objUsuarioDTO.getLoginUsuario().isEmpty() || 
            objUsuarioDTO.getSenhaUsuario().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Erro: Todos os campos devem ser preenchidos.");
            return;
        }
          
          if(add > 0){
          JOptionPane.showMessageDialog(null, "Inserido com sucesso");
          
          }else{
           JOptionPane.showMessageDialog(null, "Erro");
              
          }
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null," Inserir Usuario" + e);
        }
        
        
        
        
    }
    
    public void editar (UsuarioDTO objUsuarioDTO){
        String sql = "update tb_usuarios set usuario = ?, login = ?, senha = ?  where id_usuario = ? ;";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(4,objUsuarioDTO.getId_usuario() );
            pst.setString(3,objUsuarioDTO.getSenhaUsuario());
            pst.setString(2,objUsuarioDTO.getLoginUsuario());
            pst.setString(1,objUsuarioDTO.getNomeUsuario());
            int add = pst.executeUpdate();
            
            if (add > 0){
            JOptionPane.showMessageDialog(null, "Usuario editado com sucesso");
            conexao.close();
            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método editar" + e);
        }
        
    
    
    }
    
    public void deletar(UsuarioDTO objUsuarioDTO){
        String sql = "delete from tb_usuarios where id_usuario = ?;";
        conexao = ConexaoDAO.conector();
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            int del = pst.executeUpdate();
            if (del > 0){
                JOptionPane.showMessageDialog(null, "Usuario deletado com sucesso");
                
    
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Metodo deletar" +  e);
        }
        
    
    }
    
}
