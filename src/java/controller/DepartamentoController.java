package controller;

import ejb.Departamento;
import ejb.Oficina;
import ejb.Municipio;
import java.util.Collection;
import facade.DepartamentoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "departamentoController")
@ViewScoped
public class DepartamentoController extends AbstractController<Departamento> {

    // Flags to indicate if child collections are empty
    private boolean isOficinaCollectionEmpty;
    private boolean isMunicipioCollectionEmpty;

    public DepartamentoController() {
        // Inform the Abstract parent controller of the concrete Departamento Entity
        super(Departamento.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsOficinaCollectionEmpty();
        this.setIsMunicipioCollectionEmpty();
    }

    public boolean getIsOficinaCollectionEmpty() {
        return this.isOficinaCollectionEmpty;
    }

    private void setIsOficinaCollectionEmpty() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            this.isOficinaCollectionEmpty = ejbFacade.isOficinaCollectionEmpty(selected);
        } else {
            this.isOficinaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Oficina entities that are
     * retrieved from Departamento and returns the navigation outcome.
     *
     * @return navigation outcome for Oficina page
     */
    public String navigateOficinaCollection() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            Collection<Oficina> selectedOficinaCollection = ejbFacade.findOficinaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Oficina_items", selectedOficinaCollection);
        }
        return "/app/oficina/index";
    }

    public boolean getIsMunicipioCollectionEmpty() {
        return this.isMunicipioCollectionEmpty;
    }

    private void setIsMunicipioCollectionEmpty() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            this.isMunicipioCollectionEmpty = ejbFacade.isMunicipioCollectionEmpty(selected);
        } else {
            this.isMunicipioCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Municipio entities that
     * are retrieved from Departamento and returns the navigation outcome.
     *
     * @return navigation outcome for Municipio page
     */
    public String navigateMunicipioCollection() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            Collection<Municipio> selectedMunicipioCollection = ejbFacade.findMunicipioCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Municipio_items", selectedMunicipioCollection);
        }
        return "/app/municipio/index";
    }

}
