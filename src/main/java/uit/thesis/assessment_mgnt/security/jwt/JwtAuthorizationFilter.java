package uit.thesis.assessment_mgnt.security.jwt;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);


    private JwtUtils jwtUtils;
    private UserDetailsService userDetailsService;

    private String parseToUsername(String username){
       int idx = username.indexOf("-");
        return username.substring(0, idx);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtils.getTokenFromRequest(request);
        if(token != null && jwtUtils.validateJwtToken(token)){
            String username = jwtUtils.getUsernameFromToken(token);
            String res = parseToUsername(username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(res);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        }
        filterChain.doFilter(request, response);
    }
}
