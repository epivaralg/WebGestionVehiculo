package controller;

import ejb.Usuario;
import ejb.Bitacora;
import ejb.Solicitud;
import java.util.Collection;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private RolController idRolController;
    @Inject
    private UnidadController idUnidadController;

    // Flags to indicate if child collections are empty
    private boolean isBitacoraCollectionEmpty;
    private boolean isSolicitudCollectionEmpty;
    private boolean isSolicitudCollection1Empty;
    private boolean isSolicitudCollection2Empty;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRolController.setSelected(null);
        idUnidadController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsBitacoraCollectionEmpty();
        this.setIsSolicitudCollectionEmpty();
        this.setIsSolicitudCollection1Empty();
        this.setIsSolicitudCollection2Empty();
    }

    public boolean getIsBitacoraCollectionEmpty() {
        return this.isBitacoraCollectionEmpty;
    }

    private void setIsBitacoraCollectionEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isBitacoraCollectionEmpty = ejbFacade.isBitacoraCollectionEmpty(selected);
        } else {
            this.isBitacoraCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bitacora entities that
     * are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Bitacora page
     */
    public String navigateBitacoraCollection() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            Collection<Bitacora> selectedBitacoraCollection = ejbFacade.findBitacoraCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bitacora_items", selectedBitacoraCollection);
        }
        return "/app/bitacora/index";
    }

    public boolean getIsSolicitudCollectionEmpty() {
        return this.isSolicitudCollectionEmpty;
    }

    private void setIsSolicitudCollectionEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isSolicitudCollectionEmpty = ejbFacade.isSolicitudCollectionEmpty(selected);
        } else {
            this.isSolicitudCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            Collection<Solicitud> selectedSolicitudCollection = ejbFacade.findSolicitudCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", selectedSolicitudCollection);
        }
        return "/app/solicitud/index";
    }

    public boolean getIsSolicitudCollection1Empty() {
        return this.isSolicitudCollection1Empty;
    }

    private void setIsSolicitudCollection1Empty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isSolicitudCollection1Empty = ejbFacade.isSolicitudCollection1Empty(selected);
        } else {
            this.isSolicitudCollection1Empty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection1() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            Collection<Solicitud> selectedSolicitudCollection1 = ejbFacade.findSolicitudCollection1(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", selectedSolicitudCollection1);
        }
        return "/app/solicitud/index";
    }

    public boolean getIsSolicitudCollection2Empty() {
        return this.isSolicitudCollection2Empty;
    }

    private void setIsSolicitudCollection2Empty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isSolicitudCollection2Empty = ejbFacade.isSolicitudCollection2Empty(selected);
        } else {
            this.isSolicitudCollection2Empty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Solicitud entities that
     * are retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Solicitud page
     */
    public String navigateSolicitudCollection2() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            Collection<Solicitud> selectedSolicitudCollection2 = ejbFacade.findSolicitudCollection2(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitud_items", selectedSolicitudCollection2);
        }
        return "/app/solicitud/index";
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRol(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && idRolController.getSelected() == null) {
            idRolController.setSelected(selected.getIdRol());
        }
    }

    /**
     * Sets the "selected" attribute of the Unidad controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUnidad(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && idUnidadController.getSelected() == null) {
            idUnidadController.setSelected(selected.getIdUnidad());
        }
    }

}
