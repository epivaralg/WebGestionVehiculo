/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elliot.pivaral
 */
@Entity
@Table(name = "historia_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriaEstado.findAll", query = "SELECT h FROM HistoriaEstado h")
    , @NamedQuery(name = "HistoriaEstado.findByIdHistoriaEstado", query = "SELECT h FROM HistoriaEstado h WHERE h.idHistoriaEstado = :idHistoriaEstado")
    , @NamedQuery(name = "HistoriaEstado.findByFechaHoraEstado", query = "SELECT h FROM HistoriaEstado h WHERE h.fechaHoraEstado = :fechaHoraEstado")
    , @NamedQuery(name = "HistoriaEstado.findByComentario", query = "SELECT h FROM HistoriaEstado h WHERE h.comentario = :comentario")})
public class HistoriaEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HISTORIA_ESTADO")
    private Integer idHistoriaEstado;
    @Column(name = "FECHA_HORA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEstado;
    @Size(max = 100)
    @Column(name = "COMENTARIO")
    private String comentario;
    @JoinColumn(name = "ID_RAZON_ESTADO", referencedColumnName = "ID_RAZON_ESTADO")
    @ManyToOne
    private RazonEstado idRazonEstado;
    @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
    @ManyToOne
    private Solicitud idSolicitud;

    public HistoriaEstado() {
    }

    public HistoriaEstado(Integer idHistoriaEstado) {
        this.idHistoriaEstado = idHistoriaEstado;
    }

    public Integer getIdHistoriaEstado() {
        return idHistoriaEstado;
    }

    public void setIdHistoriaEstado(Integer idHistoriaEstado) {
        this.idHistoriaEstado = idHistoriaEstado;
    }

    public Date getFechaHoraEstado() {
        return fechaHoraEstado;
    }

    public void setFechaHoraEstado(Date fechaHoraEstado) {
        this.fechaHoraEstado = fechaHoraEstado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public RazonEstado getIdRazonEstado() {
        return idRazonEstado;
    }

    public void setIdRazonEstado(RazonEstado idRazonEstado) {
        this.idRazonEstado = idRazonEstado;
    }

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoriaEstado != null ? idHistoriaEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaEstado)) {
            return false;
        }
        HistoriaEstado other = (HistoriaEstado) object;
        if ((this.idHistoriaEstado == null && other.idHistoriaEstado != null) || (this.idHistoriaEstado != null && !this.idHistoriaEstado.equals(other.idHistoriaEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.HistoriaEstado[ idHistoriaEstado=" + idHistoriaEstado + " ]";
    }
    
}
