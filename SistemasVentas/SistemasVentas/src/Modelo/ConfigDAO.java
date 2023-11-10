package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ConfigDAO {
Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarConfig(Config cfg) {
    String sql = "INSERT INTO config (nombre, CUIL, telefono, direccion, razon) VALUES (?,?,?,?,?)";
    try {
    con = cn.getConexion();
    ps = con.prepareStatement(sql);
    ps.setString(1, cfg.getNombre());
    ps.setString(2, cfg.getCUIL());
    ps.setInt(3, cfg.getTelefono());
    ps.setString(4, cfg.getDireccion());
    ps.setString(5, cfg.getRazon());
    ps.execute();
    return true;
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.toString());
    return false;
        }    
    }
    
public List listarConfig(){
        List<Config> ListaCl = new ArrayList();
        String sql = "SELECT * FROM config ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Config cfg = new Config();
                cfg.setId(rs.getInt("id"));
                cfg.setNombre(rs.getString("nombre"));
                cfg.setCUIL(rs.getString("CUIL"));
                cfg.setTelefono(rs.getInt("telefono"));
                cfg.setDireccion(rs.getString("direccion"));
                cfg.setRazon(rs.getString("razon"));
                ListaCl.add(cfg);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }
    
    public boolean eliminarConfig(int id){
        String sql = "DELETE FROM config WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean modificarConfig(Config cfg){
        String sql = "UPDATE config SET nombre=?, CUIL=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cfg.getNombre());
            ps.setString(2, cfg.getCUIL());
            ps.setInt(3, cfg.getTelefono());
            ps.setString(4, cfg.getDireccion());
            ps.setString(5, cfg.getRazon());
            ps.setInt(6, cfg.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
    }
}