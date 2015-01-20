/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL;

import DAL.Lesmoment;
import VM.LijstLesmomentenViewModel;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Wim
 */
public class ExportController extends HttpServlet {

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

        LijstLesmomentenViewModel vmLesmomentenFinal
                = (LijstLesmomentenViewModel) session.getAttribute("vmLesmomentenFinal");

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont(font, 16);
        contentStream.moveTextPositionByAmount(60, 700);

        // Titel
        contentStream.drawString("Lessenrooster");

        contentStream.setFont(font, 10);
        contentStream.moveTextPositionByAmount(0, -20);

        int recordTeller = 0;
        for (Lesmoment l : vmLesmomentenFinal.getLesmomenten()) {

            Calendar c = Calendar.getInstance();
            c.setTime(l.getDatum());
            int dag = c.get(Calendar.DAY_OF_WEEK);

            String strDag = "";

            switch (dag) {
                case 0:
                    strDag = "Zondag";
                    break;
                case 1:
                    strDag = "Maandag";
                    break;
                case 2:
                    strDag = "Dinsdag";
                    break;
                case 3:
                    strDag = "Woensdag";
                    break;
                case 4:
                    strDag = "Donderdag";
                    break;
                case 5:
                    strDag = "Vrijdag";
                    break;
                case 6:
                    strDag = "Zaterdag";
                    break;
            }

            //Nieuwe page beginnen wanner 25 records werden weggeschreven
            if (recordTeller == 25) {
                recordTeller = 0;
                contentStream.endText();
                contentStream.close();
                
                page = new PDPage();
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                contentStream.beginText();
                contentStream.setFont(font, 10);
                contentStream.moveTextPositionByAmount(60, 700);
            } else {
                recordTeller++;
            }

            contentStream.drawString(l.getDatum().toString() + "     " + strDag + "     " + l.getBeginuur()
                    + "     " + l.getEinduur() + "     " + l.getLokaal() + "     " + l.getModule().getCode()
                    + "     " + l.getModule().getNaam());
            contentStream.moveTextPositionByAmount(0, -20);
        }

        contentStream.endText();

        // Make sure that the content stream is closed:
        contentStream.close();

        try {
            // Save the results and ensure that the document is properly closed:
            document.save("C:\\Temp\\test.pdf");
        } catch (COSVisitorException ex) {
            Logger.getLogger(ExportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();

        //Teruggaan naar resultaat pagina
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
