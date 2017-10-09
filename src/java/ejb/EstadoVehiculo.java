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
@Table(name = "estado_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoVehiculo.findAll", query = "SELECT e FROM EstadoVehiculo e")
    , @NamedQuery(name = "EstadoVehiculo.findByIdEstadoVehiculo", query = "SELECT e FROM EstadoVehiculo e WHERE e.idEstadoVehiculo = :idEstadoVehiculo")
    , @NamedQuery(name = "EstadoVehiculo.findByDescripcion", query = "SELECT e FROM EstadoVehiculo e WHERE e.descripcion = :descripcion")})
public class EstadoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTADO_VEHICULO")
    private Integer idEstadoVehiculo;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idEstadoVehiculo")
    private Collection<Vehiculo> vehiculoCollection;

    public EstadoVehiculo() {
    }

    public EstadoVehiculo(Integer idEstadoVehiculo) {
        this.idEstadoVehiculo = idEstadoVehiculo;
    }

    public Integer getIdEstadoVehiculo() {
        return idEstadoVehiculo;
    }

    public void setIdEstadoVehiculo(Integer idEstadoVehiculo) {
        this.idEstadoVehiculo = idEstadoVehiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoVehiculo != null ? idEstadoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoVehiculo)) {
            return false;
        }
        EstadoVehiculo other = (EstadoVehiculo) object;
        if ((this.idEstadoVehiculo == null && other.idEstadoVehiculo != null) || (this.idEstadoVehiculo != null && !this.idEstadoVehiculo.equals(other.idEstadoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.EstadoVehiculo[ idEstadoVehiculo=" + idEstadoVehiculo + " ]";
    }
    
}
