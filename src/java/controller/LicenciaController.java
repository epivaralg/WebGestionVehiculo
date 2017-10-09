package controller;

import ejb.Licencia;
import facade.LicenciaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "licenciaController")
@ViewScoped
public class LicenciaController extends AbstractController<Licencia> {

    @Inject
    private PilotoController idPilotoController;
    @Inject
    private TipoLicenciaController idTipoLicenciaController;

    public LicenciaController() {
        // Inform the Abstract parent controller of the concrete Licencia Entity
        super(Licencia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPilotoController.setSelected(null);
        idTipoLicenciaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Piloto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPiloto(ActionEvent event) {
        Licencia selected = this.getSelected();
        if (selected != null && idPilotoController.getSelected() == null) {
            idPilotoController.setSelected(selected.getIdPiloto());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoLicencia controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoLicencia(ActionEvent event) {
        Licencia selected = this.getSelected();
        if (selected != null && idTipoLicenciaController.getSelected() == null) {
            idTipoLicenciaController.setSelected(selected.getIdTipoLicencia());
        }
    }

}
