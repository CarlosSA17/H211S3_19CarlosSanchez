package controller;

import dao.ProductoImp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ProductoModel;

@Named(value = "PizzaC")
@SessionScoped
public class PizzaC implements Serializable {

    private ProductoModel producto;
    private ProductoImp dao;
    private List<ProductoModel> listadopro;
    private int tipo = 7;

    @PostConstruct
    public void init() {
        try {
            this.listar();
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }

    public PizzaC() {
        producto = new ProductoModel();
        dao = new ProductoImp();
    }

    public void registrar() throws Exception {
        try {
            producto.setTipoProducto("P");
            dao.guardar(producto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar ClienteC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(producto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar ClienteC/modificar" + e.getMessage());
        }
    }

    public void eliminarA() throws Exception {
        try {
            producto.setEstadoProducto("I");
            dao.eliminar(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Eliminado con éxito"));
            listar();

        } catch (Exception e) {
            System.out.println("Error en eliminarC/ClienteC" + e.getMessage());
        }
    }

    public void restaurar() throws Exception {
        try {
            dao.restaurar(producto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", "Se restauro correctamente"));
        } catch (Exception e) {
            System.out.println("error en restaurar/ClienteC " + e);
        }
    }

    public void listar() throws Exception {
        try {
            listadopro = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en ListarClienteC: " + e.getMessage());
        }

    }

    public void limpiar() throws Exception {
        producto = new ProductoModel();
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public ProductoImp getDao() {
        return dao;
    }

    public void setDao(ProductoImp dao) {
        this.dao = dao;
    }

    public List<ProductoModel> getListadopro() {
        return listadopro;
    }

    public void setListadopro(List<ProductoModel> listadopro) {
        this.listadopro = listadopro;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
}
