package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ProveedorDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor (CUIT, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        
        try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, pr.getCUIT());
        ps.setString(2, pr.getNombre());
        ps.setString(3, pr.getTelefono());
        ps.setString(4, pr.getDireccion());
        ps.setString(5, pr.getRazon());
        ps.execute();
        return true;
        } catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }

        public List listarProveedor(){
        List<Proveedor> listaPr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Proveedor pr = new Proveedor();
                pr.setID(rs.getInt("id"));
                pr.setCUIT(rs.getInt("CUIT"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                listaPr.add(pr);
            }
        } catch (SQLException e) {  
            System.out.println(e.toString());
        }
        return listaPr;
    }
        
    public boolean eliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id=?";
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
    
    public boolean modificarProveedor(Proveedor pr){
        String sql = "UPDATE proveedor SET CUIT=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getCUIT());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.setInt(6, pr.getID());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
    }    
        
}