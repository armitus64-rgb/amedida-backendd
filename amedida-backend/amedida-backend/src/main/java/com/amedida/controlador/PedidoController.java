package com.amedida.controlador;

import com.amedida.modelo.Pedido;
import com.amedida.servicio.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // GET /api/pedidos/usuario/3
    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return pedidoService.obtenerPorUsuario(usuarioId);
    }

    // La creación de un pedido (POST) se agrega cuando conectemos
    // el botón "Confirmar pedido" del carrito con el backend.
}
