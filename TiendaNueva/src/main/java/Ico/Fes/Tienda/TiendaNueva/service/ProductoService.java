package Ico.Fes.Tienda.TiendaNueva.service;

import Ico.Fes.Tienda.TiendaNueva.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductoService {

    public Iterable<Producto> findAll();

    public Page<Producto> findAll(Pageable pageable);

    public Optional<Producto> findById(int id);

    public Producto save(Producto producto);

    public void deleteById(int id);

}
