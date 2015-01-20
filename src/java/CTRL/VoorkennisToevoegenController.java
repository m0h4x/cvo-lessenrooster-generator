/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Classificatie;
import DAL.Module;
import DAL.Modulevoorkennis;
import SL.ClassificatieServices;
import SL.ModuleServices;
import SL.VoorkennisServices;
import VM.LijstClassificatieViewModel;
import VM.LijstVoorkennisViewModel;
import java.io.IOException;
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
public class VoorkennisToevoegenController extends HttpServlet {

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

        //Ophalen module id en classificatie id
        Integer classificatieId = Integer.parseInt(request.getParameter("selectlist"));
        Integer moduleId = Integer.parseInt(request.getParameter("ModuleId"));

        //Id's converteren naar module en classificatie objecten
        Module module = ModuleServices.getModule(moduleId);
        Classificatie classificatie = ClassificatieServices.getClassificatie(classificatieId);
        
        //Aanmaken modulevoorkennis object
        Modulevoorkennis modulevoorkennis = new Modulevoorkennis();
        modulevoorkennis.setModule(module);
        modulevoorkennis.setClassificatie(classificatie);
        
        //Bewaren modulevoorkennis object
        VoorkennisServices.Save(modulevoorkennis);
        
        //Ophalen van de vereiste voorkennis voor een module op basis van Id
        LijstVoorkennisViewModel vmVoorkennis
                = new LijstVoorkennisViewModel(
                        VoorkennisServices.GetAllModuleVoorkennis(module.getId()));
        
        //Ophalen van alle bestaande classificaties, deze kunnen worden gebruikt bij het toevoegen van een vereiste voorgaande module
        LijstClassificatieViewModel vmClassificatie
                = new LijstClassificatieViewModel(
                        ClassificatieServices.GetAllClassificaties());

        HttpSession session = request.getSession();
        
        //De nodige informatie meesturen met session
        session.setAttribute("vmVoorkennis", vmVoorkennis);
        session.setAttribute("vmClassificatie", vmClassificatie);

        //Aanroepen van de jsp pagina waar de voorkennis van een bepaalde module wordt getoond
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("VoorkennisTonen.jsp");
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
