package DAL;
// Generated 29-Dec-2014 19:31:07 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Lesmoment generated by hbm2java
 */
public class Lesmoment  implements java.io.Serializable {


     private Integer id;
     private Module module;
     private Date datum;
     private String beginuur;
     private String einduur;
     private String lokaal;

    public Lesmoment() {
    }

    public Lesmoment(Module module, Date datum, String beginuur, String einduur, String lokaal) {
       this.module = module;
       this.datum = datum;
       this.beginuur = beginuur;
       this.einduur = einduur;
       this.lokaal = lokaal;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Module getModule() {
        return this.module;
    }
    
    public void setModule(Module module) {
        this.module = module;
    }
    public Date getDatum() {
        return this.datum;
    }
    
    public void setDatum(Date datum) {
        this.datum = datum;
    }
    public String getBeginuur() {
        return this.beginuur;
    }
    
    public void setBeginuur(String beginuur) {
        this.beginuur = beginuur;
    }
    public String getEinduur() {
        return this.einduur;
    }
    
    public void setEinduur(String einduur) {
        this.einduur = einduur;
    }
    public String getLokaal() {
        return this.lokaal;
    }
    
    public void setLokaal(String lokaal) {
        this.lokaal = lokaal;
    }




}


