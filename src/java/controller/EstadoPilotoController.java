package controller;

import ejb.EstadoPiloto;
import ejb.Piloto;
import java.util.Collection;
import facade.EstadoPilotoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "estadoPilotoController")
@ViewScoped
public class EstadoPilotoController extends AbstractController<EstadoPiloto> {

    // Flags to indicate if child collections are empty
    private boolean isPilotoCollectionEmpty;

    public EstadoPilotoController() {
        // Inform the Abstract parent controller of the concrete EstadoPiloto Entity
        super(EstadoPiloto.class);
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
        EstadoPiloto selected = this.getSelected();
        if (selected != null) {
            EstadoPilotoFacade ejbFacade = (EstadoPilotoFacade) this.getFacade();
            this.isPilotoCollectionEmpty = ejbFacade.isPilotoCollectionEmpty(selected);
        } else {
            this.isPilotoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Piloto entities that are
     * retrieved from EstadoPiloto and returns the navigation outcome.
     *
     * @return navigation outcome for Piloto page
     */
    public String navigatePilotoCollection() {
        EstadoPiloto selected = this.getSelected();
        if (selected != null) {
            EstadoPilotoFacade ejbFacade = (EstadoPilotoFacade) this.getFacade();
            Collection<Piloto> selectedPilotoCollection = ejbFacade.findPilotoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Piloto_items", selectedPilotoCollection);
        }
        return "/app/piloto/index";
    }

}
