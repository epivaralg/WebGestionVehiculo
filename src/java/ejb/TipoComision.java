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
@Table(name = "tipo_comision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComision.findAll", query = "SELECT t FROM TipoComision t")
    , @NamedQuery(name = "TipoComision.findByIdTipoComision", query = "SELECT t FROM TipoComision t WHERE t.idTipoComision = :idTipoComision")
    , @NamedQuery(name = "TipoComision.findByDescripcion", query = "SELECT t FROM TipoComision t WHERE t.descripcion = :descripcion")})
public class TipoComision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_COMISION")
    private Integer idTipoComision;
    @Size(max = 20)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipoComision")
    private Collection<Comision> comisionCollection;

    public TipoComision() {
    }

    public TipoComision(Integer idTipoComision) {
        this.idTipoComision = idTipoComision;
    }

    public Integer getIdTipoComision() {
        return idTipoComision;
    }

    public void setIdTipoComision(Integer idTipoComision) {
        this.idTipoComision = idTipoComision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Comision> getComisionCollection() {
        return comisionCollection;
    }

    public void setComisionCollection(Collection<Comision> comisionCollection) {
        this.comisionCollection = comisionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoComision != null ? idTipoComision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComision)) {
            return false;
        }
        TipoComision other = (TipoComision) object;
        if ((this.idTipoComision == null && other.idTipoComision != null) || (this.idTipoComision != null && !this.idTipoComision.equals(other.idTipoComision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TipoComision[ idTipoComision=" + idTipoComision + " ]";
    }
    
}
