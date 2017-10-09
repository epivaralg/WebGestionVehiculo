package controller;

import ejb.Vehiculo;
import ejb.Comision;
import java.util.Collection;
import facade.VehiculoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "vehiculoController")
@ViewScoped
public class VehiculoController extends AbstractController<Vehiculo> {

    @Inject
    private EstadoVehiculoController idEstadoVehiculoController;
    @Inject
    private LineaController idLineaController;
    @Inject
    private TipoVehiculoController idTipoVehiculoController;

    // Flags to indicate if child collections are empty
    private boolean isComisionCollectionEmpty;

    public VehiculoController() {
        // Inform the Abstract parent controller of the concrete Vehiculo Entity
        super(Vehiculo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idEstadoVehiculoController.setSelected(null);
        idLineaController.setSelected(null);
        idTipoVehiculoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsComisionCollectionEmpty();
    }

    public boolean getIsComisionCollectionEmpty() {
        return this.isComisionCollectionEmpty;
    }

    private void setIsComisionCollectionEmpty() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            this.isComisionCollectionEmpty = ejbFacade.isComisionCollectionEmpty(selected);
        } else {
            this.isComisionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Comision entities that
     * are retrieved from Vehiculo and returns the navigation outcome.
     *
     * @return navigation outcome for Comision page
     */
    public String navigateComisionCollection() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            Collection<Comision> selectedComisionCollection = ejbFacade.findComisionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Comision_items", selectedComisionCollection);
        }
        return "/app/comision/index";
    }

    /**
     * Sets the "selected" attribute of the EstadoVehiculo controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEstadoVehiculo(ActionEvent event) {
        Vehiculo selected = this.getSelected();
        if (selected != null && idEstadoVehiculoController.getSelected() == null) {
            idEstadoVehiculoController.setSelected(selected.getIdEstadoVehiculo());
        }
    }

    /**
     * Sets the "selected" attribute of the Linea controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdLinea(ActionEvent event) {
        Vehiculo selected = this.getSelected();
        if (selected != null && idLineaController.getSelected() == null) {
            idLineaController.setSelected(selected.getIdLinea());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoVehiculo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoVehiculo(ActionEvent event) {
        Vehiculo selected = this.getSelected();
        if (selected != null && idTipoVehiculoController.getSelected() == null) {
            idTipoVehiculoController.setSelected(selected.getIdTipoVehiculo());
        }
    }

}
