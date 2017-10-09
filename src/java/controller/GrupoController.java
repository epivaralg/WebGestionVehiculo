package controller;

import ejb.Grupo;
import ejb.Piloto;
import java.util.Collection;
import facade.GrupoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "grupoController")
@ViewScoped
public class GrupoController extends AbstractController<Grupo> {

    // Flags to indicate if child collections are empty
    private boolean isPilotoCollectionEmpty;

    public GrupoController() {
        // Inform the Abstract parent controller of the concrete Grupo Entity
        super(Grupo.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPilotoCollectionEmpty();
    }

    public boolean getIsPilotoCollectionEmpty() {
        return this.isPilotoCollectionEmpty;
    }

    private void setIsPilotoCollectionEmpty() {
        Grupo selected = this.getSelected();
        if (selected != null) {
            GrupoFacade ejbFacade = (GrupoFacade) this.getFacade();
            this.isPilotoCollectionEmpty = ejbFacade.isPilotoCollectionEmpty(selected);
        } else {
            this.isPilotoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Piloto entities that are
     * retrieved from Grupo and returns the navigation outcome.
     *
     * @return navigation outcome for Piloto page
     */
    public String navigatePilotoCollection() {
        Grupo selected = this.getSelected();
        if (selected != null) {
            GrupoFacade ejbFacade = (GrupoFacade) this.getFacade();
            Collection<Piloto> selectedPilotoCollection = ejbFacade.findPilotoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Piloto_items", selectedPilotoCollection);
        }
        return "/app/piloto/index";
    }

}
