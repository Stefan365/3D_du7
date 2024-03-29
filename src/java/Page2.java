
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
 * @author Stefan Veres
 */
public class Page2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        Locale fLocale; //finalne Locale

        String firstname = "", surname = "", password = "", send="";

        HttpSession sessionA = request.getSession();
        String volba = Pom.nastavJazyk(sessionA, request);
        fLocale = Pom.getLocale(volba);
        
        
        Cookie c = new Cookie("page", "3");
        response.addCookie(c);
        
        ResourceBundle fTexty = ResourceBundle.getBundle("Texty", fLocale);
        firstname = "" + fTexty.getString("name");
        surname = "" + fTexty.getString("surname");
        password = "" + fTexty.getString("password");
        send =  "" + fTexty.getString("send");

        // příprava odpovědi pro klienta
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //tvorba těla odpovědi
        PrintWriter out = response.getWriter();
        
        Pom.printHead(out);
        Pom.printCSS(out);
 
        out.println("</head>");
        out.println("<body>");

        out.println("<form action = \"/DU7/third\" method = \"post\">");
        out.println("<p>");
        out.println("<label>");
        out.println(firstname + " : ");
        out.println("<input type=\"text\" name=\"firstname\"/><br/>");
        out.println(surname + " : ");
        out.println("<input type=\"text\" name=\"surname\"/><br/>");
        out.println(password + " : ");
        out.println("<input type=\"password\" name=\"password\"/><br/>");
       
        out.println("<input type=\"submit\" value=" + send +">");

        out.println("</label>");
        out.println("</p>");
        out.println("</form>");

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
