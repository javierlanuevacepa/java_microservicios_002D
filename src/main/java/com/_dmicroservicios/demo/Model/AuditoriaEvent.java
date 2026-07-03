package com._dmicroservicios.demo.Model;



public class AuditoriaEvent {

    private String accion;
    private Long productoId;
    private String usuario;
    private String fecha;

    public AuditoriaEvent() {}

    public AuditoriaEvent(String accion, Long productoId, String usuario, String fecha) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}