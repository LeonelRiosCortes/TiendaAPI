package Ico.Fes.Tienda.TiendaFrontend.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductoF {
    private int id;
    private String nombre;
    private int precio;
    private String distribuidor;
    private String descripcion;
}
