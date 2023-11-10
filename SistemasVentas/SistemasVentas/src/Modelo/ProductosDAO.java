package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductosDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Productos pd) {
    String sql = "INSERT INTO productos (codigo, descripcion, proveedor, stock, precio) VALUES (?,?,?,?,?)";
    try {
    con = cn.getConexion();
    ps = con.prepareStatement(sql);
    ps.setString(1, pd.getCodigo());
    ps.setString(2, pd.getDescripcion());
    ps.setString(3, pd.getProveedor());
    ps.setInt(4, pd.getStock());
    ps.setInt(5, pd.getPrecio());
    ps.execute();
    return true;
    } catch (SQLException e) {
    JOptionPane.showMessageDialog(null, e.toString());
    return false;
        }    
    }
    
public List listarProducto(){
        List<Productos> ListaPd = new ArrayList();
        String sql = "SELECT * FROM productos ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Productos pd = new Productos();
                pd.setId(rs.getInt("id"));
                pd.setCodigo(rs.getString("Codigo"));
                pd.setDescripcion(rs.getString("Descripcion"));
                pd.setProveedor(rs.getString("Proveedor"));
                pd.setStock(rs.getInt("Stock"));
                pd.setPrecio(rs.getInt("Precio"));
                ListaPd.add(pd);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPd;
    }
    
    public boolean eliminarProductos(int id){
        String sql = "DELETE FROM productos WHERE id=?";
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
    
    public boolean modificarProductos(Productos pd){
        String sql = "UPDATE productos SET codigo=?, descripcion=?, proveedor=?, stock=?, precio=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pd.getCodigo());
            ps.setString(2, pd.getDescripcion());
            ps.setString(3, pd.getProveedor());
            ps.setInt(4, pd.getStock());
            ps.setInt(5, pd.getPrecio());
            ps.setInt(6, pd.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
    }
}
