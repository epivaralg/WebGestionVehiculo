package controller;

import ejb.HistoriaEstado;
import facade.HistoriaEstadoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "historiaEstadoController")
@ViewScoped
public class HistoriaEstadoController extends AbstractController<HistoriaEstado> {

    @Inject
    private RazonEstadoController idRazonEstadoController;
    @Inject
    private SolicitudController idSolicitudController;

    public HistoriaEstadoController() {
        // Inform the Abstract parent controller of the concrete HistoriaEstado Entity
        super(HistoriaEstado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRazonEstadoController.setSelected(null);
        idSolicitudController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the RazonEstado controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRazonEstado(ActionEvent event) {
        HistoriaEstado selected = this.getSelected();
        if (selected != null && idRazonEstadoController.getSelected() == null) {
            idRazonEstadoController.setSelected(selected.getIdRazonEstado());
        }
    }

    /**
     * Sets the "selected" attribute of the Solicitud controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSolicitud(ActionEvent event) {
        HistoriaEstado selected = this.getSelected();
        if (selected != null && idSolicitudController.getSelected() == null) {
            idSolicitudController.setSelected(selected.getIdSolicitud());
        }
    }

}
