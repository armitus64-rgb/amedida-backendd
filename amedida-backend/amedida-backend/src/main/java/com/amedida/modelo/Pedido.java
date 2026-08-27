package com.amedida.modelo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Un pedido confirmado: el usuario que compró, qué items,
 * cuándo, y en qué estado va la confección/envío.
 */
public class Pedido {

    private Long id;
    private Usuario usuario;
    private List<ItemCarrito> items;
    private LocalDateTime fecha;
    private EstadoPedido estado;

    public Pedido(Long id, Usuario usuario, List<ItemCarrito> items) {
        this.id = id;
        this.usuario = usuario;
        this.items = items;
        this.fecha = LocalDateTime.now();
        this.estado = EstadoPedido.RECIBIDO;
    }

    public double getTotal() {
        return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }

    public void avanzarEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }
}
