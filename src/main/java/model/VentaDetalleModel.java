package model;

public class VentaDetalleModel {
    private int codigoVentaDetalle;
    private int fkVenta;
    private int fkProducto;
    private int cantidadProducto;
    private Double subtotalVenta;

    public int getCodigoVentaDetalle() {
        return codigoVentaDetalle;
    }

    public void setCodigoVentaDetalle(int codigoVentaDetalle) {
        this.codigoVentaDetalle = codigoVentaDetalle;
    }

    public int getFkVenta() {
        return fkVenta;
    }

    public void setFkVenta(int fkVenta) {
        this.fkVenta = fkVenta;
    }

    public int getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(int fkProducto) {
        this.fkProducto = fkProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Double getSubtotalVenta() {
        return subtotalVenta;
    }

    public void setSubtotalVenta(Double subtotalVenta) {
        this.subtotalVenta = subtotalVenta;
    }
    
    
}
