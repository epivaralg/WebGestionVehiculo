package controller;

import ejb.Comision;
import ejb.Solicitud;
import java.util.Collection;
import facade.ComisionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "comisionController")
@ViewScoped
public class ComisionController extends AbstractController<Comision> {

    @Inject
    private PilotoController idPilotoController;
    @Inject
    private TipoComisionController idTipoComisionController;
    @Inject
    private VehiculoController placaController;

    // Flags to indicate if child collections are empty
    private boolean isSolicitudCollectionEmpty;

    public ComisionController() {
        // Inform the Abstract parent controller of the concrete Comision Entity
        super(Comision.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPilotoController.setSelected(null);
        idTipoComisionController.setSelected(null);
        placaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsSolicitudCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Piloto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPiloto(ActionEvent event) {
        Comision selected = this.getSelected();
        if (selected != null && idPilotoController.getSelected() == null) {
            idPilotoController.setSelected(selected.getIdPiloto());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoComision controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoComision(ActionEvent event) {
        Comision selected = this.getSelected();
        if (selected != null && idTipoComisionController.getSelected() == null) {
            idTipoComisionController.setSelected(selected.getIdTipoComision());
        }
    }

    /**
     * Sets the "selected" attribute of the Vehiculo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePlaca(ActionEvent event) {
        Comision selected = this.getSelected();
        if (selected != null && placaController.getSelected() == null) {
            placaController.setSelected(selected.getPlaca());
        }
    }

    public boolean getIsSolicitudCollectionEmpty() {
        return this.isSolicitudCollectionEmpty;
    }

    private void setIsSolicitudCollectionEmpty() {
        Comision selected = this.getSelected();
        if (selected != null) {
            ComisionFacade ejbFacade = (ComisionFacade) this.getFacade();
            this.isSolicitudCollectionEmpty = ejbFacade.isSolicitudCollectionEmpty(selected);
        } else {
            this.isSolicitudCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from Comision and returns the navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection() {
        Comision selected = this.getSelected();
        if (selected != null) {
            ComisionFacade ejbFacade = (ComisionFacade) this.getFacade();
            Collection<Solicitud> selectedSolicitudCollection = ejbFacade.findSolicitudCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", selectedSolicitudCollection);
        }
        return "/app/solicitud/index";
    }

}
