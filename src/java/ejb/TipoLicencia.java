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
@Table(name = "tipo_licencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLicencia.findAll", query = "SELECT t FROM TipoLicencia t")
    , @NamedQuery(name = "TipoLicencia.findByIdTipoLicencia", query = "SELECT t FROM TipoLicencia t WHERE t.idTipoLicencia = :idTipoLicencia")
    , @NamedQuery(name = "TipoLicencia.findByDescripcion", query = "SELECT t FROM TipoLicencia t WHERE t.descripcion = :descripcion")})
public class TipoLicencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_LICENCIA")
    private Integer idTipoLicencia;
    @Size(max = 10)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipoLicencia")
    private Collection<Licencia> licenciaCollection;

    public TipoLicencia() {
    }

    public TipoLicencia(Integer idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    public Integer getIdTipoLicencia() {
        return idTipoLicencia;
    }

    public void setIdTipoLicencia(Integer idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Licencia> getLicenciaCollection() {
        return licenciaCollection;
    }

    public void setLicenciaCollection(Collection<Licencia> licenciaCollection) {
        this.licenciaCollection = licenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoLicencia != null ? idTipoLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLicencia)) {
            return false;
        }
        TipoLicencia other = (TipoLicencia) object;
        if ((this.idTipoLicencia == null && other.idTipoLicencia != null) || (this.idTipoLicencia != null && !this.idTipoLicencia.equals(other.idTipoLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TipoLicencia[ idTipoLicencia=" + idTipoLicencia + " ]";
    }
    
}
