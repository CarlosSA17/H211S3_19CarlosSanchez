package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProductoModel;

public class ProductoImp extends Conexion implements ICRUD<ProductoModel> {

    @Override
    public void guardar(ProductoModel producto) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO PRODUCTO"
                    + "(TIPPRO,NOMPRO,DESPRO,PREPRO,ESTPRO)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getTipoProducto());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getDescripcionProducto());
            ps.setDouble(4, producto.getPrecioProducto());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ProductoImp/Guardar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(ProductoModel producto) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE set PRODUCTO TIPPRO=? NOMPRO=? DESPRO=? PREPRO=? ESTPRO=? WHERE IDPRO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getTipoProducto());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getDescripcionProducto());
            ps.setDouble(4, producto.getPrecioProducto());
            ps.setString(5, producto.getEstadoProducto());
            ps.setInt(6, producto.getCodigoProducto());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ProductoImp/Modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(ProductoModel producto) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PRODCUCTO SET ESTPRO=? WHERE IDPRO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getEstadoProducto());
            ps.setInt(2, producto.getCodigoProducto());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en ProductoImp/Eliminar: " + e.getMessage());
        }
    }

    //@Override
    public void cambiarEstado(ProductoModel producto) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PRODUCTO SET ESTPRO=? WHERE IDPRO=?";
            try ( PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setString(1, producto.getEstadoProducto());
                ps.setInt(2, producto.getCodigoProducto());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en ProductoImp/cambiarEstado: " + e.getMessage());
        }
    }

    @Override
    public void restaurar(ProductoModel producto) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PRODUCTO SET ESTPRO = 'A' where IDPRO=?";
            try ( PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setInt(1, producto.getCodigoProducto());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en ProductoImp/Restaurar: " + e.getMessage());
        } finally {
            this.Cerrar();

        }
    }

    @Override
    public List<ProductoModel> listarTodos(int tipo) throws Exception {
        this.conectar();
        List<ProductoModel> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'C' ORDER BY IDPRO desc";
                break;
            case 2:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'C' ORDER BY IDPRO desc";
                break;
            case 3:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'C' ORDER BY IDPRO desc";
                break;
            case 4:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'O' ORDER BY IDPRO desc";
                break;
            case 5:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'O' ORDER BY IDPRO desc";
                break;
            case 6:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'O' ORDER BY IDPRO desc";
                break;
            case 7:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'P' ORDER BY IDPRO desc";
                break;
            case 8:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'P' ORDER BY IDPRO desc";
                break;
            case 9:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'P' ORDER BY IDPRO desc";
                break;
            case 10:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'A' ORDER BY IDPRO desc";
                break;
            case 11:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'A' ORDER BY IDPRO desc";
                break;
            case 12:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'A' ORDER BY IDPRO desc";
                break;
            case 13:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'E' ORDER BY IDPRO desc";
                break;
            case 14:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'E' ORDER BY IDPRO desc";
                break;
            case 15:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'E' ORDER BY IDPRO desc";
                break;
            case 16:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'S' ORDER BY IDPRO desc";
                break;
            case 17:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'S' ORDER BY IDPRO desc";
                break;
            case 18:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'S' ORDER BY IDPRO desc";
                break;
            case 19:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'B' ORDER BY IDPRO desc";
                break;
            case 20:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'B' ORDER BY IDPRO desc";
                break;
            case 21:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'B' ORDER BY IDPRO desc";
                break;
            case 22:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' AND TIPPRO = 'T' ORDER BY IDPRO desc";
                break;
            case 23:
                sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' AND TIPPRO = 'T' ORDER BY IDPRO desc";
                break;
            case 24:
                sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'T' ORDER BY IDPRO desc";
                break;
        }
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoModel pro = new ProductoModel();
                pro.setCodigoProducto(rs.getInt("IDPRO"));
                pro.setTipoProducto(rs.getString("TIPPRO"));
                pro.setNombreProducto(rs.getString("NOMPRO"));
                pro.setDescripcionProducto(rs.getString("DESPRO"));
                pro.setPrecioProducto(rs.getDouble("PREPRO"));
                pro.setEstadoProducto(rs.getString("ESTPRO"));
                listado.add(pro);
            }
        } catch (SQLException e) {
            System.out.println("Error en ProductoImpl/Listar: " + e.getMessage());
        } finally {
            this.Cerrar();
        }
        return listado;
    }
}
