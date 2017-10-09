package controller;

import ejb.TipoBitacora;
import ejb.Bitacora;
import java.util.Collection;
import facade.TipoBitacoraFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoBitacoraController")
@ViewScoped
public class TipoBitacoraController extends AbstractController<TipoBitacora> {

    // Flags to indicate if child collections are empty
    private boolean isBitacoraCollectionEmpty;

    public TipoBitacoraController() {
        // Inform the Abstract parent controller of the concrete TipoBitacora Entity
        super(TipoBitacora.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsBitacoraCollectionEmpty();
    }

    public boolean getIsBitacoraCollectionEmpty() {
        return this.isBitacoraCollectionEmpty;
    }

    private void setIsBitacoraCollectionEmpty() {
        TipoBitacora selected = this.getSelected();
        if (selected != null) {
            TipoBitacoraFacade ejbFacade = (TipoBitacoraFacade) this.getFacade();
            this.isBitacoraCollectionEmpty = ejbFacade.isBitacoraCollectionEmpty(selected);
        } else {
            this.isBitacoraCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bitacora entities that
     * are retrieved from TipoBitacora and returns the navigation outcome.
     *
     * @return navigation outcome for Bitacora page
     */
    public String navigateBitacoraCollection() {
        TipoBitacora selected = this.getSelected();
        if (selected != null) {
            TipoBitacoraFacade ejbFacade = (TipoBitacoraFacade) this.getFacade();
            Collection<Bitacora> selectedBitacoraCollection = ejbFacade.findBitacoraCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bitacora_items", selectedBitacoraCollection);
        }
        return "/app/bitacora/index";
    }

}
