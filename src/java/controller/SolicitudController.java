package controller;

import ejb.Solicitud;
import ejb.HistoriaEstado;
import ejb.Ruta;
import java.util.Collection;
import facade.SolicitudFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "solicitudController")
@ViewScoped
public class SolicitudController extends AbstractController<Solicitud> {

    @Inject
    private UsuarioController idUsuarioAprobadorController;
    @Inject
    private ComisionController idComisionController;
    @Inject
    private UsuarioController idUsuarioGestorController;
    @Inject
    private TipoSolicitudController idTipoComisionController;
    @Inject
    private TipoUsoVehiculoController idTipoUsoVehiculoController;
    @Inject
    private UsuarioController idUsuarioController;

    // Flags to indicate if child collections are empty
    private boolean isHistoriaEstadoCollectionEmpty;
    private boolean isRutaCollectionEmpty;

    public SolicitudController() {
        // Inform the Abstract parent controller of the concrete Solicitud Entity
        super(Solicitud.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idUsuarioAprobadorController.setSelected(null);
        idComisionController.setSelected(null);
        idUsuarioGestorController.setSelected(null);
        idTipoComisionController.setSelected(null);
        idTipoUsoVehiculoController.setSelected(null);
        idUsuarioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsHistoriaEstadoCollectionEmpty();
        this.setIsRutaCollectionEmpty();
    }

    public boolean getIsHistoriaEstadoCollectionEmpty() {
        return this.isHistoriaEstadoCollectionEmpty;
    }

    private void setIsHistoriaEstadoCollectionEmpty() {
        Solicitud selected = this.getSelected();
        if (selected != null) {
            SolicitudFacade ejbFacade = (SolicitudFacade) this.getFacade();
            this.isHistoriaEstadoCollectionEmpty = ejbFacade.isHistoriaEstadoCollectionEmpty(selected);
        } else {
            this.isHistoriaEstadoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HistoriaEstado entities
     * that are retrieved from Solicitud and returns the navigation outcome.
     *
     * @return navigation outcome for HistoriaEstado page
     */
    public String navigateHistoriaEstadoCollection() {
        Solicitud selected = this.getSelected();
        if (selected != null) {
            SolicitudFacade ejbFacade = (SolicitudFacade) this.getFacade();
            Collection<HistoriaEstado> selectedHistoriaEstadoCollection = ejbFacade.findHistoriaEstadoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HistoriaEstado_items", selectedHistoriaEstadoCollection);
        }
        return "/app/historiaEstado/index";
    }

    public boolean getIsRutaCollectionEmpty() {
        return this.isRutaCollectionEmpty;
    }

    private void setIsRutaCollectionEmpty() {
        Solicitud selected = this.getSelected();
        if (selected != null) {
            SolicitudFacade ejbFacade = (SolicitudFacade) this.getFacade();
            this.isRutaCollectionEmpty = ejbFacade.isRutaCollectionEmpty(selected);
        } else {
            this.isRutaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ruta entities that are
     * retrieved from Solicitud and returns the navigation outcome.
     *
     * @return navigation outcome for Ruta page
     */
    public String navigateRutaCollection() {
        Solicitud selected = this.getSelected();
        if (selected != null) {
            SolicitudFacade ejbFacade = (SolicitudFacade) this.getFacade();
            Collection<Ruta> selectedRutaCollection = ejbFacade.findRutaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ruta_items", selectedRutaCollection);
        }
        return "/app/ruta/index";
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuarioAprobador(ActionEvent event) {
        Solicitud selected = this.getSelected();
        if (selected != null && idUsuarioAprobadorController.getSelected() == null) {
            idUsuarioAprobadorController.setSelected(selected.getIdUsuarioAprobador());
        }
    }

    /**
     * Sets the "selected" attribute of the Comision controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdComision(ActionEvent event) {
        Solicitud selected = this.getSelected();
        if (selected != null && idComisionController.getSelected() == null) {
            idComisionController.setSelected(selected.getIdComision());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuarioGestor(ActionEvent event) {
        Solicitud selected = this.getSelected();
        if (selected != null && idUsuarioGestorController.getSelected() == null) {
            idUsuarioGestorController.setSelected(selected.getIdUsuarioGestor());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoSolicitud controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoComision(ActionEvent event) {
        Solicitud selected = this.getSelected();
        if (selected != null && idTipoComisionController.getSelected() == null) {
            idTipoComisionController.setSelected(selected.getIdTipoComision());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoUsoVehiculo controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoUsoVehiculo(ActionEvent event) {
        Solicitud selected = this.getSelected();
        if (selected != null && idTipoUsoVehiculoController.getSelected() == null) {
            idTipoUsoVehiculoController.setSelected(selected.getIdTipoUsoVehiculo());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuario(ActionEvent event) {
        Solicitud selected = this.getSelected();
        if (selected != null && idUsuarioController.getSelected() == null) {
            idUsuarioController.setSelected(selected.getIdUsuario());
        }
    }

}
