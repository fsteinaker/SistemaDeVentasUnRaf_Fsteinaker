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
    ps.setDouble(5, pd.getPrecio());
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
                pd.setPrecio(rs.getDouble("Precio"));
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
            ps.setDouble(5, pd.getPrecio());
            ps.setInt(6, pd.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }        
    }
    
 /* Comandos planilla venta

 public Productos BuscarPro(String cod){
        Productos producto = new Productos();
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }
    public Productos BuscarId(int id){
        Productos pro = new Productos();
        String sql = "SELECT pr.id AS id_proveedor, pr.nombre AS nombre_proveedor, p.* FROM proveedor pr INNER JOIN productos p ON p.proveedor = pr.id WHERE p.id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getInt("proveedor"));
                pro.setProveedorPro(rs.getString("nombre_proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pro;
    }
    public Proveedor BuscarProveedor(String nombre){
        Proveedor pr = new Proveedor();
        String sql = "SELECT * FROM proveedor WHERE nombre = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()) {
                pr.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pr;
    }
    public Config BuscarDatos(){
        Config conf = new Config();
        String sql = "SELECT * FROM config";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                conf.setId(rs.getInt("id"));
                conf.setRuc(rs.getString("ruc"));
                conf.setNombre(rs.getString("nombre"));
                conf.setTelefono(rs.getString("telefono"));
                conf.setDireccion(rs.getString("direccion"));
                conf.setMensaje(rs.getString("mensaje"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    }
    
    public boolean ModificarDatos(Config conf){
       String sql = "UPDATE config SET ruc=?, nombre=?, telefono=?, direccion=?, mensaje=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, conf.getRuc());
           ps.setString(2, conf.getNombre());
           ps.setString(3, conf.getTelefono());
           ps.setString(4, conf.getDireccion());
           ps.setString(5, conf.getMensaje());
           ps.setInt(6, conf.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   } */

}