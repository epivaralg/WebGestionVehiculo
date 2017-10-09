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
@Table(name = "piloto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piloto.findAll", query = "SELECT p FROM Piloto p")
    , @NamedQuery(name = "Piloto.findByIdPiloto", query = "SELECT p FROM Piloto p WHERE p.idPiloto = :idPiloto")
    , @NamedQuery(name = "Piloto.findByUsuarioPilotoPdh", query = "SELECT p FROM Piloto p WHERE p.usuarioPilotoPdh = :usuarioPilotoPdh")
    , @NamedQuery(name = "Piloto.findByNombre", query = "SELECT p FROM Piloto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Piloto.findByApellido", query = "SELECT p FROM Piloto p WHERE p.apellido = :apellido")
    , @NamedQuery(name = "Piloto.findByFechaNacimiento", query = "SELECT p FROM Piloto p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Piloto.findByTelefono", query = "SELECT p FROM Piloto p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Piloto.findByAdicional1", query = "SELECT p FROM Piloto p WHERE p.adicional1 = :adicional1")
    , @NamedQuery(name = "Piloto.findByAdicional2", query = "SELECT p FROM Piloto p WHERE p.adicional2 = :adicional2")
    , @NamedQuery(name = "Piloto.findByAdicional3", query = "SELECT p FROM Piloto p WHERE p.adicional3 = :adicional3")
    , @NamedQuery(name = "Piloto.findByAdicional4", query = "SELECT p FROM Piloto p WHERE p.adicional4 = :adicional4")})
public class Piloto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PILOTO")
    private Integer idPiloto;
    @Size(max = 20)
    @Column(name = "USUARIO_PILOTO_PDH")
    private String usuarioPilotoPdh;
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 20)
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 20)
    @Column(name = "ADICIONAL_1")
    private String adicional1;
    @Size(max = 20)
    @Column(name = "ADICIONAL_2")
    private String adicional2;
    @Size(max = 20)
    @Column(name = "ADICIONAL_3")
    private String adicional3;
    @Size(max = 20)
    @Column(name = "ADICIONAL_4")
    private String adicional4;
    @OneToMany(mappedBy = "idPiloto")
    private Collection<Comision> comisionCollection;
    @JoinColumn(name = "ID_ESTADO_PILOTO", referencedColumnName = "ID_ESTADO_PILOTO")
    @ManyToOne
    private EstadoPiloto idEstadoPiloto;
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne
    private Grupo idGrupo;
    @OneToMany(mappedBy = "idPiloto")
    private Collection<Licencia> licenciaCollection;

    public Piloto() {
    }

    public Piloto(Integer idPiloto) {
        this.idPiloto = idPiloto;
    }

    public Integer getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(Integer idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getUsuarioPilotoPdh() {
        return usuarioPilotoPdh;
    }

    public void setUsuarioPilotoPdh(String usuarioPilotoPdh) {
        this.usuarioPilotoPdh = usuarioPilotoPdh;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    public Collection<Comision> getComisionCollection() {
        return comisionCollection;
    }

    public void setComisionCollection(Collection<Comision> comisionCollection) {
        this.comisionCollection = comisionCollection;
    }

    public EstadoPiloto getIdEstadoPiloto() {
        return idEstadoPiloto;
    }

    public void setIdEstadoPiloto(EstadoPiloto idEstadoPiloto) {
        this.idEstadoPiloto = idEstadoPiloto;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
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
        hash += (idPiloto != null ? idPiloto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piloto)) {
            return false;
        }
        Piloto other = (Piloto) object;
        if ((this.idPiloto == null && other.idPiloto != null) || (this.idPiloto != null && !this.idPiloto.equals(other.idPiloto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Piloto[ idPiloto=" + idPiloto + " ]";
    }
    
}
