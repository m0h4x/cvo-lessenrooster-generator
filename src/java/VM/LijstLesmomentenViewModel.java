/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VM;

import DAL.Lesmoment;
import java.util.List;

/**
 *
 * @author Wim
 */
public class LijstLesmomentenViewModel {
    
    private List<Lesmoment> lesmomenten;

    public LijstLesmomentenViewModel(List<Lesmoment> lesmomenten) {
        this.lesmomenten = lesmomenten;
    }

    /**
     * @return the lesmomenten
     */
    public List<Lesmoment> getLesmomenten() {
        return lesmomenten;
    }

    /**
     * @param lesmomenten the lesmomenten to set
     */
    public void setLesmomenten(List<Lesmoment> lesmomenten) {
        this.lesmomenten = lesmomenten;
    }
}
