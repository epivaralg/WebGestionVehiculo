package controller;

import ejb.Bitacora;
import facade.BitacoraFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "bitacoraController")
@ViewScoped
public class BitacoraController extends AbstractController<Bitacora> {

    @Inject
    private TipoBitacoraController idTipoBitacoraController;
    @Inject
    private UsuarioController idUsuarioController;

    public BitacoraController() {
        // Inform the Abstract parent controller of the concrete Bitacora Entity
        super(Bitacora.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idTipoBitacoraController.setSelected(null);
        idUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoBitacora controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoBitacora(ActionEvent event) {
        Bitacora selected = this.getSelected();
        if (selected != null && idTipoBitacoraController.getSelected() == null) {
            idTipoBitacoraController.setSelected(selected.getIdTipoBitacora());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuario(ActionEvent event) {
        Bitacora selected = this.getSelected();
        if (selected != null && idUsuarioController.getSelected() == null) {
            idUsuarioController.setSelected(selected.getIdUsuario());
        }
    }

}
