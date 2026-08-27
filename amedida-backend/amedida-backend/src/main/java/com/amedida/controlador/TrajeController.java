package com.amedida.controlador;

import com.amedida.modelo.Traje;
import com.amedida.servicio.TrajeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Expone el catálogo. @RestController hace que todo lo que
 * devuelvan estos métodos se convierta automáticamente a JSON.
 *
 * Equivale al array "products" que tenías escrito a mano en el
 * JavaScript — la diferencia es que ahora vive en Java y el
 * front-end lo pide con fetch().
 */
@RestController
@RequestMapping("/api/trajes")
public class TrajeController {

    private final TrajeService trajeService;

    public TrajeController(TrajeService trajeService) {
        this.trajeService = trajeService;
    }

    // GET /api/trajes            -> todos
    // GET /api/trajes?categoria=dama
    @GetMapping
    public List<Traje> listar(@RequestParam(required = false) String categoria) {
        return trajeService.filtrarPorCategoria(categoria);
    }

    // GET /api/trajes/1
    @GetMapping("/{id}")
    public Traje obtenerUno(@PathVariable Long id) {
        return trajeService.buscarPorId(id);
    }
}
