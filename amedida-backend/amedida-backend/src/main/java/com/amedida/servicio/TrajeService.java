package com.amedida.servicio;

import com.amedida.modelo.Color;
import com.amedida.modelo.Tela;
import com.amedida.modelo.Traje;
import com.amedida.modelo.TrajeCaballero;
import com.amedida.modelo.TrajeDama;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene la lógica relacionada a los trajes: listarlos, filtrarlos, buscarlos.
 * Por ahora los datos viven en una lista en memoria (ArrayList), igual que el
 * array "products" que tenías en JavaScript. Cuando quieras persistencia real,
 * este es el único lugar que cambiaría: reemplazas la lista por consultas
 * a una base de datos (por ejemplo con Spring Data JPA), y el resto del
 * proyecto (controladores, front-end) no se entera del cambio.
 */
@Service
public class TrajeService {

    private final List<Traje> trajes = new ArrayList<>();

    public TrajeService() {
        cargarDatosDeEjemplo();
    }

    public List<Traje> obtenerTodos() {
        return trajes;
    }

    public List<Traje> filtrarPorCategoria(String categoria) {
        if (categoria == null || categoria.equalsIgnoreCase("todos")) {
            return trajes;
        }
        return trajes.stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public Traje buscarPorId(Long id) {
        return trajes.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Traje no encontrado: " + id));
    }

    private void cargarDatosDeEjemplo() {
        trajes.add(new TrajeCaballero(
                1L, "Traje Ejecutivo Azul", "ejecutivo", 480000,
                "Corte clásico en lana peinada, forro completo, solapa de pico.",
                List.of("Lana peinada 130s", "Forro transpirable", "Solapa de pico"),
                List.of(new Color("#1F3D2B", "Verde noche"), new Color("#22304a", "Azul marino")),
                List.of(new Tela("Lana peinada", 0), new Tela("Lana fría", 35000)),
                "entallado"
        ));

        trajes.add(new TrajeDama(
                2L, "Traje Sastre Rosa Palo", "ejecutivo", 410000,
                "Blazer y falda a juego en tono empolvado, hombros estructurados.",
                List.of("Blazer + falda", "Hombros estructurados", "Forro interior"),
                List.of(new Color("#C97B84", "Rosa palo"), new Color("#8f5a63", "Vino")),
                List.of(new Tela("Crepé", 0), new Tela("Lana fría", 30000)),
                "entallada"
        ));

        // Puedes seguir agregando aquí el resto de tu catálogo original.
    }
}
