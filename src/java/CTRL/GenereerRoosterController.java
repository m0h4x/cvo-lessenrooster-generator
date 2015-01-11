/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Lesmoment;
import DAL.Module;
import SL.ModuleServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        List<Module> lstModulesDieWelKunnenWordenGevolgd = new ArrayList<Module>();
        List<Module> lstModulesDieNietKunnenWordenGevolgd = new ArrayList<Module>();

        for (Module m : lstGekozenModules) {
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
                lstModulesDieWelKunnenWordenGevolgd.add(m);
            } else {
                lstModulesDieNietKunnenWordenGevolgd.add(m);
            }
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(lstModulesDieWelKunnenWordenGevolgd + "<BR>");
            out.println(lstModulesDieNietKunnenWordenGevolgd + "<BR>");
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
