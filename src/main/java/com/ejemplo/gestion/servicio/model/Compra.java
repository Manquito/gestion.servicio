package com.ejemplo.gestion.servicio.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID de la compra

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; // Relación con la entidad Cliente

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto; // Relación con la entidad Producto

    @Column(nullable = false)
    private Integer cantidad; // Cantidad del producto comprado

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra; // Fecha y hora en que se realizó la compra

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
