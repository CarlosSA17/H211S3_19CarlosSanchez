package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.PersonaModel;
import model.VentaDetalleModel;
import model.VentaModel;

public class VentaImp extends Conexion {

    // Formateo para el campo fecha 
    DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

    // Registrar en la tabla VENTA
    public void guardarVenta(VentaModel venta) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO VENTA (TIPVEN, IDPER, FECVEN, ESTVEN) VALUES (?,?,?;?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, venta.getTipoVenta());
            ps.setInt(2, venta.getFkPersona());
            ps.setString(3, formato.format(venta.getFechaVenta()));
            ps.setString(4, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en VentaImp/RegistrarVenta");
        }
    }

    // Registrar en la tabla VENTADETALLE
    public void guardarVentaDetalle(List<VentaDetalleModel> listaVentaDetalle, int fkVenta) throws Exception {
        this.conectar();
        String sql = "INSERT INTO VENTA_DETALLE (IDVEN,IDPRO,CANTPRO,SUBTOTAVEN) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            for (VentaDetalleModel ventadetalle : listaVentaDetalle) {
                ps.setInt(1, ventadetalle.getFkVenta());
                ps.setInt(2, ventadetalle.getFkProducto());
                ps.setInt(3, ventadetalle.getCantidadProducto());
                ps.setDouble(4, ventadetalle.getSubtotalVenta());
                ps.executeUpdate();
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en VentaImp/RegistrarVentaDetalle " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.Cerrar();
        }
    }

    public int ultimoIdVenta() {
        this.conectar();
        try {
            PreparedStatement ps = this.getCn().prepareStatement("SELECT MAX(IDVEN) FROM VENTA");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDVEN");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en ultimoIdVenta_D " + e.getMessage());
        }
        return -1;
    }

    public List<VentaModel> listarVenta() throws Exception {
        List<VentaModel> listado = new ArrayList<>();
        PersonaModel persona;
        VentaModel venta;
        String sql = "SELECT * FROM vVenta ORDER BY IDVEN DESC";
        try {
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                venta = new VentaModel();
                venta.setCodigoVenta(rs.getInt("IDVEN"));
                venta.setFechaVenta(rs.getDate("FECVEN"));
                persona = new PersonaModel();
                persona.setCodigoPersona(rs.getInt("IDCLI"));
                persona.setNombrePersona(rs.getString("NOMCLI"));
                persona.setDniPersona(rs.getString("DNICLI"));
                listado.add(venta);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error en el listarVenta_D " + e.getMessage());
        } finally {
            this.Cerrar();
        }
        return listado;
    }

}
