/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VM;

import DAL.Classificatie;
import java.util.List;

/**
 *
 * @author Wim
 */
public class LijstClassificatieViewModel {
    private List<Classificatie> classificatie;

    public LijstClassificatieViewModel(List<Classificatie> classificatie) {
        this.classificatie = classificatie;
    }

    /**
     * @return the classificatie
     */
    public List<Classificatie> getClassificatie() {
        return classificatie;
    }

    /**
     * @param classificatie the classificatie to set
     */
    public void setClassificatie(List<Classificatie> classificatie) {
        this.classificatie = classificatie;
    }
    
}
