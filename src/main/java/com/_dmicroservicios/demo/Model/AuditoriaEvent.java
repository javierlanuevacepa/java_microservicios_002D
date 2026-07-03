package com._dmicroservicios.demo.Model;

import java.time.Instant;

public class AuditoriaEvent {

    private String accion;
    private Long productoId;
    private String usuario;
    private Instant fecha;

    public AuditoriaEvent() {}

    public AuditoriaEvent(String accion, Long productoId, String usuario, Instant fecha) {
        this.accion = accion;
        this.productoId = productoId;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }
}