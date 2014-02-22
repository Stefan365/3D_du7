package pak1;

import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stefan
 */
public class Pom {

    public static String volba; //= "cz";
    private static Locale cestina = new Locale("cs", "CZ");
    private static Locale usa = Locale.US;
    private static Locale uk = Locale.UK;
    private static String firstname = "", surname = "", password = "";

    /**
     * Spracuje session zoznam cookies a nastavi farbu pisma, pozadia a velkost
     * pisma.
     *
     * @param mySiteCookies cookies od klienta.
     * @param response pripravovana odpoved.
     *
     */
    public static String spracujSessionA(HttpSession mySession, HttpServletRequest request) {
        volba = "cz";

        volba = request.getParameter("jaz");

        if (volba == null || volba.equals("")) {
            //uloz to co si mal v session do lokalnej premennej, ktoru budes dalej skumat:
            volba = (String) mySession.getAttribute("jazyk");
        } else {
            //zapametaj si volbu do session:
            mySession.setAttribute("jazyk", volba);
        }
        return volba;
    }

    public static Locale getLocale(String volba) {
        //zisti co si zvolil a na zaklade toho sa rozhodni:
        if (volba.equals("cz")) {
            return cestina;
        } else if (volba.equals("uk")) {
            return uk;
        } else if (volba.equals("us")) {
            return usa;
        } else {
            //pre istotu, ze ked nic, tak cestina.
            return cestina;
        }
    }

    public static void printHead(PrintWriter out) {
        out.println("<?xml version = \"1.0\" encoding=\"UTF-8\"?>\n"
            + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n"
            + "    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
            + "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"cs\">\n"
            + "    <head>\n"
            + "        <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
            + "        <title>DU7</title>\n");
    }

    public static void printCSS(PrintWriter out) {
        out.println("<style type=\"text/css\">\n"
            + "body{\n"
            + "	background-color: #eef;\n"
            + "	color: #000;\n"
            + "    font-size: 15;\n"
            + "    font-family: Verdana;\n"
            + "    \n"
            + "}\n"
            + "\n"
            + "#menu{\n"
            + "position: absolute;\n"
            + "width:189px;\n"
            + "height:477px;\n"
            + "top:210px;\n"
            + "left: 40px \n"
            + "}\n"
            + "\n"
            + "#podmenu{\n"
            + "position: absolute;\n"
            + "top:250px;\n"
            + "left: 40px \n"
            + "}\n"
            + "\n"
            + "#hlavicka {\n"
            + "position: absolute;\n"
            + "width:1000px;\n"
            + "height:42px;\n"
            + "top: 20px;\n"
            + "left: 40px\n"
            + "}\n"
            + "\n"
            + "#paticka {\n"
            + "position: absolute;\n"
            + "width:900px;\n"
            + "height:89px;\n"
            + "overflow: hidden;\n"
            + "bottom:50px;\n"
            + "left: 40px \n"
            + "}\n"
            + "\n"
            + "#podpaticka {\n"
            + "position: absolute;\n"
            + "width:900px;\n"
            + "height:89px;\n"
            + "overflow: hidden;\n"
            + "bottom:30px;\n"
            + "left: 40px \n"
            + "} </style>");
    }

    public static void spracujSessionB(HttpSession sessionB, HttpServletRequest request) {

        firstname = request.getParameter("firstname");
        surname = request.getParameter("surname");
        password = request.getParameter("password");
        
        if (!(firstname == null || firstname.equals(""))) {
            sessionB.setAttribute("firstname", firstname);
        }
        if (!(surname == null || surname.equals(""))) {
            sessionB.setAttribute("surname", surname);
        }
        if (!(password == null || password.equals(""))) {
            sessionB.setAttribute("password", password);
        }
    }
}
