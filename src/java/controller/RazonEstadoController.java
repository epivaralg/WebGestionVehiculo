package controller;

import ejb.RazonEstado;
import ejb.HistoriaEstado;
import java.util.Collection;
import facade.RazonEstadoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "razonEstadoController")
@ViewScoped
public class RazonEstadoController extends AbstractController<RazonEstado> {

    @Inject
    private EstadoSolicitudController idEstadoSolicitudController;

    // Flags to indicate if child collections are empty
    private boolean isHistoriaEstadoCollectionEmpty;

    public RazonEstadoController() {
        // Inform the Abstract parent controller of the concrete RazonEstado Entity
        super(RazonEstado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idEstadoSolicitudController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsHistoriaEstadoCollectionEmpty();
    }

    public boolean getIsHistoriaEstadoCollectionEmpty() {
        return this.isHistoriaEstadoCollectionEmpty;
    }

    private void setIsHistoriaEstadoCollectionEmpty() {
        RazonEstado selected = this.getSelected();
        if (selected != null) {
            RazonEstadoFacade ejbFacade = (RazonEstadoFacade) this.getFacade();
            this.isHistoriaEstadoCollectionEmpty = ejbFacade.isHistoriaEstadoCollectionEmpty(selected);
        } else {
            this.isHistoriaEstadoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HistoriaEstado entities
     * that are retrieved from RazonEstado and returns the navigation outcome.
     *
     * @return navigation outcome for HistoriaEstado page
     */
    public String navigateHistoriaEstadoCollection() {
        RazonEstado selected = this.getSelected();
        if (selected != null) {
            RazonEstadoFacade ejbFacade = (RazonEstadoFacade) this.getFacade();
            Collection<HistoriaEstado> selectedHistoriaEstadoCollection = ejbFacade.findHistoriaEstadoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HistoriaEstado_items", selectedHistoriaEstadoCollection);
        }
        return "/app/historiaEstado/index";
    }

    /**
     * Sets the "selected" attribute of the EstadoSolicitud controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEstadoSolicitud(ActionEvent event) {
        RazonEstado selected = this.getSelected();
        if (selected != null && idEstadoSolicitudController.getSelected() == null) {
            idEstadoSolicitudController.setSelected(selected.getIdEstadoSolicitud());
        }
    }

}
