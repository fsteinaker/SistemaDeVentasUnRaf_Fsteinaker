package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class loginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion ();
    
public loginDAO(){
}
    
    public login autenticar(String correo, String pass) {
    login l = null;
    
    String sql = "SELECT * FROM usuario WHERE correo = ? AND pass = ?";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, correo);
        ps.setString(2, pass);
        rs = ps.executeQuery();
        if (rs.next()) {
            l = new login();
            l.setId(rs.getInt("id"));
            l.setNombre(rs.getString("nombre"));
            l.setCorreo(rs.getString("correo"));
            l.setPass(rs.getString("pass"));
        }
    }catch(SQLException e){
        System.out.println(e.toString());
    } finally {
        try {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
            } catch (SQLException e) {
            System.out.println(e.toString());
                }
            }
    return l;
    }
}