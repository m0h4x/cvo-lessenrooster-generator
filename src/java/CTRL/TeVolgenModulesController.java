/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Module;
import DAL.Modulevoorkennis;
import SL.ModuleServices;
import VM.LijstModulesViewModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class TeVolgenModulesController extends HttpServlet {

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

        //Ophalen van alle (distinct) module namen 
        List<Module> lstModules = ModuleServices.GetAllUniqueModules();

        //Gevolgde classificatie ID's ophalen om te bepalen welke voorkennis de gebruiker al heeft
        List<String> lstGevolgdeClassificatieIds = new ArrayList<String>();
        if (request.getParameterValues("chkModule") != null) {
            lstGevolgdeClassificatieIds = Arrays.asList(request.getParameterValues("chkModule"));
        }

        //Lijst van te volgen modules samenstellen  
        List<Module> lstTeVolgenModules = new ArrayList<Module>();

        for (Module module : lstModules) {
            //Reeds gevolgde modules gaan we niet toevoegen, enkel de nog niet gevolgde
            if (!lstGevolgdeClassificatieIds.contains(module.getClassificatie().getId().toString())) {
                //Voorkennis ophalen voor module               
                Set<Modulevoorkennis> moduleVoorkennis = module.getModulevoorkennises();

                //Als er geen voorkennis is vereist mag de module worden toegevoegd
                if (moduleVoorkennis.isEmpty()) {
                    lstTeVolgenModules.add(module);
                } else {
                    //Als er wel voorkennis is vereist moet worden nagekeken of alle voorwaarden ok zijn
                    Boolean magWordenToegevoegd = true;

                    for (Modulevoorkennis m : moduleVoorkennis) {
                        if (!lstGevolgdeClassificatieIds.contains(m.getClassificatie().getId().toString())) {
                            magWordenToegevoegd = false;
                        }
                    }

                    if (magWordenToegevoegd == true) {
                        lstTeVolgenModules.add(module);
                    }
                }
            }
        }
        
        //Viewmodel aanmaken voor de modules die in aanmerking komen om te volgen
        LijstModulesViewModel vmTeVolgenModules = new LijstModulesViewModel(lstTeVolgenModules);
        
        HttpSession session = request.getSession();
        
        //Modules meesturen met session
        session.setAttribute("vmTeVolgenModules", vmTeVolgenModules);
        
        //Aanroepen van de jsp pagina waar de modules op worden getoond
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("TeVolgenModules.jsp");
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
