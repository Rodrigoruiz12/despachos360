package com.botilleria.despachos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "despachos")
public class Despacho {

    @Id
    private String id;

    @Column(name = "orden_id")
    private String ordenId;

    @Column(name = "codigo_seguimiento")
    private String codigoSeguimiento;

    private String estado;

    public Despacho() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrdenId() { return ordenId; }
    public void setOrdenId(String ordenId) { this.ordenId = ordenId; }
    public String getCodigoSeguimiento() { return codigoSeguimiento; }
    public void setCodigoSeguimiento(String codigoSeguimiento) { this.codigoSeguimiento = codigoSeguimiento; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
