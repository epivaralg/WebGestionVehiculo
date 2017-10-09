package controller;

import ejb.EstadoSolicitud;
import ejb.RazonEstado;
import java.util.Collection;
import facade.EstadoSolicitudFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "estadoSolicitudController")
@ViewScoped
public class EstadoSolicitudController extends AbstractController<EstadoSolicitud> {

    // Flags to indicate if child collections are empty
    private boolean isRazonEstadoCollectionEmpty;

    public EstadoSolicitudController() {
        // Inform the Abstract parent controller of the concrete EstadoSolicitud Entity
        super(EstadoSolicitud.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRazonEstadoCollectionEmpty();
    }

    public boolean getIsRazonEstadoCollectionEmpty() {
        return this.isRazonEstadoCollectionEmpty;
    }

    private void setIsRazonEstadoCollectionEmpty() {
        EstadoSolicitud selected = this.getSelected();
        if (selected != null) {
            EstadoSolicitudFacade ejbFacade = (EstadoSolicitudFacade) this.getFacade();
            this.isRazonEstadoCollectionEmpty = ejbFacade.isRazonEstadoCollectionEmpty(selected);
        } else {
            this.isRazonEstadoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RazonEstado entities that
     * are retrieved from EstadoSolicitud and returns the navigation outcome.
     *
     * @return navigation outcome for RazonEstado page
     */
    public String navigateRazonEstadoCollection() {
        EstadoSolicitud selected = this.getSelected();
        if (selected != null) {
            EstadoSolicitudFacade ejbFacade = (EstadoSolicitudFacade) this.getFacade();
            Collection<RazonEstado> selectedRazonEstadoCollection = ejbFacade.findRazonEstadoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RazonEstado_items", selectedRazonEstadoCollection);
        }
        return "/app/razonEstado/index";
    }

}
