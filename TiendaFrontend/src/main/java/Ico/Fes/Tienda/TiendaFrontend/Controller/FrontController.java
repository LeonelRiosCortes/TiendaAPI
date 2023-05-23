package Ico.Fes.Tienda.TiendaFrontend.Controller;

import Ico.Fes.Tienda.TiendaFrontend.Model.ProductoF;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/Tienda")
public class FrontController {

    private final WebClient webClient;

    @Autowired
    public FrontController(WebClient webClient){
        this.webClient = webClient;
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) throws JsonProcessingException {
        String url = "http://localhost:8080/api/Tienda/Productos";

        Flux<ProductoF> productoFlux = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<ProductoF>() {
                });

        List<ProductoF> result = productoFlux.collectList().block();

        model.addAttribute("productos", result);
        return "productos";
    }

    @GetMapping("/producto/{id}")
    public String mostrarProductoId(@PathVariable String id, Model model) throws JsonProcessingException{
        String url = "http://localhost:8080/api/Tienda/producto/" + id;
        Mono<ProductoF> productoFMono = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ProductoF.class);
        ProductoF result = productoFMono.block();

        model.addAttribute("producto", result);
        return "producto";
    }

    @GetMapping("/producto/crear")
    public String formAdd(Model model) throws JsonProcessingException{
        return "agregarprod";
    }

    @PostMapping("/productos/creando")
    public String creandoProd(
            @ModelAttribute("id") int id,
            @ModelAttribute("nombre") String nombre,
            @ModelAttribute("precio") int precio,
            @ModelAttribute("distribuidor") String distribuidor,
            @ModelAttribute("descripcion") String descripcion,
            Model model

    ){
        ProductoF productoF = new ProductoF(id, nombre, precio, distribuidor, descripcion);
        String url = "http://localhost:8080/api/Tienda/crear";

        Mono<String> resultMono = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(productoF)
                .retrieve()
                .bodyToMono(String.class);

        String result = resultMono.block();
        model.addAttribute("productonuevo", result);
        return "productoN";
    }

    @GetMapping("/producto/borrar")
    public String formDelete(Model model) throws JsonProcessingException{
        return "borrarprod";
    }

    @PostMapping("/producto/borrando")
    public String borrarProd(@ModelAttribute("id") int id, Model model){

        String url = "http://127.0.0.1:8080/api/Tienda/borrar/" + id;

        Mono<Void> resultMono = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Void.class);

        resultMono.block(); // no es necesario almacenar el resultado en una variable

        return "productoB";
    }

    @GetMapping("/producto/actualizar")
    public String actualizarP(Model model) throws JsonProcessingException{
        return "formP";
    }

    @PostMapping("/producto/actualizando")
    public String actualizarProd(
            @ModelAttribute("id") int id,
            @ModelAttribute("nombre") String nombre,
            @ModelAttribute("precio") int precio,
            @ModelAttribute("distribuidor") String distribuidor,
            @ModelAttribute("descripcion") String descripcion,
            Model model
    ){
        ProductoF productoF = new ProductoF(id, nombre, precio, distribuidor, descripcion);
        String url = "http://127.0.0.1:8080/api/Tienda/actualizar/" + id;

        Mono<String> resultMono = webClient.put()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(productoF)
                .retrieve()
                .bodyToMono(String.class);


        String result = resultMono.block();
        model.addAttribute("producto", result);
        return "productoM";
    }


}














