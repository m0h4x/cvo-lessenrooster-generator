/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VM;

import DAL.Module;
import java.util.List;

/**
 *
 * @author Wim
 */
public class LijstModulesViewModel {
    
    private List<Module> modules;

    public LijstModulesViewModel(List<Module> modules) {
        this.modules = modules;
    }        

    /**
     * @return the modules
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * @param modules the modules to set
     */
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }   
}
