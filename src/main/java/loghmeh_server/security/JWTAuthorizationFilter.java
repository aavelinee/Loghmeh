package loghmeh_server.security;

import loghmeh_server.domain.Loghmeh;

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
import static loghmeh_server.security.SecurityConstants.*;


@ WebFilter(asyncSupported = true)
public class JWTAuthorizationFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String[] uriTokens = request.getRequestURI().split("/");
        if(uriTokens.length < 3) {
            return;
        }
        String uri = "/" + uriTokens[2];
        if (uri.equals(SIGN_UP_URL) || uri.equals(SIGN_IN_URL) || uri.equals(GOOGLE_SIGN_IN)) {
            chain.doFilter(servletRequest, servletResponse);
        }
        else {
            String header = request.getHeader(HEADER_STRING);
            if (header == null || !header.startsWith(TOKEN_PREFIX)) {
                ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            int userId = JWTUtils.getInstance().verifyJWTToken(header);
            if (userId == -1) {
                System.out.println("no user id in filter");
                ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            if(Loghmeh.getInstance().getCustomerById(userId) == null) {
                System.out.println("no such user");
                ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            request.setAttribute("userId", userId);
        }
        System.out.println("AUTH before doFilter");
        chain.doFilter(request, servletResponse);
        System.out.println("AUTH after doFilter");

    }

    public void init(FilterConfig fConfig) throws ServletException {

    }
}
