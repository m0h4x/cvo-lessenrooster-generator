/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Lesmoment;
import DAL.Module;
import SL.ModuleServices;
import VM.LijstLesmomentenViewModel;
import VM.LijstModulesViewModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wim
 */
public class GenereerRoosterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Gekozen module ID's ophalen
        List<String> lstGegekozenModuleIds = new ArrayList<String>();
        if (request.getParameterValues("Modules") != null) {
            lstGegekozenModuleIds = Arrays.asList(request.getParameterValues("Modules"));
        }

        //ID's omzetten naar modules
        List<Module> lstGekozenModules = new ArrayList<Module>();
        for (String s : lstGegekozenModuleIds) {
            Module m = ModuleServices.getModule(Integer.parseInt(s));
            lstGekozenModules.add(m);
        }

        //Eerder werd er gewerkt met unieke module namen, zodat de gebruiker zijn selectie kon maken.
        //Nu is het weer tijd om met alle modules te gaan werken. (vb. programmeren 1 kan verschillende keren voorkomen)
        List<Module> lstGekozenModulesAll = new ArrayList<Module>();
        for (Module m : lstGekozenModules) {
            List<Module> lstSoortgelijkeModules = new ArrayList<Module>();
            lstSoortgelijkeModules = ModuleServices.GetAllModulesMetClassificatie(m.getClassificatie());

            for (Module m2 : lstSoortgelijkeModules) {
                lstGekozenModulesAll.add(m2);
            }
        }

        //Gekozen weekdagen ophalen
        List<String> lstGekozenWeekdagen = new ArrayList<String>();
        if (request.getParameterValues("chkWeekdagen") != null) {
            lstGekozenWeekdagen = Arrays.asList(request.getParameterValues("chkWeekdagen"));
        }

        List<Integer> gekozenWeekdagen = new ArrayList<>();

        if (lstGekozenWeekdagen.contains("Maandag")) {
            gekozenWeekdagen.add(2);
        }

        if (lstGekozenWeekdagen.contains("Dinsdag")) {
            gekozenWeekdagen.add(3);
        }

        if (lstGekozenWeekdagen.contains("Woensdag")) {
            gekozenWeekdagen.add(4);
        }

        if (lstGekozenWeekdagen.contains("Donderdag")) {
            gekozenWeekdagen.add(5);
        }

        if (lstGekozenWeekdagen.contains("Vrijdag")) {
            gekozenWeekdagen.add(6);
        }

        //Per gekozen module kijken of deze nog in aanmerking komt door te vergelijken met de gekozen dagen  
        List<Module> lstModulesOkVoorGekozenDagen = new ArrayList<Module>();
        List<Module> lstModulesNietOkVoorGekozenDagen = new ArrayList<Module>();

        for (Module m : lstGekozenModulesAll) {
            Set<Lesmoment> lesmomenten = m.getLesmoments();

            boolean magWordenToegevoegd = true;

            for (Lesmoment l : lesmomenten) {

                Date lesmomentDatum = l.getDatum();
                Calendar c = Calendar.getInstance();
                c.setTime(lesmomentDatum);
                int lesmomentDag = c.get(Calendar.DAY_OF_WEEK);

                if (!gekozenWeekdagen.contains(lesmomentDag)) {
                    magWordenToegevoegd = false;
                }
            }

            if (magWordenToegevoegd == true) {
                lstModulesOkVoorGekozenDagen.add(m);
            } else {
                lstModulesNietOkVoorGekozenDagen.add(m);
            }
        }

        //Vergelijken van dagen en final list samenstellen
        List<Module> lstModulesFinal = new ArrayList<Module>();
        List<Lesmoment> lstLesmomenten = new ArrayList<Lesmoment>();

        int teller = 0;
        for (Module m : lstModulesOkVoorGekozenDagen) {
            //Eerste module wordt sowieso toegevoegd
            if (teller == 0) {
                lstModulesFinal.add(m);
            }

            //Zorgen dat er geen 2 dezelfde modules worden toegevoegd
            boolean soortgelijkeModuleAlToegevoegd = false;
            for (Module m2 : lstModulesFinal) {
                if (m2.getClassificatie() == m.getClassificatie()) {
                    soortgelijkeModuleAlToegevoegd = true;
                }
            }

            if (soortgelijkeModuleAlToegevoegd == false) {
                //De module mag worden toegevoegd, maar we moeten wel nog nakijken of er geen 
                //tijdstip conflicten zijn met de andere modules die reeds werden toegevoegd
                boolean magWordenToegevoegd = true;

                Set<Lesmoment> lstModuleLesmomenten = m.getLesmoments();

                for (Lesmoment l : lstModuleLesmomenten) {
                    //voor elk moment nakijken in lstModulesFinal of datum nog vrij is

                    for (Module m3 : lstModulesFinal) {
                        Set<Lesmoment> lstModuleLesmomentenCompare = m3.getLesmoments();

                        for (Lesmoment l2 : lstModuleLesmomentenCompare) {
                            if (l2.getDatum().compareTo(l.getDatum()) == 0) {
                                magWordenToegevoegd = false;
                            }
                        }
                    }
                }

                if (magWordenToegevoegd == true) {
                    lstModulesFinal.add(m);
                }
            }
            teller++;
        }

        //Alle lesmomenten moeten ook in een viewmodel komen om de rooster weer te geven
        List<Lesmoment> lstLesmomentenFinal = new ArrayList<Lesmoment>();
        for (Module m : lstModulesFinal) {
            lstLesmomentenFinal.addAll(m.getLesmoments());
        }
        
        //Sorteren op datum
        Collections.sort(lstLesmomentenFinal, (Lesmoment l1, Lesmoment l2) -> l1.getDatum().compareTo(l2.getDatum()));

        //Viewmodels aanmaken voor modules die in aamerking komen
        LijstModulesViewModel vmLstModulesFinal = new LijstModulesViewModel(lstModulesFinal);
        LijstLesmomentenViewModel vmLesmomentenFinal = new LijstLesmomentenViewModel(lstLesmomentenFinal);

        HttpSession session = request.getSession();
        
        //De nodige informatie meesturen met session
        session.setAttribute("vmLstModulesFinal", vmLstModulesFinal);
        session.setAttribute("vmLesmomentenFinal", vmLesmomentenFinal);

        //Aanroepen van de jsp pagina's waar de modules en lessenrooster op worden getoond
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("Resultaat.jsp");
        dispatcher.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
