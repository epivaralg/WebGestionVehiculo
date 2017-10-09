package controller;

import ejb.Linea;
import ejb.Vehiculo;
import java.util.Collection;
import facade.LineaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "lineaController")
@ViewScoped
public class LineaController extends AbstractController<Linea> {

    @Inject
    private MarcaController idMarcaController;

    // Flags to indicate if child collections are empty
    private boolean isVehiculoCollectionEmpty;

    public LineaController() {
        // Inform the Abstract parent controller of the concrete Linea Entity
        super(Linea.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idMarcaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsVehiculoCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Marca controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdMarca(ActionEvent event) {
        Linea selected = this.getSelected();
        if (selected != null && idMarcaController.getSelected() == null) {
            idMarcaController.setSelected(selected.getIdMarca());
        }
    }

    public boolean getIsVehiculoCollectionEmpty() {
        return this.isVehiculoCollectionEmpty;
    }

    private void setIsVehiculoCollectionEmpty() {
        Linea selected = this.getSelected();
        if (selected != null) {
            LineaFacade ejbFacade = (LineaFacade) this.getFacade();
            this.isVehiculoCollectionEmpty = ejbFacade.isVehiculoCollectionEmpty(selected);
        } else {
            this.isVehiculoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Vehiculo entities that
     * are retrieved from Linea and returns the navigation outcome.
     *
     * @return navigation outcome for Vehiculo page
     */
    public String navigateVehiculoCollection() {
        Linea selected = this.getSelected();
        if (selected != null) {
            LineaFacade ejbFacade = (LineaFacade) this.getFacade();
            Collection<Vehiculo> selectedVehiculoCollection = ejbFacade.findVehiculoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vehiculo_items", selectedVehiculoCollection);
        }
        return "/app/vehiculo/index";
    }

}
