package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("ProductoConverter")
public class ProductoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        String tipo = "";
        if (t != null) {
            tipo = (String) t;
            switch (tipo) {
                case "P": tipo = "Pizza"; break;
                case "C": tipo = "Combos"; break;
                case "A": tipo = "Pastas"; break;
                case "O": tipo = "Ofertas"; break;
                case "E": tipo = "Entradas"; break;
                case "S": tipo = "Salsas"; break;
                case "T": tipo = "Postre"; break;
                case "B": tipo = "Bebidas"; break;
            }
        }

        return tipo;
    }
}