package controller;

import ejb.Piloto;
import ejb.Comision;
import ejb.Licencia;
import java.util.Collection;
import facade.PilotoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pilotoController")
@ViewScoped
public class PilotoController extends AbstractController<Piloto> {

    @Inject
    private EstadoPilotoController idEstadoPilotoController;
    @Inject
    private GrupoController idGrupoController;

    // Flags to indicate if child collections are empty
    private boolean isComisionCollectionEmpty;
    private boolean isLicenciaCollectionEmpty;

    public PilotoController() {
        // Inform the Abstract parent controller of the concrete Piloto Entity
        super(Piloto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idEstadoPilotoController.setSelected(null);
        idGrupoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsComisionCollectionEmpty();
        this.setIsLicenciaCollectionEmpty();
    }

    public boolean getIsComisionCollectionEmpty() {
        return this.isComisionCollectionEmpty;
    }

    private void setIsComisionCollectionEmpty() {
        Piloto selected = this.getSelected();
        if (selected != null) {
            PilotoFacade ejbFacade = (PilotoFacade) this.getFacade();
            this.isComisionCollectionEmpty = ejbFacade.isComisionCollectionEmpty(selected);
        } else {
            this.isComisionCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Comision entities that
     * are retrieved from Piloto and returns the navigation outcome.
     *
     * @return navigation outcome for Comision page
     */
    public String navigateComisionCollection() {
        Piloto selected = this.getSelected();
        if (selected != null) {
            PilotoFacade ejbFacade = (PilotoFacade) this.getFacade();
            Collection<Comision> selectedComisionCollection = ejbFacade.findComisionCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Comision_items", selectedComisionCollection);
        }
        return "/app/comision/index";
    }

    /**
     * Sets the "selected" attribute of the EstadoPiloto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEstadoPiloto(ActionEvent event) {
        Piloto selected = this.getSelected();
        if (selected != null && idEstadoPilotoController.getSelected() == null) {
            idEstadoPilotoController.setSelected(selected.getIdEstadoPiloto());
        }
    }

    /**
     * Sets the "selected" attribute of the Grupo controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGrupo(ActionEvent event) {
        Piloto selected = this.getSelected();
        if (selected != null && idGrupoController.getSelected() == null) {
            idGrupoController.setSelected(selected.getIdGrupo());
        }
    }

    public boolean getIsLicenciaCollectionEmpty() {
        return this.isLicenciaCollectionEmpty;
    }

    private void setIsLicenciaCollectionEmpty() {
        Piloto selected = this.getSelected();
        if (selected != null) {
            PilotoFacade ejbFacade = (PilotoFacade) this.getFacade();
            this.isLicenciaCollectionEmpty = ejbFacade.isLicenciaCollectionEmpty(selected);
        } else {
            this.isLicenciaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Licencia entities that
     * are retrieved from Piloto and returns the navigation outcome.
     *
     * @return navigation outcome for Licencia page
     */
    public String navigateLicenciaCollection() {
        Piloto selected = this.getSelected();
        if (selected != null) {
            PilotoFacade ejbFacade = (PilotoFacade) this.getFacade();
            Collection<Licencia> selectedLicenciaCollection = ejbFacade.findLicenciaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Licencia_items", selectedLicenciaCollection);
        }
        return "/app/licencia/index";
    }

}
