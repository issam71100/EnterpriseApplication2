package userServlets;

import com.model.Event;
import com.model.Utilisateur;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        urlPatterns = {"/user/sign", "/user/sign"}
)

public class SignIn extends HttpServlet {

    public static final String VUE = "/user/sign_in.jsp";
    public static final String CHAMP_EMAIL = "email";
    public static final String CHAMP_PASS = "motdepasse";
    public static final String CHAMP_CONF = "confirmation";
    public static final String CHAMP_NOM = "nom";
    public static final String ATT_ERREURS = "erreurs";
    public static final String ATT_RESULTAT = "resultat";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        /* Récupération des champs du formulaire. */
        String email = request.getParameter(CHAMP_EMAIL);
        String motDePasse = request.getParameter(CHAMP_PASS);
        String confirmation = request.getParameter(CHAMP_CONF);
        String nom = request.getParameter(CHAMP_NOM);

        /* Validation du champ email. */
        try {
            validationEmail(email);
        } catch (Exception e) {
            erreurs.put(CHAMP_EMAIL, e.getMessage());
        }

        /* Validation des champs mot de passe et confirmation. */
        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (Exception e) {
            erreurs.put(CHAMP_PASS, e.getMessage());
        }

        /* Validation du champ nom. */
        try {
            validationNom(nom);
        } catch (Exception e) {
            erreurs.put(CHAMP_NOM, e.getMessage());
        }

        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
            resultat = "Succès de l'inscription.";
        } else {
            resultat = "Échec de l'inscription.";
        }

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        String id;
        try {
            em.getTransaction().begin();
            Utilisateur user = new Utilisateur(email, motDePasse, nom);
            em.persist(user);
            id = Long.toString(user.getId());
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
            }
        }
        //generate a new session
        HttpSession newSession = request.getSession(true);
        //setting session to expiry in 1 hour
        newSession.setMaxInactiveInterval(60 * 60);
        newSession.setAttribute("name", nom);
        newSession.setAttribute("id", id);

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute(ATT_ERREURS, erreurs);
        request.setAttribute(ATT_RESULTAT, resultat);

        /* Transmission de la paire d'objets request/response à notre JSP */
        //this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        response.sendRedirect("/EnterpriseApplication2-war/eventServlet");

    }

    /**
     * Valide l'adresse mail saisie.
     */
    private void validationEmail(String email) throws Exception {
        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    /**
     * Valide les mots de passe saisis.
     */
    private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
        if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /**
     * Valide le nom d'utilisateur saisi.
     */
    private void validationNom(String nom) throws Exception {
        if (nom != null && nom.trim().length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
    }
}
