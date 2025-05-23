package tiendaAOVE;

import java.util.Comparator;
import java.util.HashMap;

public class ComparadorMedia implements Comparator<Producto> {
    @Override
    public int compare(Producto p1, Producto p2) {
        double media1 = calcularValoracionMedia(p1.getValoraciones());
        double media2 = calcularValoracionMedia(p2.getValoraciones());
        return Double.compare(media1, media2);
    }

    private double calcularValoracionMedia(HashMap<Cliente, Integer> valoraciones) {
        if (valoraciones == null || valoraciones.isEmpty()) {
            return 0;
        }
        
        int suma = 0;
        for (int valoracion : valoraciones.values()) {
            suma += valoracion;
        }
        
        return (double) suma / valoraciones.size();
    }
}