package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VentasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarVentas(Ventas cl) {
    String sql = "INSERT INTO ventas (cliente, vendedor, total, fecha) VALUES (?,?,?,?)";
    try {
    con = cn.getConexion();
    ps = con.prepareStatement(sql);
    ps.setString(1, cl.getCliente());
    ps.setString(2, cl.getVendedor());
    ps.setFloat(3, cl.getTotal());
    ps.setString(2, cl.getFecha());
    ps.execute();
    return true;
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.toString());
    return false;
        }    
    }
    
public List listarVentas(){
        List<Ventas> ListaCl = new ArrayList();
        String sql = "SELECT * FROM ventas ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Ventas cl = new Ventas();
                cl.setId(rs.getInt("id"));
                cl.setCliente(rs.getString("Cliente"));
                cl.setTotal(rs.getFloat("Total"));
                cl.setFecha(rs.getString("Fecha"));
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }
    
    public boolean eliminarUsuario(int id){
        String sql = "DELETE FROM ventas WHERE id=?";
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
    
    public boolean modificarUsuario(Usuario cl){
        String sql = "UPDATE usuario SET nombre=?, correo=?, pass=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getCorreo());
            ps.setString(3, cl.getPass());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
    }
}
