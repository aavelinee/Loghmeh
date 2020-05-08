package loghmeh_server.security;

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
        System.out.println("is authorized?!");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("uri:" + request.getRequestURI().split("/")[2]);
        String uri = "/" + request.getRequestURI().split("/")[2];
        if (uri.equals(SIGN_UP_URL) || uri.equals(SIGN_IN_URL)) {
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
                ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            request.setAttribute("userId", userId);
        }
        chain.doFilter(request, servletResponse);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }
}
