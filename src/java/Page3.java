
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pak1.Pom;
import static java.text.DateFormat.*;
import java.text.NumberFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 * Použití cookies pro počítání přístupů daného klienta na webový server.
 *
 * @author Jaroslav Srp
 */
public class Page3 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {

        request.setCharacterEncoding("utf-8");

        //finalne Locale:
        Locale fLocale;

        String firstname = "", surname = "", password = "", volba = "";
        //jazykove mutacie:
        String datum = "", pw_l = "", choice = "", pw = "";

        HttpSession sessionB = request.getSession();

        //int location = (Integer) request.getAttribute("page");
        Cookie[] cookies = request.getCookies();

        String location = "";

        int k;
        for (int i = 0; i < cookies.length; i++ ) {
            if (cookies[i].getName().equals("page")) {
                location = "" + cookies[i].getValue();
                k = i;
            }
        }

        //nastavenie presmerovania:
        if (location.equals("3")) {
            Cookie c = new Cookie("page", "4");
            response.addCookie(c);
        } else if (location.equals("4")) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/fourth");
            rd.forward(request, response);
        }

        fLocale = Pom.getLocale((String) sessionB.getAttribute("jazyk"));
        Pom.spracujSessionB(sessionB, request);

        firstname = "" + sessionB.getAttribute("firstname");
        surname = "" + sessionB.getAttribute("surname");
        password = "" + sessionB.getAttribute("password");
        volba = "" + sessionB.getAttribute("jazyk");

        //na zobrazenie datumu:
        ResourceBundle fTexty = ResourceBundle.getBundle("Texty", fLocale);
        datum = "" + fTexty.getString("date");
        pw_l = "" + fTexty.getString("pw_length");
        choice = "" + fTexty.getString("choice");
        pw = "" + fTexty.getString("password");

        int dlzka_ret = 8 * password.getBytes().length;
        NumberFormat nf = NumberFormat.getInstance(fLocale);

        //datum:
        DateFormat f = getDateInstance(LONG, fLocale);
        String d = f.format(new java.util.Date());

        // příprava odpovědi pro klienta
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //tvorba těla odpovědi
        PrintWriter out = response.getWriter();

        Pom.printHead(out);
        Pom.printCSS(out);

        out.println("</head>");

        out.println("<body>");

        out.println("<p id=\"hlavicka\">");
        out.println(firstname + " " + surname + ", " + choice + " : " + volba);
        out.println("</p>");

        out.println("<p id=\"menu\">");
        out.println(pw_l + " : " + nf.format(dlzka_ret));
        out.println("</p>");

        out.println("<div id=\"podpaticka\">");
        out.println(datum + " : " + d);
        out.println("</div>");

        out.println("<div id=\"podmenu\">");
        out.println("<a href=\"http://localhost:8080/DU7/third\">" + pw + "</a>");
        //out.println("<a href=\"third\">" + pw + "</a>");
        out.println("</div>");

        /*
         out.println("<div id=\"paticka\">");
         out.println("<a href=\"http://localhost:8080/DU7/second\">" + "BACK" + "</a>");
         out.println("</div>");
         */
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
