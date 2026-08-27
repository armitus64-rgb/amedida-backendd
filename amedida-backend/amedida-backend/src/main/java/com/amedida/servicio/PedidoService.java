package com.amedida.servicio;

import com.amedida.modelo.ItemCarrito;
import com.amedida.modelo.Pedido;
import com.amedida.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PedidoService {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Pedido confirmarPedido(Usuario usuario, List<ItemCarrito> items) {
        Pedido pedido = new Pedido(contadorId.getAndIncrement(), usuario, items);
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> obtenerPorUsuario(Long usuarioId) {
        return pedidos.stream()
                .filter(p -> p.getUsuario().getId().equals(usuarioId))
                .toList();
    }
}
