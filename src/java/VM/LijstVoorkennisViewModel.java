/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VM;

import DAL.Modulevoorkennis;
import java.util.List;

/**
 *
 * @author Wim
 */
public class LijstVoorkennisViewModel {
    private List<Modulevoorkennis> lstModulevoorkennis;

    public LijstVoorkennisViewModel(List<Modulevoorkennis> lstModulevoorkennis) {
        this.lstModulevoorkennis = lstModulevoorkennis;
    }   

    /**
     * @return the lstModulevoorkennis
     */
    public List<Modulevoorkennis> getLstModulevoorkennis() {
        return lstModulevoorkennis;
    }

    /**
     * @param lstModulevoorkennis the lstModulevoorkennis to set
     */
    public void setLstModulevoorkennis(List<Modulevoorkennis> lstModulevoorkennis) {
        this.lstModulevoorkennis = lstModulevoorkennis;
    }
}
