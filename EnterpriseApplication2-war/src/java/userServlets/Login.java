/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userServlets;

import com.model.Event;
import com.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static userServlets.SignIn.VUE;

/**
 *
 * @author issam
 */
@WebServlet(name = "Login", urlPatterns = {"/user/login", "/user/login/"})
public class Login extends HttpServlet {

    public static final String VUE = "/user/login.jsp";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
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

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("motdepasse");

            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> criteriaQuery = criteriaBuilder.createQuery(Utilisateur.class);
            Root<Utilisateur> user = criteriaQuery.from(Utilisateur.class);

            criteriaQuery.select(user).where(criteriaBuilder.equal(user.get("email"), email));
            Utilisateur utilisateur= null;
            if(em.createQuery(criteriaQuery).getResultList().size() > 0)
                utilisateur = em.createQuery(criteriaQuery).getResultList().get(0);
            
            if (utilisateur != null && password.equals(utilisateur.getMotDePasse())) {
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                HttpSession newSession = request.getSession(true);
                //setting session to expiry in 1 hour
                newSession.setMaxInactiveInterval(60 * 60);
                newSession.setAttribute("name", utilisateur.getNom());
                newSession.setAttribute("id", utilisateur.getId());
                Cookie message = new Cookie("message", "Welcome");
                response.addCookie(message);
                System.out.println("success");
                response.sendRedirect("/EnterpriseApplication2-war/eventServlet");
            } else {
                doGet(request, response);
            }
            request.setAttribute("user_session", utilisateur);

        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
            }
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
