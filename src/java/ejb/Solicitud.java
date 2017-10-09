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
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "Solicitud.findByFechaHoraSolicitud", query = "SELECT s FROM Solicitud s WHERE s.fechaHoraSolicitud = :fechaHoraSolicitud")
    , @NamedQuery(name = "Solicitud.findByUsuarioPdh", query = "SELECT s FROM Solicitud s WHERE s.usuarioPdh = :usuarioPdh")
    , @NamedQuery(name = "Solicitud.findByNombreEncargado", query = "SELECT s FROM Solicitud s WHERE s.nombreEncargado = :nombreEncargado")
    , @NamedQuery(name = "Solicitud.findByTelefono", query = "SELECT s FROM Solicitud s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Solicitud.findByCantidadPasajeros", query = "SELECT s FROM Solicitud s WHERE s.cantidadPasajeros = :cantidadPasajeros")
    , @NamedQuery(name = "Solicitud.findByFechaHoraSalida", query = "SELECT s FROM Solicitud s WHERE s.fechaHoraSalida = :fechaHoraSalida")
    , @NamedQuery(name = "Solicitud.findByFechaHoraRegreso", query = "SELECT s FROM Solicitud s WHERE s.fechaHoraRegreso = :fechaHoraRegreso")
    , @NamedQuery(name = "Solicitud.findByComentario", query = "SELECT s FROM Solicitud s WHERE s.comentario = :comentario")
    , @NamedQuery(name = "Solicitud.findByAdicional1", query = "SELECT s FROM Solicitud s WHERE s.adicional1 = :adicional1")
    , @NamedQuery(name = "Solicitud.findByAdicional2", query = "SELECT s FROM Solicitud s WHERE s.adicional2 = :adicional2")
    , @NamedQuery(name = "Solicitud.findByAdicional3", query = "SELECT s FROM Solicitud s WHERE s.adicional3 = :adicional3")
    , @NamedQuery(name = "Solicitud.findByAdicional4", query = "SELECT s FROM Solicitud s WHERE s.adicional4 = :adicional4")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SOLICITUD")
    private Integer idSolicitud;
    @Column(name = "FECHA_HORA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSolicitud;
    @Size(max = 20)
    @Column(name = "USUARIO_PDH")
    private String usuarioPdh;
    @Size(max = 50)
    @Column(name = "NOMBRE_ENCARGADO")
    private String nombreEncargado;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CANTIDAD_PASAJEROS")
    private Integer cantidadPasajeros;
    @Column(name = "FECHA_HORA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSalida;
    @Column(name = "FECHA_HORA_REGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegreso;
    @Size(max = 100)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Size(max = 20)
    @Column(name = "ADICIONAL_1")
    private String adicional1;
    @Size(max = 20)
    @Column(name = "ADICIONAL_2")
    private String adicional2;
    @Size(max = 20)
    @Column(name = "ADICIONAL_3")
    private String adicional3;
    @Size(max = 40)
    @Column(name = "ADICIONAL_4")
    private String adicional4;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<HistoriaEstado> historiaEstadoCollection;
    @OneToMany(mappedBy = "idSolicitud")
    private Collection<Ruta> rutaCollection;
    @JoinColumn(name = "ID_USUARIO_APROBADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuarioAprobador;
    @JoinColumn(name = "ID_COMISION", referencedColumnName = "ID_COMISION")
    @ManyToOne
    private Comision idComision;
    @JoinColumn(name = "ID_USUARIO_GESTOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuarioGestor;
    @JoinColumn(name = "ID_TIPO_COMISION", referencedColumnName = "ID_TIPO_SOLICITUD")
    @ManyToOne
    private TipoSolicitud idTipoComision;
    @JoinColumn(name = "ID_TIPO_USO_VEHICULO", referencedColumnName = "ID_TIPO_USO_VEHICULO")
    @ManyToOne
    private TipoUsoVehiculo idTipoUsoVehiculo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public Solicitud() {
    }

    public Solicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public String getUsuarioPdh() {
        return usuarioPdh;
    }

    public void setUsuarioPdh(String usuarioPdh) {
        this.usuarioPdh = usuarioPdh;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(Integer cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    public String getAdicional3() {
        return adicional3;
    }

    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    public String getAdicional4() {
        return adicional4;
    }

    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    @XmlTransient
    public Collection<HistoriaEstado> getHistoriaEstadoCollection() {
        return historiaEstadoCollection;
    }

    public void setHistoriaEstadoCollection(Collection<HistoriaEstado> historiaEstadoCollection) {
        this.historiaEstadoCollection = historiaEstadoCollection;
    }

    @XmlTransient
    public Collection<Ruta> getRutaCollection() {
        return rutaCollection;
    }

    public void setRutaCollection(Collection<Ruta> rutaCollection) {
        this.rutaCollection = rutaCollection;
    }

    public Usuario getIdUsuarioAprobador() {
        return idUsuarioAprobador;
    }

    public void setIdUsuarioAprobador(Usuario idUsuarioAprobador) {
        this.idUsuarioAprobador = idUsuarioAprobador;
    }

    public Comision getIdComision() {
        return idComision;
    }

    public void setIdComision(Comision idComision) {
        this.idComision = idComision;
    }

    public Usuario getIdUsuarioGestor() {
        return idUsuarioGestor;
    }

    public void setIdUsuarioGestor(Usuario idUsuarioGestor) {
        this.idUsuarioGestor = idUsuarioGestor;
    }

    public TipoSolicitud getIdTipoComision() {
        return idTipoComision;
    }

    public void setIdTipoComision(TipoSolicitud idTipoComision) {
        this.idTipoComision = idTipoComision;
    }

    public TipoUsoVehiculo getIdTipoUsoVehiculo() {
        return idTipoUsoVehiculo;
    }

    public void setIdTipoUsoVehiculo(TipoUsoVehiculo idTipoUsoVehiculo) {
        this.idTipoUsoVehiculo = idTipoUsoVehiculo;
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
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Solicitud[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
