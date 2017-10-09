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
@Table(name = "tipo_bitacora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBitacora.findAll", query = "SELECT t FROM TipoBitacora t")
    , @NamedQuery(name = "TipoBitacora.findByIdTipoBitacora", query = "SELECT t FROM TipoBitacora t WHERE t.idTipoBitacora = :idTipoBitacora")
    , @NamedQuery(name = "TipoBitacora.findByDescripcion", query = "SELECT t FROM TipoBitacora t WHERE t.descripcion = :descripcion")})
public class TipoBitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_BITACORA")
    private Integer idTipoBitacora;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTipoBitacora")
    private Collection<Bitacora> bitacoraCollection;

    public TipoBitacora() {
    }

    public TipoBitacora(Integer idTipoBitacora) {
        this.idTipoBitacora = idTipoBitacora;
    }

    public Integer getIdTipoBitacora() {
        return idTipoBitacora;
    }

    public void setIdTipoBitacora(Integer idTipoBitacora) {
        this.idTipoBitacora = idTipoBitacora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Bitacora> getBitacoraCollection() {
        return bitacoraCollection;
    }

    public void setBitacoraCollection(Collection<Bitacora> bitacoraCollection) {
        this.bitacoraCollection = bitacoraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoBitacora != null ? idTipoBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBitacora)) {
            return false;
        }
        TipoBitacora other = (TipoBitacora) object;
        if ((this.idTipoBitacora == null && other.idTipoBitacora != null) || (this.idTipoBitacora != null && !this.idTipoBitacora.equals(other.idTipoBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TipoBitacora[ idTipoBitacora=" + idTipoBitacora + " ]";
    }
    
}
