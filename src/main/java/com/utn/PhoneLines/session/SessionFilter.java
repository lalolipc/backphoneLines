package com.utn.PhoneLines.session;

import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {
/*
    @Autowired
    private SessionManager sessionManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String sessionToken = request.getHeader("Authorization");
        Session session = null;

        session = sessionManager.getSession(sessionToken);

        if (null != session) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

*/
    @Autowired
    private SessionManager sessionManager;

    private static final String userTypeClient = "CLIENT";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String sessionToken = request.getHeader("Authorization");
        Session session = sessionManager.getSession(sessionToken);

        if (null != session) {

            if (session.getLoggedUser().getUserType().getIdUserType() == UserType.EUSERTYPE.CLIENT.getValue() && session.getLoggedUser().getActive() == true) {
                filterChain.doFilter(request, response);
            }
            else {
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        }
        else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}