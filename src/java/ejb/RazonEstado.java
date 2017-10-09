/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elliot.pivaral
 */
@Entity
@Table(name = "razon_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RazonEstado.findAll", query = "SELECT r FROM RazonEstado r")
    , @NamedQuery(name = "RazonEstado.findByIdRazonEstado", query = "SELECT r FROM RazonEstado r WHERE r.idRazonEstado = :idRazonEstado")
    , @NamedQuery(name = "RazonEstado.findByDescripcion", query = "SELECT r FROM RazonEstado r WHERE r.descripcion = :descripcion")})
public class RazonEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RAZON_ESTADO")
    private Integer idRazonEstado;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idRazonEstado")
    private Collection<HistoriaEstado> historiaEstadoCollection;
    @JoinColumn(name = "ID_ESTADO_SOLICITUD", referencedColumnName = "ID_ESTADO_SOLICITUD")
    @ManyToOne
    private EstadoSolicitud idEstadoSolicitud;

    public RazonEstado() {
    }

    public RazonEstado(Integer idRazonEstado) {
        this.idRazonEstado = idRazonEstado;
    }

    public Integer getIdRazonEstado() {
        return idRazonEstado;
    }

    public void setIdRazonEstado(Integer idRazonEstado) {
        this.idRazonEstado = idRazonEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<HistoriaEstado> getHistoriaEstadoCollection() {
        return historiaEstadoCollection;
    }

    public void setHistoriaEstadoCollection(Collection<HistoriaEstado> historiaEstadoCollection) {
        this.historiaEstadoCollection = historiaEstadoCollection;
    }

    public EstadoSolicitud getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(EstadoSolicitud idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRazonEstado != null ? idRazonEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RazonEstado)) {
            return false;
        }
        RazonEstado other = (RazonEstado) object;
        if ((this.idRazonEstado == null && other.idRazonEstado != null) || (this.idRazonEstado != null && !this.idRazonEstado.equals(other.idRazonEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.RazonEstado[ idRazonEstado=" + idRazonEstado + " ]";
    }
    
}
