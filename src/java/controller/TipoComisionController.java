package controller;

import ejb.TipoComision;
import ejb.Comision;
import java.util.Collection;
import facade.TipoComisionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoComisionController")
@ViewScoped
public class TipoComisionController extends AbstractController<TipoComision> {

    // Flags to indicate if child collections are empty
    private boolean isComisionCollectionEmpty;

    public TipoComisionController() {
        // Inform the Abstract parent controller of the concrete TipoComision Entity
        super(TipoComision.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsComisionCollectionEmpty();
    }

    public boolean getIsComisionCollectionEmpty() {
        return this.isComisionCollectionEmpty;
    }

    private void setIsComisionCollectionEmpty() {
        TipoComision selected = this.getSelected();
        if (selected != null) {
            TipoComisionFacade ejbFacade = (TipoComisionFacade) this.getFacade();
            this.isComisionCollectionEmpty = ejbFacade.isComisionCollectionEmpty(selected);
        } else {
            this.isComisionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Comision entities that
     * are retrieved from TipoComision and returns the navigation outcome.
     *
     * @return navigation outcome for Comision page
     */
    public String navigateComisionCollection() {
        TipoComision selected = this.getSelected();
        if (selected != null) {
            TipoComisionFacade ejbFacade = (TipoComisionFacade) this.getFacade();
            Collection<Comision> selectedComisionCollection = ejbFacade.findComisionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Comision_items", selectedComisionCollection);
        }
        return "/app/comision/index";
    }

}
