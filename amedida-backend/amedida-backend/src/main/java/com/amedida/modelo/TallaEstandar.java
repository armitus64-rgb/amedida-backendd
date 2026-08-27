package com.amedida.modelo;

/**
 * Tallas fijas disponibles cuando el cliente NO quiere ingresar
 * una medida personalizada. Usar un enum (en vez de un String suelto
 * como "S" o "m") evita errores de escritura y deja explícitos
 * los únicos valores válidos.
 */
public enum TallaEstandar {
    S, M, L, XL
}
