package Ico.Fes.Tienda.TiendaNueva.repository;

import Ico.Fes.Tienda.TiendaNueva.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Integer> {

}
