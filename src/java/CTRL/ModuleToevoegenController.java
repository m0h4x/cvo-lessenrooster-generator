/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Classificatie;
import DAL.Module;
import SL.ClassificatieServices;
import SL.ModuleServices;
import VM.LijstModulesViewModel;
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
public class ModuleToevoegenController extends HttpServlet {

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

        //Datums verwerken
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String beginDatum = request.getParameter("Begindatum");
        String eindDatum = request.getParameter("Einddatum");
        Date date1 = format.parse(beginDatum);
        Date date2 = format.parse(eindDatum);

        //Classificatie verwerken en bewaren in db indien nog niet gekend
        String c = request.getParameter("Classificatie");
        Classificatie classificatie = ClassificatieServices.getClassificatie(c);

        if (classificatie == null) {
            classificatie = new Classificatie();
            classificatie.setCode(c);
            ClassificatieServices.Save(classificatie);
        }
        
        //Module aanmaken en bewaren
        Module module = new Module();
        module.setNaam(request.getParameter("Naam"));
        module.setBegindatum(date1);
        module.setEinddatum(date2);
        module.setCode(Integer.parseInt(request.getParameter("Code")));
        module.setClassificatie(classificatie);
        ModuleServices.Save(module);

        LijstModulesViewModel vm
                = new LijstModulesViewModel(
                        ModuleServices.GetAllModules());

        HttpSession session = request.getSession();
        session.setAttribute("ViewModel", vm);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("ModulesTonen.jsp");
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
            Logger.getLogger(ModuleToevoegenController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModuleToevoegenController.class.getName()).log(Level.SEVERE, null, ex);
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
