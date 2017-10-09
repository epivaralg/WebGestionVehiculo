package controller;

import ejb.Oficina;
import ejb.Unidad;
import java.util.Collection;
import facade.OficinaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "oficinaController")
@ViewScoped
public class OficinaController extends AbstractController<Oficina> {

    @Inject
    private DepartamentoController idDepartamentoController;

    // Flags to indicate if child collections are empty
    private boolean isUnidadCollectionEmpty;

    public OficinaController() {
        // Inform the Abstract parent controller of the concrete Oficina Entity
        super(Oficina.class);
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
        this.setIsUnidadCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdDepartamento(ActionEvent event) {
        Oficina selected = this.getSelected();
        if (selected != null && idDepartamentoController.getSelected() == null) {
            idDepartamentoController.setSelected(selected.getIdDepartamento());
        }
    }

    public boolean getIsUnidadCollectionEmpty() {
        return this.isUnidadCollectionEmpty;
    }

    private void setIsUnidadCollectionEmpty() {
        Oficina selected = this.getSelected();
        if (selected != null) {
            OficinaFacade ejbFacade = (OficinaFacade) this.getFacade();
            this.isUnidadCollectionEmpty = ejbFacade.isUnidadCollectionEmpty(selected);
        } else {
            this.isUnidadCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Unidad entities that are
     * retrieved from Oficina and returns the navigation outcome.
     *
     * @return navigation outcome for Unidad page
     */
    public String navigateUnidadCollection() {
        Oficina selected = this.getSelected();
        if (selected != null) {
            OficinaFacade ejbFacade = (OficinaFacade) this.getFacade();
            Collection<Unidad> selectedUnidadCollection = ejbFacade.findUnidadCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Unidad_items", selectedUnidadCollection);
        }
        return "/app/unidad/index";
    }

}
