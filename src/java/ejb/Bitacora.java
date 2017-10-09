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
@Table(name = "bitacora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b")
    , @NamedQuery(name = "Bitacora.findByIdBitacora", query = "SELECT b FROM Bitacora b WHERE b.idBitacora = :idBitacora")
    , @NamedQuery(name = "Bitacora.findByFechaHora", query = "SELECT b FROM Bitacora b WHERE b.fechaHora = :fechaHora")
    , @NamedQuery(name = "Bitacora.findByTabla", query = "SELECT b FROM Bitacora b WHERE b.tabla = :tabla")
    , @NamedQuery(name = "Bitacora.findByCampo", query = "SELECT b FROM Bitacora b WHERE b.campo = :campo")
    , @NamedQuery(name = "Bitacora.findByValorAnterior", query = "SELECT b FROM Bitacora b WHERE b.valorAnterior = :valorAnterior")
    , @NamedQuery(name = "Bitacora.findByValorNuevo", query = "SELECT b FROM Bitacora b WHERE b.valorNuevo = :valorNuevo")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BITACORA")
    private Integer idBitacora;
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Size(max = 20)
    @Column(name = "TABLA")
    private String tabla;
    @Size(max = 20)
    @Column(name = "CAMPO")
    private String campo;
    @Size(max = 100)
    @Column(name = "VALOR_ANTERIOR")
    private String valorAnterior;
    @Size(max = 100)
    @Column(name = "VALOR_NUEVO")
    private String valorNuevo;
    @JoinColumn(name = "ID_TIPO_BITACORA", referencedColumnName = "ID_TIPO_BITACORA")
    @ManyToOne
    private TipoBitacora idTipoBitacora;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public Bitacora() {
    }

    public Bitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public TipoBitacora getIdTipoBitacora() {
        return idTipoBitacora;
    }

    public void setIdTipoBitacora(TipoBitacora idTipoBitacora) {
        this.idTipoBitacora = idTipoBitacora;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Bitacora[ idBitacora=" + idBitacora + " ]";
    }
    
}
