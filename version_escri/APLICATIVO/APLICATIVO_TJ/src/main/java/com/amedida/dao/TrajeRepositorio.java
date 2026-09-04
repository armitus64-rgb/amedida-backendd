package com.amedida.dao;

import com.amedida.modelo.Traje;

import java.util.List;

/**
 * Contrato para guardar y consultar trajes, sin importar CÓMO se
 * almacenen realmente (en memoria, en SQLite, en MySQL, en MongoDB...).
 *
 * La ventana y los paneles (la GUI) solo conocen esta interfaz —
 * nunca hablan directo con una base de datos. Esto se llama
 * "programar contra una interfaz, no una implementación", y es lo
 * que permite que el compañero que hace la base de datos entregue
 * su clase después, sin que nadie tenga que tocar la parte visual.
 *
 * Hoy existe UNA implementación: RepositorioEnMemoria.
 * Cuando esté lista la base de datos, se agrega otra, por ejemplo
 * RepositorioSQLite, que cumpla exactamente estos mismos métodos.
 */
public interface TrajeRepositorio {

    void insertar(Traje traje) throws Exception;

    List<Traje> listarTodos() throws Exception;
}
