package controller;

import ejb.Unidad;
import ejb.Usuario;
import java.util.Collection;
import facade.UnidadFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "unidadController")
@ViewScoped
public class UnidadController extends AbstractController<Unidad> {

    @Inject
    private OficinaController idOficinaController;

    // Flags to indicate if child collections are empty
    private boolean isUsuarioCollectionEmpty;

    public UnidadController() {
        // Inform the Abstract parent controller of the concrete Unidad Entity
        super(Unidad.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idOficinaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsuarioCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Oficina controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdOficina(ActionEvent event) {
        Unidad selected = this.getSelected();
        if (selected != null && idOficinaController.getSelected() == null) {
            idOficinaController.setSelected(selected.getIdOficina());
        }
    }

    public boolean getIsUsuarioCollectionEmpty() {
        return this.isUsuarioCollectionEmpty;
    }

    private void setIsUsuarioCollectionEmpty() {
        Unidad selected = this.getSelected();
        if (selected != null) {
            UnidadFacade ejbFacade = (UnidadFacade) this.getFacade();
            this.isUsuarioCollectionEmpty = ejbFacade.isUsuarioCollectionEmpty(selected);
        } else {
            this.isUsuarioCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Unidad and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioCollection() {
        Unidad selected = this.getSelected();
        if (selected != null) {
            UnidadFacade ejbFacade = (UnidadFacade) this.getFacade();
            Collection<Usuario> selectedUsuarioCollection = ejbFacade.findUsuarioCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioCollection);
        }
        return "/app/usuario/index";
    }

}
