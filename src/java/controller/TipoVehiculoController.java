package controller;

import ejb.TipoVehiculo;
import ejb.Vehiculo;
import java.util.Collection;
import facade.TipoVehiculoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoVehiculoController")
@ViewScoped
public class TipoVehiculoController extends AbstractController<TipoVehiculo> {

    // Flags to indicate if child collections are empty
    private boolean isVehiculoCollectionEmpty;

    public TipoVehiculoController() {
        // Inform the Abstract parent controller of the concrete TipoVehiculo Entity
        super(TipoVehiculo.class);
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
        TipoVehiculo selected = this.getSelected();
        if (selected != null) {
            TipoVehiculoFacade ejbFacade = (TipoVehiculoFacade) this.getFacade();
            this.isVehiculoCollectionEmpty = ejbFacade.isVehiculoCollectionEmpty(selected);
        } else {
            this.isVehiculoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Vehiculo entities that
     * are retrieved from TipoVehiculo and returns the navigation outcome.
     *
     * @return navigation outcome for Vehiculo page
     */
    public String navigateVehiculoCollection() {
        TipoVehiculo selected = this.getSelected();
        if (selected != null) {
            TipoVehiculoFacade ejbFacade = (TipoVehiculoFacade) this.getFacade();
            Collection<Vehiculo> selectedVehiculoCollection = ejbFacade.findVehiculoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vehiculo_items", selectedVehiculoCollection);
        }
        return "/app/vehiculo/index";
    }

}
