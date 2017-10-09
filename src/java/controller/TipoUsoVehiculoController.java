package controller;

import ejb.TipoUsoVehiculo;
import ejb.Solicitud;
import java.util.Collection;
import facade.TipoUsoVehiculoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoUsoVehiculoController")
@ViewScoped
public class TipoUsoVehiculoController extends AbstractController<TipoUsoVehiculo> {

    // Flags to indicate if child collections are empty
    private boolean isSolicitudCollectionEmpty;

    public TipoUsoVehiculoController() {
        // Inform the Abstract parent controller of the concrete TipoUsoVehiculo Entity
        super(TipoUsoVehiculo.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSolicitudCollectionEmpty();
    }

    public boolean getIsSolicitudCollectionEmpty() {
        return this.isSolicitudCollectionEmpty;
    }

    private void setIsSolicitudCollectionEmpty() {
        TipoUsoVehiculo selected = this.getSelected();
        if (selected != null) {
            TipoUsoVehiculoFacade ejbFacade = (TipoUsoVehiculoFacade) this.getFacade();
            this.isSolicitudCollectionEmpty = ejbFacade.isSolicitudCollectionEmpty(selected);
        } else {
            this.isSolicitudCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from TipoUsoVehiculo and returns the navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection() {
        TipoUsoVehiculo selected = this.getSelected();
        if (selected != null) {
            TipoUsoVehiculoFacade ejbFacade = (TipoUsoVehiculoFacade) this.getFacade();
            Collection<Solicitud> selectedSolicitudCollection = ejbFacade.findSolicitudCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", selectedSolicitudCollection);
        }
        return "/app/solicitud/index";
    }

}
