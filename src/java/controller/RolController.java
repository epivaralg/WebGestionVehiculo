package controller;

import ejb.Rol;
import ejb.Usuario;
import java.util.Collection;
import facade.RolFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> {

    // Flags to indicate if child collections are empty
    private boolean isUsuarioCollectionEmpty;

    public RolController() {
        // Inform the Abstract parent controller of the concrete Rol Entity
        super(Rol.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsuarioCollectionEmpty();
    }

    public boolean getIsUsuarioCollectionEmpty() {
        return this.isUsuarioCollectionEmpty;
    }

    private void setIsUsuarioCollectionEmpty() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            this.isUsuarioCollectionEmpty = ejbFacade.isUsuarioCollectionEmpty(selected);
        } else {
            this.isUsuarioCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Rol and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioCollection() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            Collection<Usuario> selectedUsuarioCollection = ejbFacade.findUsuarioCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioCollection);
        }
        return "/app/usuario/index";
    }

}
