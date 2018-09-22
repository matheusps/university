package com.blackdev.bestCoffee.security.Filters;

import com.blackdev.bestCoffee.credential.Credential;
import com.blackdev.bestCoffee.credential.CredentialRole;
import com.blackdev.bestCoffee.credential.CredentialService;
import com.blackdev.bestCoffee.owner.OwnerService;
import com.blackdev.bestCoffee.security.TokenAuthenticationService;
import com.blackdev.bestCoffee.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userService;

    private OwnerService ownerService;

    private CredentialService credentialService;

    public JWTLoginFilter(String url, AuthenticationManager authManager, CredentialService credentialService, UserService userService, OwnerService ownerService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.credentialService = credentialService;
        this.userService = userService;
        this.ownerService = ownerService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        Credential credentials = new ObjectMapper()
                .readValue(request.getInputStream(), Credential.class);

        System.out.println(credentials.getRole());

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication auth) throws IOException, ServletException {

        String username = ((User) auth.getPrincipal()).getUsername();
        TokenAuthenticationService.addAuthentication(response, username);

        String id = "";

        Credential credential = this.credentialService.getByUsername(username);
        if(credential.getRole() == CredentialRole.ROLE_USER){
            id = "" + this.userService.getByUsername(username).get().getId();
        }else{
            System.out.println(this.ownerService.getOwnerByUsername(username).getId());
            id = "" + this.ownerService.getOwnerByUsername(username).getId();
        }

        response.getWriter().write("\"userId\" :" + id + "}");
        response.addHeader("userId", id);
    }
}
