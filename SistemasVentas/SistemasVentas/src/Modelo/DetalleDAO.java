package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DetalleDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarDetalle(Detalle cl) {
    String sql = "INSERT INTO detalle (codProducto, cantidad, precio, idVenta) VALUES (?,?,?,?)";
    try {
    con = cn.getConexion();
    ps = con.prepareStatement(sql);
    ps.setInt(1, cl.getCodProducto());
    ps.setInt(2, cl.getCantidad());
    ps.setFloat(3, cl.getPrecio());
    ps.setInt(4, cl.getIdVenta());
    ps.execute();
    return true;
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.toString());
    return false;
        }    
    }
    
public List listarDetalle(){
        List<Detalle> ListaCl = new ArrayList();
        String sql = "SELECT * FROM detalle ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Detalle cl = new Detalle();
                cl.setId(rs.getInt("id"));
                cl.setCodProducto(rs.getInt("codProducto"));
                cl.setCantidad(rs.getInt("cantidad"));
                cl.setPrecio(rs.getFloat("precio"));
                cl.setIdVenta(rs.getInt("idVenta"));
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaCl;
    }
    
    public boolean eliminarDetalle(int id){
        String sql = "DELETE FROM detalle WHERE id=?";
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
    
    public boolean modificarDetale(Detalle cl){
        String sql = "UPDATE detalle SET codProducto=?, cantidad=?, precio=?, idVenta=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getCodProducto());
            ps.setInt(2, cl.getCantidad());
            ps.setFloat(3, cl.getPrecio());
            ps.setInt(4, cl.getIdVenta());
            ps.setInt(5, cl.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
    }
}