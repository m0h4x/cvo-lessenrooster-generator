/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Lesmoment;
import DAL.Module;
import SL.ModuleServices;
import VM.LijstModulesViewModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wim
 */
public class GenereerRoosterController2 extends HttpServlet {

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

        HttpSession session = request.getSession();
        LijstModulesViewModel vmLstModulesDieWelKunnenWordenGevolgd
                = (LijstModulesViewModel) session.getAttribute("vmLstModulesDieWelKunnenWordenGevolgd");      
        
        
        //De gekozen modules worden mogelijk op meerdere tijdstippen gegeven, 
        //de overige modules moeten ook terug aan de lijst worden toegevoegd
        List<Module> lstModulesFinal = new ArrayList<Module>();        
        for (Module m : vmLstModulesDieWelKunnenWordenGevolgd.getModules())
        {
            List<Module> lstSoortgelijkeModules = new ArrayList<Module>();            
            lstSoortgelijkeModules = ModuleServices.GetAllModulesMetClassificatie(m.getClassificatie());
            
            for (Module m2 : lstSoortgelijkeModules)
            {
                lstModulesFinal.add(m2);
            }
        }               
      
        List<Lesmoment> lstAlleLesmomenten = new ArrayList<Lesmoment>();
        
        for (Module m : lstModulesFinal) {
            Set<Lesmoment> ModuleLesmomenten = m.getLesmoments();

            for (Lesmoment l : ModuleLesmomenten) {
                lstAlleLesmomenten.add(l);
            }
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GenereerRoosterController2</title>");
            out.println("</head>");
            out.println("<body>");
            
            for (Lesmoment l : lstAlleLesmomenten) {
                out.println(l.getModule().getNaam() + " " + l.getModule().getCode() + " " + l.getDatum() + "</br>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
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
