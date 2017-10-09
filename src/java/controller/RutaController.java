package controller;

import ejb.Ruta;
import facade.RutaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "rutaController")
@ViewScoped
public class RutaController extends AbstractController<Ruta> {

    @Inject
    private MunicipioController idMunicipioController;
    @Inject
    private SolicitudController idSolicitudController;

    public RutaController() {
        // Inform the Abstract parent controller of the concrete Ruta Entity
        super(Ruta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idMunicipioController.setSelected(null);
        idSolicitudController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Municipio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdMunicipio(ActionEvent event) {
        Ruta selected = this.getSelected();
        if (selected != null && idMunicipioController.getSelected() == null) {
            idMunicipioController.setSelected(selected.getIdMunicipio());
        }
    }

    /**
     * Sets the "selected" attribute of the Solicitud controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSolicitud(ActionEvent event) {
        Ruta selected = this.getSelected();
        if (selected != null && idSolicitudController.getSelected() == null) {
            idSolicitudController.setSelected(selected.getIdSolicitud());
        }
    }

}
