package controller;

import ejb.EstadoVehiculo;
import ejb.Vehiculo;
import java.util.Collection;
import facade.EstadoVehiculoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "estadoVehiculoController")
@ViewScoped
public class EstadoVehiculoController extends AbstractController<EstadoVehiculo> {

    // Flags to indicate if child collections are empty
    private boolean isVehiculoCollectionEmpty;

    public EstadoVehiculoController() {
        // Inform the Abstract parent controller of the concrete EstadoVehiculo Entity
        super(EstadoVehiculo.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsVehiculoCollectionEmpty();
    }

    public boolean getIsVehiculoCollectionEmpty() {
        return this.isVehiculoCollectionEmpty;
    }

    private void setIsVehiculoCollectionEmpty() {
        EstadoVehiculo selected = this.getSelected();
        if (selected != null) {
            EstadoVehiculoFacade ejbFacade = (EstadoVehiculoFacade) this.getFacade();
            this.isVehiculoCollectionEmpty = ejbFacade.isVehiculoCollectionEmpty(selected);
        } else {
            this.isVehiculoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Vehiculo entities that
     * are retrieved from EstadoVehiculo and returns the navigation outcome.
     *
     * @return navigation outcome for Vehiculo page
     */
    public String navigateVehiculoCollection() {
        EstadoVehiculo selected = this.getSelected();
        if (selected != null) {
            EstadoVehiculoFacade ejbFacade = (EstadoVehiculoFacade) this.getFacade();
            Collection<Vehiculo> selectedVehiculoCollection = ejbFacade.findVehiculoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vehiculo_items", selectedVehiculoCollection);
        }
        return "/app/vehiculo/index";
    }

}
