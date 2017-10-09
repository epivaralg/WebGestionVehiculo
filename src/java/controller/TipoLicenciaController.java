package controller;

import ejb.TipoLicencia;
import ejb.Licencia;
import java.util.Collection;
import facade.TipoLicenciaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoLicenciaController")
@ViewScoped
public class TipoLicenciaController extends AbstractController<TipoLicencia> {

    // Flags to indicate if child collections are empty
    private boolean isLicenciaCollectionEmpty;

    public TipoLicenciaController() {
        // Inform the Abstract parent controller of the concrete TipoLicencia Entity
        super(TipoLicencia.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsLicenciaCollectionEmpty();
    }

    public boolean getIsLicenciaCollectionEmpty() {
        return this.isLicenciaCollectionEmpty;
    }

    private void setIsLicenciaCollectionEmpty() {
        TipoLicencia selected = this.getSelected();
        if (selected != null) {
            TipoLicenciaFacade ejbFacade = (TipoLicenciaFacade) this.getFacade();
            this.isLicenciaCollectionEmpty = ejbFacade.isLicenciaCollectionEmpty(selected);
        } else {
            this.isLicenciaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Licencia entities that
     * are retrieved from TipoLicencia and returns the navigation outcome.
     *
     * @return navigation outcome for Licencia page
     */
    public String navigateLicenciaCollection() {
        TipoLicencia selected = this.getSelected();
        if (selected != null) {
            TipoLicenciaFacade ejbFacade = (TipoLicenciaFacade) this.getFacade();
            Collection<Licencia> selectedLicenciaCollection = ejbFacade.findLicenciaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Licencia_items", selectedLicenciaCollection);
        }
        return "/app/licencia/index";
    }

}
