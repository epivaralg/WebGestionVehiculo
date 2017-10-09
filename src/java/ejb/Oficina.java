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
@Table(name = "oficina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficina.findAll", query = "SELECT o FROM Oficina o")
    , @NamedQuery(name = "Oficina.findByIdOficina", query = "SELECT o FROM Oficina o WHERE o.idOficina = :idOficina")
    , @NamedQuery(name = "Oficina.findByDescripcion", query = "SELECT o FROM Oficina o WHERE o.descripcion = :descripcion")})
public class Oficina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OFICINA")
    private Integer idOficina;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
    @ManyToOne
    private Departamento idDepartamento;
    @OneToMany(mappedBy = "idOficina")
    private Collection<Unidad> unidadCollection;

    public Oficina() {
    }

    public Oficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    public Integer getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @XmlTransient
    public Collection<Unidad> getUnidadCollection() {
        return unidadCollection;
    }

    public void setUnidadCollection(Collection<Unidad> unidadCollection) {
        this.unidadCollection = unidadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOficina != null ? idOficina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficina)) {
            return false;
        }
        Oficina other = (Oficina) object;
        if ((this.idOficina == null && other.idOficina != null) || (this.idOficina != null && !this.idOficina.equals(other.idOficina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Oficina[ idOficina=" + idOficina + " ]";
    }
    
}
