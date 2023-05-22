package Ico.Fes.Tienda.TiendaNueva.Controller;


import Ico.Fes.Tienda.TiendaNueva.entity.Producto;
import Ico.Fes.Tienda.TiendaNueva.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Tienda")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Create a new Producto
    @PostMapping("/crear")
    public ResponseEntity<Producto> addProd (@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    //Read a Producto
    @GetMapping("/producto/{id}")
    public ResponseEntity<Optional<Producto>> BuscarId (@PathVariable(value = "id") int id){
        Optional<Producto> oProd = productoService.findById(id);
        if (!oProd.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oProd);
    }

    //Update a Producto
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProd (@RequestBody Producto productoDetails, @PathVariable(value = "id") int id ){
        Optional<Producto> oProd = productoService.findById(id);
        if (!oProd.isPresent()){
            return ResponseEntity.notFound().build();
        }
        oProd.get().setNombre(productoDetails.getNombre());
        oProd.get().setPrecio(productoDetails.getPrecio());
        oProd.get().setDescripcion(productoDetails.getDescripcion());
        oProd.get().setDistribuidor(productoDetails.getDistribuidor());
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(oProd.get()));
    }

    //Delete a Producto
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Producto> borrarProd (@PathVariable(value = "id") int id){
        if (!productoService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        productoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Read all Prod
    /*
    @GetMapping("/Productos")
    public List<Producto> allProd(){
        List<Producto> productos = StreamSupport
                .stream(productoService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return productos;
    }*/
    @GetMapping("/Productos")
    public ResponseEntity<List<Producto>> allProd(){
        List<Producto> productos = StreamSupport
                .stream(productoService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productos);
    }

}














