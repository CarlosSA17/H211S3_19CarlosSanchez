package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PersonaModel;

public class PersonaImp extends Conexion implements ICRUD<PersonaModel> {

    @Override
    public void guardar(PersonaModel persona) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO PERSONA"
                    + "(TIPPER,NOMCOMPER,DNIPER,CELPER,EMAILPER,DIRPER,CODUBI,ESTPER)"
                    + " VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getTipoPersona());
            ps.setString(2, persona.getNombrePersona());
            ps.setString(3, persona.getDniPersona());
            ps.setString(4, persona.getCelularPersona());
            ps.setString(5, persona.getEmailPersona());
            ps.setString(6, persona.getDireccionPersona());
            ps.setString(7, persona.getFkUbigeo());
            ps.setString(8, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PersonaImp/Guardar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(PersonaModel persona) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE SET PERSONA TIPPER=? NOMCOMPER=? DNIPER=? CELPER=? EMAILPER=? DIRPER=? CODUBI=? ESTPER=? WHERE IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getTipoPersona());
            ps.setString(2, persona.getNombrePersona());
            ps.setString(3, persona.getDniPersona());
            ps.setString(4, persona.getCelularPersona());
            ps.setString(5, persona.getEmailPersona());
            ps.setString(6, persona.getDireccionPersona());
            ps.setString(7, persona.getFkUbigeo());
            ps.setString(8, "A");
            ps.setInt(9, persona.getCodigoPersona());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PersonaImp/Modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(PersonaModel persona) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PERSONA SET ESTPER=? WHERE IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getEstadoPersona());
            ps.setInt(2, persona.getCodigoPersona());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en PèrsonaImp/Eliminar: " + e.getMessage());
        }
    }

    //@Override
    public void cambiarEstado(PersonaModel persona) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PERSONA SET ESTPER=? WHERE IDPER=?";
            try ( PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setString(1, persona.getEstadoPersona());
                ps.setInt(2, persona.getCodigoPersona());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en PersonaImp/cambiarEstado: " + e.getMessage());
        }
    }

    @Override
    public void restaurar(PersonaModel persona) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PERSONA SET ESTPER = 'A' where IDPER=?";
            try ( PreparedStatement ps = this.getCn().prepareStatement(sql)) {
                ps.setInt(1, persona.getCodigoPersona());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error en PersonaImp/Restaurar: " + e.getMessage());
        } finally {
            this.Cerrar();

        }
    }

    public int validar(PersonaModel persona, int caso) throws Exception {
        this.conectar();
        String SQL1 = "SELECT IDPER FROM PERSONA WHERE DNIPER = ?";
        try ( PreparedStatement ps1 = this.getCn().prepareCall(SQL1)) {
            ps1.setString(1, persona.getDniPersona());
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                caso = 1; //Dni existente
            } else {
                String SQL2 = "SELECT IDPER FROM PERSONA WHERE DNIPER = ? AND NOMCOMPER = ?";
                PreparedStatement ps2 = this.getCn().prepareCall(SQL2);
                ps2.setString(1, persona.getDniPersona());
                ps2.setString(2, persona.getNombrePersona());
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    caso = 2; //Dni, nombres existente
                } else {
                    String SQL3 = "SELECT IDPER FROM PERSONA WHERE CELPER = ?";
                    PreparedStatement ps3 = this.getCn().prepareCall(SQL3);
                    ps3.setString(1, persona.getCelularPersona());
                    ResultSet rs3 = ps3.executeQuery();
                    if (rs3.next()) {
                        caso = 3; //Celular existente
                    } else {
                        String SQL4 = "SELECT IDPER FROM PERSONA WHERE EMAILPER = ?";
                        PreparedStatement ps4 = this.getCn().prepareCall(SQL4);
                        ps4.setString(1, persona.getEmailPersona());
                        ResultSet rs4 = ps4.executeQuery();
                        if (rs4.next()) {
                            caso = 4; //Correo existente
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en PersonaImp/Validar: " + e.getMessage());
        } finally {
            this.Cerrar();
        }
        return caso;
    }

    @Override
    public List<PersonaModel> listarTodos(int tipo) throws Exception {
        this.conectar();
        List<PersonaModel> listado = new ArrayList<>();
        ResultSet rs;
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'A' AND TIPPER = 'A' ORDER BY IDPER desc";
                break;
            case 2:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'I' AND TIPPER = 'A' ORDER BY IDPER desc";
                break;
            case 3:
                sql = "SELECT * FROM PERSONA WHERE TIPPER = 'A' ORDER BY IDPER desc";
                break;
            case 4:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'A' AND TIPPER = 'J' ORDER BY IDPER desc";
                break;
            case 5:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'I' AND TIPPER = 'J' ORDER BY IDPER desc";
                break;
            case 6:
                sql = "SELECT * FROM PERSONA WHERE TIPPER = 'J' ORDER BY IDPER desc";
                break;
            case 7:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'A' AND TIPPER = 'V' ORDER BY IDPER desc";
                break;
            case 8:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'I' AND TIPPER = 'V' ORDER BY IDPER desc";
                break;
            case 9:
                sql = "SELECT * FROM PERSONA WHERE TIPPER = 'V' ORDER BY IDPER desc";
                break;
            case 10:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'A' AND TIPPER = 'D' ORDER BY IDPER desc";
                break;
            case 11:
                sql = "SELECT * FROM PERSONA WHERE ESTPER = 'I' AND TIPPER = 'D' ORDER BY IDPER desc";
                break;
            case 12:
                sql = "SELECT * FROM PERSONA WHERE TIPPER = 'D' ORDER BY IDPER desc";
                break;
        }
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PersonaModel per = new PersonaModel();
                per.setCodigoPersona(rs.getInt("IDPER"));
                per.setTipoPersona(rs.getString("TIPPER"));
                per.setNombrePersona(rs.getString("NOMCOMPER"));
                per.setDniPersona(rs.getString("DNIPER"));
                per.setCelularPersona(rs.getString("CELPER"));
                per.setEmailPersona(rs.getString("EMAILPER"));
                per.setDireccionPersona(rs.getString("DIRPER"));
                per.setFkUbigeo(rs.getString("CODUBI"));
                per.setEstadoPersona(rs.getString("ESTPER"));
                listado.add(per);
            }
        } catch (SQLException e) {
            System.out.println("Error en PersonaImpl/Listar: " + e.getMessage());
        } finally {
            this.Cerrar();
        }
        return listado;
    }
}
