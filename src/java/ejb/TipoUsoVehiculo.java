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
@Table(name = "tipo_uso_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsoVehiculo.findAll", query = "SELECT t FROM TipoUsoVehiculo t")
    , @NamedQuery(name = "TipoUsoVehiculo.findByIdTipoUsoVehiculo", query = "SELECT t FROM TipoUsoVehiculo t WHERE t.idTipoUsoVehiculo = :idTipoUsoVehiculo")
    , @NamedQuery(name = "TipoUsoVehiculo.findByDescripcion", query = "SELECT t FROM TipoUsoVehiculo t WHERE t.descripcion = :descripcion")})
public class TipoUsoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_USO_VEHICULO")
    private Integer idTipoUsoVehiculo;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipoUsoVehiculo")
    private Collection<Solicitud> solicitudCollection;

    public TipoUsoVehiculo() {
    }

    public TipoUsoVehiculo(Integer idTipoUsoVehiculo) {
        this.idTipoUsoVehiculo = idTipoUsoVehiculo;
    }

    public Integer getIdTipoUsoVehiculo() {
        return idTipoUsoVehiculo;
    }

    public void setIdTipoUsoVehiculo(Integer idTipoUsoVehiculo) {
        this.idTipoUsoVehiculo = idTipoUsoVehiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUsoVehiculo != null ? idTipoUsoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsoVehiculo)) {
            return false;
        }
        TipoUsoVehiculo other = (TipoUsoVehiculo) object;
        if ((this.idTipoUsoVehiculo == null && other.idTipoUsoVehiculo != null) || (this.idTipoUsoVehiculo != null && !this.idTipoUsoVehiculo.equals(other.idTipoUsoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TipoUsoVehiculo[ idTipoUsoVehiculo=" + idTipoUsoVehiculo + " ]";
    }
    
}
