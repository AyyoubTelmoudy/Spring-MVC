package com.emsi.pfa.shared;


import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import com.emsi.pfa.entity.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class Utils {
    private final static Random RANDOM=new SecureRandom();
    private final static String ALPHANUM="01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz";

    public String genereteRandomString(int length)
    {
        StringBuilder returnvalue=new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnvalue.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return new String(returnvalue);
    }

    public static Object getAuthenticatedUser()
    {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void redirctUser(HttpServletResponse response) throws IOException {
        Object principal =getAuthenticatedUser();
        if(principal instanceof UserDetailsImpl)
        {
            String role=((UserDetailsImpl)principal).getRole();
            switch(role)
            {
                case "ROLE_JOURNALISTE":response.sendRedirect("/journalistes");break;
                case "ROLE_CHEF":response.sendRedirect("/chefs");break;
                case "ROLE_LECTEUR":response.sendRedirect("/");break;
                case "ROLE_ADMINISTRATEUR":response.sendRedirect("/admins");break;
            }
        }
    }

}
