package controller;

import ejb.TipoSolicitud;
import ejb.Solicitud;
import java.util.Collection;
import facade.TipoSolicitudFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoSolicitudController")
@ViewScoped
public class TipoSolicitudController extends AbstractController<TipoSolicitud> {

    // Flags to indicate if child collections are empty
    private boolean isSolicitudCollectionEmpty;

    public TipoSolicitudController() {
        // Inform the Abstract parent controller of the concrete TipoSolicitud Entity
        super(TipoSolicitud.class);
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
        TipoSolicitud selected = this.getSelected();
        if (selected != null) {
            TipoSolicitudFacade ejbFacade = (TipoSolicitudFacade) this.getFacade();
            this.isSolicitudCollectionEmpty = ejbFacade.isSolicitudCollectionEmpty(selected);
        } else {
            this.isSolicitudCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from TipoSolicitud and returns the navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection() {
        TipoSolicitud selected = this.getSelected();
        if (selected != null) {
            TipoSolicitudFacade ejbFacade = (TipoSolicitudFacade) this.getFacade();
            Collection<Solicitud> selectedSolicitudCollection = ejbFacade.findSolicitudCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", selectedSolicitudCollection);
        }
        return "/app/solicitud/index";
    }

}
