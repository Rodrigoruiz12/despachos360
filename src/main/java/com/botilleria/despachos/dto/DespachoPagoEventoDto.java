package com.botilleria.despachos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DespachoPagoEventoDto {
    private String tipoEvento;
    private String ordenId;
    private String usuarioId;
    private String estado;
    private String mensajeAlerta;
    private String timestamp;

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public String getOrdenId() { return ordenId; }
    public void setOrdenId(String ordenId) { this.ordenId = ordenId; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMensajeAlerta() { return mensajeAlerta; }
    public void setMensajeAlerta(String mensajeAlerta) { this.mensajeAlerta = mensajeAlerta; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
