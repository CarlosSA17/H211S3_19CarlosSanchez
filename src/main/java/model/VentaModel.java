package model;

import java.sql.Date;

public class VentaModel {

    private int codigoVenta;
    private String tipoVenta;
    private int fkPersona;
    private Date fechaVenta;
    private String estadoVenta;

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public int getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(int fkPersona) {
        this.fkPersona = fkPersona;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
    
    
}
