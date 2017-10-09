package controller;

import ejb.Marca;
import ejb.Linea;
import java.util.Collection;
import facade.MarcaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "marcaController")
@ViewScoped
public class MarcaController extends AbstractController<Marca> {

    // Flags to indicate if child collections are empty
    private boolean isLineaCollectionEmpty;

    public MarcaController() {
        // Inform the Abstract parent controller of the concrete Marca Entity
        super(Marca.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsLineaCollectionEmpty();
    }

    public boolean getIsLineaCollectionEmpty() {
        return this.isLineaCollectionEmpty;
    }

    private void setIsLineaCollectionEmpty() {
        Marca selected = this.getSelected();
        if (selected != null) {
            MarcaFacade ejbFacade = (MarcaFacade) this.getFacade();
            this.isLineaCollectionEmpty = ejbFacade.isLineaCollectionEmpty(selected);
        } else {
            this.isLineaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Linea entities that are
     * retrieved from Marca and returns the navigation outcome.
     *
     * @return navigation outcome for Linea page
     */
    public String navigateLineaCollection() {
        Marca selected = this.getSelected();
        if (selected != null) {
            MarcaFacade ejbFacade = (MarcaFacade) this.getFacade();
            Collection<Linea> selectedLineaCollection = ejbFacade.findLineaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Linea_items", selectedLineaCollection);
        }
        return "/app/linea/index";
    }

}
