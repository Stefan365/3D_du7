
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
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
public class Page4 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        
        

        Locale fLocale; //finalne Locale
        
        String password = "";
        //jazykove mutacie:
        String back  = "", pw ="" ;
        
        
        HttpSession sessionB = request.getSession();
        //String volba = Pom.spracujSessionA(sessionB, request);
        fLocale = Pom.getLocale((String)sessionB.getAttribute("jazyk"));
        //Pom.spracujSessionB(sessionB, request);
        
        password = "" + sessionB.getAttribute("password");
        
        //na zobrazenie datumu:
        ResourceBundle fTexty = ResourceBundle.getBundle("Texty", fLocale);
        pw = "" + fTexty.getString("password");
        back = "" + fTexty.getString("back");
            
        
        // příprava odpovědi pro klienta
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //tvorba těla odpovědi
        PrintWriter out = response.getWriter();
        
        Pom.printHead(out);
        Pom.printCSS(out);
        
        //out.println(firstname + " " + surname + ", " + choice  + " : " + volba);
        out.println("</head>");
        
        out.println("<body>");
        
        out.println("<div id=\"menu\">");
        out.println(pw + " : " + password);
        out.println("</div>");

        //odkaz na stranku zpet:
        out.println("<div id=\"paticka\">");
        out.println("<a href=\"http://localhost:8080/DU7/third\">" + back + "</a>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");

    }
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        
        doPost(request, response);
    }
}