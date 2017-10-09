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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elliot.pivaral
 */
@Entity
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
    , @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa")
    , @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo")
    , @NamedQuery(name = "Vehiculo.findByColor", query = "SELECT v FROM Vehiculo v WHERE v.color = :color")
    , @NamedQuery(name = "Vehiculo.findByMotor", query = "SELECT v FROM Vehiculo v WHERE v.motor = :motor")
    , @NamedQuery(name = "Vehiculo.findByChasis", query = "SELECT v FROM Vehiculo v WHERE v.chasis = :chasis")
    , @NamedQuery(name = "Vehiculo.findByCombustible", query = "SELECT v FROM Vehiculo v WHERE v.combustible = :combustible")
    , @NamedQuery(name = "Vehiculo.findByAdicional1", query = "SELECT v FROM Vehiculo v WHERE v.adicional1 = :adicional1")
    , @NamedQuery(name = "Vehiculo.findByAdicional2", query = "SELECT v FROM Vehiculo v WHERE v.adicional2 = :adicional2")
    , @NamedQuery(name = "Vehiculo.findByAdicional3", query = "SELECT v FROM Vehiculo v WHERE v.adicional3 = :adicional3")
    , @NamedQuery(name = "Vehiculo.findByAdicional4", query = "SELECT v FROM Vehiculo v WHERE v.adicional4 = :adicional4")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "MODELO")
    private Integer modelo;
    @Size(max = 20)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 50)
    @Column(name = "MOTOR")
    private String motor;
    @Size(max = 50)
    @Column(name = "CHASIS")
    private String chasis;
    @Size(max = 20)
    @Column(name = "COMBUSTIBLE")
    private String combustible;
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
    @OneToMany(mappedBy = "placa")
    private Collection<Comision> comisionCollection;
    @JoinColumn(name = "ID_ESTADO_VEHICULO", referencedColumnName = "ID_ESTADO_VEHICULO")
    @ManyToOne
    private EstadoVehiculo idEstadoVehiculo;
    @JoinColumn(name = "ID_LINEA", referencedColumnName = "ID_LINEA")
    @ManyToOne
    private Linea idLinea;
    @JoinColumn(name = "ID_TIPO_VEHICULO", referencedColumnName = "ID_TIPO_VEHICULO")
    @ManyToOne
    private TipoVehiculo idTipoVehiculo;

    public Vehiculo() {
    }

    public Vehiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
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

    public EstadoVehiculo getIdEstadoVehiculo() {
        return idEstadoVehiculo;
    }

    public void setIdEstadoVehiculo(EstadoVehiculo idEstadoVehiculo) {
        this.idEstadoVehiculo = idEstadoVehiculo;
    }

    public Linea getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Linea idLinea) {
        this.idLinea = idLinea;
    }

    public TipoVehiculo getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(TipoVehiculo idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Vehiculo[ placa=" + placa + " ]";
    }
    
}
