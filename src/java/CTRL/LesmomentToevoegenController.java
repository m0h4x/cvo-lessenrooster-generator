/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Module;
import DAL.Lesmoment;
import SL.LesmomentServices;
import SL.ModuleServices;
import VM.LijstLesmomentenViewModel;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LesmomentToevoegenController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Module id ophalen, deze is nodig als we lesmomenten willen toevoegen aan een module
        String ModuleId = request.getParameter("ModuleId");
        Module module = ModuleServices.getModule(Integer.parseInt(ModuleId));
        
        //Datum lesmoment ophalen en parsen
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String datum = request.getParameter("Datum");
        Date date = format.parse(datum);
        
        //Nieuw lesmoment aanmaken
        Lesmoment lesmoment = new Lesmoment();
        lesmoment.setModule(module);
        lesmoment.setLokaal(request.getParameter("Lokaal"));
        lesmoment.setBeginuur(request.getParameter("Beginuur"));
        lesmoment.setEinduur(request.getParameter("Einduur"));
        lesmoment.setDatum(date);
        
        //Lesmoment bewaren
        LesmomentServices.Save(lesmoment);
        
        //Ophalen van alle lesmomenten voor een bepaalde module op basis van Id
        LijstLesmomentenViewModel vmLesmomenten
                = new LijstLesmomentenViewModel(
                        LesmomentServices.GetAllLesmomenten(module.getId()));
        
        HttpSession session = request.getSession();
        
        //Lesmomenten + geselecteerde module meesturen met session
        session.setAttribute("vmLesmomenten", vmLesmomenten);
        session.setAttribute("ModuleId", request.getParameter("id"));

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("LesmomentenTonen.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(LesmomentToevoegenController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(LesmomentToevoegenController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
