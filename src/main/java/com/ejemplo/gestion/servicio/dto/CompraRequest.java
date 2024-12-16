package com.ejemplo.gestion.servicio.dto;

public class CompraRequest {

    private Long clienteId;   // ID del cliente que realiza la compra
    private Long productoId;  // ID del producto comprado
    private Integer cantidad; // Cantidad del producto que compra

    // Getters y Setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
