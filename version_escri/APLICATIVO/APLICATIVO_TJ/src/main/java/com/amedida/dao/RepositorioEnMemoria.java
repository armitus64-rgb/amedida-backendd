package com.amedida.dao;

import com.amedida.modelo.Traje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementación PROVISIONAL de TrajeRepositorio: guarda los trajes
 * en una simple lista en memoria (ArrayList), como el array que
 * usábamos al inicio en JavaScript.
 *
 * Ventaja: la GUI funciona completa desde ya, sin instalar ni
 * configurar ninguna base de datos.
 * Limitación (a propósito, por ahora): si cierras la aplicación,
 * los datos se pierden — porque no hay persistencia real todavía.
 * Eso es justo lo que agregará tu compañero con RepositorioSQLite.
 */
public class RepositorioEnMemoria implements TrajeRepositorio {

    private final List<Traje> trajes = new ArrayList<>();
    private long siguienteId = 1;

    @Override
    public void insertar(Traje traje) {
        traje.setId(siguienteId++);
        trajes.add(traje);
    }

    @Override
    public List<Traje> listarTodos() {
        List<Traje> copia = new ArrayList<>(trajes);
        Collections.reverse(copia); // los más recientes primero
        return copia;
    }
}
