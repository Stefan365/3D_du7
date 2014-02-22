
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pak1.Pom;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * Použití cookies pro počítání přístupů daného klienta na webový server.
 *
 * @author Jaroslav Srp
 */
public class Page1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        // příprava odpovědi pro klienta
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String volba = request.getParameter("jaz");

        if (volba == null || volba.equals("")) {
            //neudelej nic
        } else {
            HttpSession sessionA = request.getSession();
            sessionA.setAttribute("jazyk", volba);
        }

        //tvorba těla odpovědi
        PrintWriter out = response.getWriter();

        Pom.printHead(out);
        Pom.printCSS(out);

        out.println("</head>\n"
            + "    <body>\n"
            + "        <form action = \"/DU7/second\" method = \"post\">\n"
            + "                <p>\n"
            + "                    <label>\n"
            + "                        VÝBĚR/CHOICE       :     \n"
            + "                        <input type=\"radio\" name=\"jaz\" value=\"cz\" checked=\"true\"/>CZ \n"
            + "                        <input type=\"radio\" name=\"jaz\" value=\"uk\"/>UK \n"
            + "                        <input type=\"radio\" name=\"jaz\" value=\"us\"/>USA <br/><br/>\n"
            + "                        \n"
            + "                        <input type=\"submit\" value=\"Send/Odeslat\"/>\n"
            + "                    </label>\n"
            + "                </p>\n"
            + "        </form>\n"
            + "    </body>\n"
            + "</html>");

    }
    
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        
        doPost(request, response);
    }
}
