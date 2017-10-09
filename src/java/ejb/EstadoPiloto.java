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
@Table(name = "estado_piloto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPiloto.findAll", query = "SELECT e FROM EstadoPiloto e")
    , @NamedQuery(name = "EstadoPiloto.findByIdEstadoPiloto", query = "SELECT e FROM EstadoPiloto e WHERE e.idEstadoPiloto = :idEstadoPiloto")
    , @NamedQuery(name = "EstadoPiloto.findByDescripcion", query = "SELECT e FROM EstadoPiloto e WHERE e.descripcion = :descripcion")})
public class EstadoPiloto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTADO_PILOTO")
    private Integer idEstadoPiloto;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idEstadoPiloto")
    private Collection<Piloto> pilotoCollection;

    public EstadoPiloto() {
    }

    public EstadoPiloto(Integer idEstadoPiloto) {
        this.idEstadoPiloto = idEstadoPiloto;
    }

    public Integer getIdEstadoPiloto() {
        return idEstadoPiloto;
    }

    public void setIdEstadoPiloto(Integer idEstadoPiloto) {
        this.idEstadoPiloto = idEstadoPiloto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Piloto> getPilotoCollection() {
        return pilotoCollection;
    }

    public void setPilotoCollection(Collection<Piloto> pilotoCollection) {
        this.pilotoCollection = pilotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPiloto != null ? idEstadoPiloto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPiloto)) {
            return false;
        }
        EstadoPiloto other = (EstadoPiloto) object;
        if ((this.idEstadoPiloto == null && other.idEstadoPiloto != null) || (this.idEstadoPiloto != null && !this.idEstadoPiloto.equals(other.idEstadoPiloto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.EstadoPiloto[ idEstadoPiloto=" + idEstadoPiloto + " ]";
    }
    
}
