package controller;

import ejb.Municipio;
import ejb.Ruta;
import java.util.Collection;
import facade.MunicipioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "municipioController")
@ViewScoped
public class MunicipioController extends AbstractController<Municipio> {

    @Inject
    private DepartamentoController idDepartamentoController;

    // Flags to indicate if child collections are empty
    private boolean isRutaCollectionEmpty;

    public MunicipioController() {
        // Inform the Abstract parent controller of the concrete Municipio Entity
        super(Municipio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idDepartamentoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRutaCollectionEmpty();
    }

    public boolean getIsRutaCollectionEmpty() {
        return this.isRutaCollectionEmpty;
    }

    private void setIsRutaCollectionEmpty() {
        Municipio selected = this.getSelected();
        if (selected != null) {
            MunicipioFacade ejbFacade = (MunicipioFacade) this.getFacade();
            this.isRutaCollectionEmpty = ejbFacade.isRutaCollectionEmpty(selected);
        } else {
            this.isRutaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ruta entities that are
     * retrieved from Municipio and returns the navigation outcome.
     *
     * @return navigation outcome for Ruta page
     */
    public String navigateRutaCollection() {
        Municipio selected = this.getSelected();
        if (selected != null) {
            MunicipioFacade ejbFacade = (MunicipioFacade) this.getFacade();
            Collection<Ruta> selectedRutaCollection = ejbFacade.findRutaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ruta_items", selectedRutaCollection);
        }
        return "/app/ruta/index";
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdDepartamento(ActionEvent event) {
        Municipio selected = this.getSelected();
        if (selected != null && idDepartamentoController.getSelected() == null) {
            idDepartamentoController.setSelected(selected.getIdDepartamento());
        }
    }

}
