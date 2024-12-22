package config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AaFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // Récupérer l'origine de la requête
        String origin = request.getHeader("Origin");

        // Configurer les en-têtes CORS
        if (origin != null) {
            // Autoriser dynamiquement l'origine envoyée par la requête
            response.setHeader("Access-Control-Allow-Origin", origin);
        }

        // Ajouter les autres en-têtes CORS
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Méthodes autorisées
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With"); // En-têtes autorisés
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Si des cookies ou sessions doivent être envoyés

        // Gérer les requêtes prévol (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // Réponse pour les requêtes OPTIONS
            response.setStatus(HttpServletResponse.SC_OK); // Retourne un statut 200 pour les requêtes OPTIONS
            return; // Terminer ici pour les requêtes OPTIONS
        }

      
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
      
    }
}