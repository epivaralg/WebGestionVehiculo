/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elliot.pivaral
 */
@Entity
@Table(name = "comision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comision.findAll", query = "SELECT c FROM Comision c")
    , @NamedQuery(name = "Comision.findByIdComision", query = "SELECT c FROM Comision c WHERE c.idComision = :idComision")
    , @NamedQuery(name = "Comision.findByFechaHoraSalida", query = "SELECT c FROM Comision c WHERE c.fechaHoraSalida = :fechaHoraSalida")
    , @NamedQuery(name = "Comision.findByFechaHoraRegreso", query = "SELECT c FROM Comision c WHERE c.fechaHoraRegreso = :fechaHoraRegreso")
    , @NamedQuery(name = "Comision.findByKmSalida", query = "SELECT c FROM Comision c WHERE c.kmSalida = :kmSalida")
    , @NamedQuery(name = "Comision.findByKmRegreso", query = "SELECT c FROM Comision c WHERE c.kmRegreso = :kmRegreso")
    , @NamedQuery(name = "Comision.findByInformePiloto", query = "SELECT c FROM Comision c WHERE c.informePiloto = :informePiloto")})
public class Comision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMISION")
    private Integer idComision;
    @Column(name = "FECHA_HORA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSalida;
    @Column(name = "FECHA_HORA_REGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegreso;
    @Column(name = "KM_SALIDA")
    private Integer kmSalida;
    @Column(name = "KM_REGRESO")
    private Integer kmRegreso;
    @Size(max = 100)
    @Column(name = "INFORME_PILOTO")
    private String informePiloto;
    @JoinColumn(name = "ID_PILOTO", referencedColumnName = "ID_PILOTO")
    @ManyToOne
    private Piloto idPiloto;
    @JoinColumn(name = "ID_TIPO_COMISION", referencedColumnName = "ID_TIPO_COMISION")
    @ManyToOne
    private TipoComision idTipoComision;
    @JoinColumn(name = "PLACA", referencedColumnName = "PLACA")
    @ManyToOne
    private Vehiculo placa;
    @OneToMany(mappedBy = "idComision")
    private Collection<Solicitud> solicitudCollection;

    public Comision() {
    }

    public Comision(Integer idComision) {
        this.idComision = idComision;
    }

    public Integer getIdComision() {
        return idComision;
    }

    public void setIdComision(Integer idComision) {
        this.idComision = idComision;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Date getFechaHoraRegreso() {
        return fechaHoraRegreso;
    }

    public void setFechaHoraRegreso(Date fechaHoraRegreso) {
        this.fechaHoraRegreso = fechaHoraRegreso;
    }

    public Integer getKmSalida() {
        return kmSalida;
    }

    public void setKmSalida(Integer kmSalida) {
        this.kmSalida = kmSalida;
    }

    public Integer getKmRegreso() {
        return kmRegreso;
    }

    public void setKmRegreso(Integer kmRegreso) {
        this.kmRegreso = kmRegreso;
    }

    public String getInformePiloto() {
        return informePiloto;
    }

    public void setInformePiloto(String informePiloto) {
        this.informePiloto = informePiloto;
    }

    public Piloto getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(Piloto idPiloto) {
        this.idPiloto = idPiloto;
    }

    public TipoComision getIdTipoComision() {
        return idTipoComision;
    }

    public void setIdTipoComision(TipoComision idTipoComision) {
        this.idTipoComision = idTipoComision;
    }

    public Vehiculo getPlaca() {
        return placa;
    }

    public void setPlaca(Vehiculo placa) {
        this.placa = placa;
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
        hash += (idComision != null ? idComision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comision)) {
            return false;
        }
        Comision other = (Comision) object;
        if ((this.idComision == null && other.idComision != null) || (this.idComision != null && !this.idComision.equals(other.idComision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Comision[ idComision=" + idComision + " ]";
    }
    
}
