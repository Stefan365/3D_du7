
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pak1.Pom;


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
        
        String password;
        //jazykove mutacie:
        String back, pw;
        
        Cookie c = new Cookie("page", "3");
        response.addCookie(c);
        
        HttpSession sessionB = request.getSession();
        fLocale = Pom.getLocale((String)sessionB.getAttribute("jazyk"));
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
        
        out.println("</head>");
        
        out.println("<body>");
        
        out.println("<div id=\"menu\">");
        out.println(pw + " : " + password);
        out.println("</div>");
        
        //ODOSIELACIE TLACITKO:
        out.println("<div id=\"paticka\">");
        out.println("<form action = \"third\" method = \"post\">");
        out.println("<input type=\"submit\" name=\"page\" value=\"" + back + "\" />");
        out.println("</form>");
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
