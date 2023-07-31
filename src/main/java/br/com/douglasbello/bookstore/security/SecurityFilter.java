package br.com.douglasbello.bookstore.security;

import br.com.douglasbello.bookstore.services.impl.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final CustomerService customerService;

    public SecurityFilter(TokenService tokenService, CustomerService customerService) {
        this.tokenService = tokenService;
        this.customerService = customerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if ( token != null ) {
            var username = tokenService.validateToken(token);
            UserDetails customer = customerService.loadUserByUsername(username);

            var auth = new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if ( authHeader == null ) return null;
        return authHeader.replace("Bearer ", "");
    }
}
