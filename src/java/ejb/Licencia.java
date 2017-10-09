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
@Table(name = "licencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Licencia.findAll", query = "SELECT l FROM Licencia l")
    , @NamedQuery(name = "Licencia.findByIdLicencia", query = "SELECT l FROM Licencia l WHERE l.idLicencia = :idLicencia")
    , @NamedQuery(name = "Licencia.findByNumero", query = "SELECT l FROM Licencia l WHERE l.numero = :numero")
    , @NamedQuery(name = "Licencia.findByFechaVencimiento", query = "SELECT l FROM Licencia l WHERE l.fechaVencimiento = :fechaVencimiento")})
public class Licencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LICENCIA")
    private Integer idLicencia;
    @Size(max = 20)
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @JoinColumn(name = "ID_PILOTO", referencedColumnName = "ID_PILOTO")
    @ManyToOne
    private Piloto idPiloto;
    @JoinColumn(name = "ID_TIPO_LICENCIA", referencedColumnName = "ID_TIPO_LICENCIA")
    @ManyToOne
    private TipoLicencia idTipoLicencia;

    public Licencia() {
    }

    public Licencia(Integer idLicencia) {
        this.idLicencia = idLicencia;
    }

    public Integer getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Integer idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Piloto getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(Piloto idPiloto) {
        this.idPiloto = idPiloto;
    }

    public TipoLicencia getIdTipoLicencia() {
        return idTipoLicencia;
    }

    public void setIdTipoLicencia(TipoLicencia idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLicencia != null ? idLicencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licencia)) {
            return false;
        }
        Licencia other = (Licencia) object;
        if ((this.idLicencia == null && other.idLicencia != null) || (this.idLicencia != null && !this.idLicencia.equals(other.idLicencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Licencia[ idLicencia=" + idLicencia + " ]";
    }
    
}
