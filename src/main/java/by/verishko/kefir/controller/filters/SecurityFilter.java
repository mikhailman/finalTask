package by.verishko.kefir.controller.filters;

import by.verishko.kefir.controller.action.Command;
import by.verishko.kefir.controller.action.userAction.*;
import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecurityFilter implements Filter {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     * The FilterChain passed in to this method allows the Filter to pass
     * on the request and response to the next entity in the chain.
     *
     * <p>A typical implementation of this method would follow the following
     * pattern:
     * <ol>
     * <li>Examine the request
     * <li>Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering
     * <li>Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering
     * <li>
     * <ul>
     * <li><strong>Either</strong> invoke the next entity in the chain
     * using the FilterChain object
     * (<code>chain.doFilter()</code>),
     * <li><strong>or</strong> not pass on the request/response pair to
     * the next entity in the filter chain to
     * block the request processing
     * </ul>
     * <li>Directly set headers on the response after invocation of the
     * next entity in the filter chain.
     * </ol>
     *
     * @param request  the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     * @see UnavailableException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Command command = (Command) httpRequest.getAttribute("action");
        User user = (User) httpRequest.getSession().getAttribute("authorizedUser");
        boolean flag = false;
        if (user != null && user.getRole() == Role.ADMINISTRATOR) {
            if (adminAction.containsKey(command.getClass())) {
                flag = true;
            }
        } else {
            if (userAction.containsKey(command.getClass())) {
                flag = true;
            }
        }
        if (flag) {
            chain.doFilter(request, response);
        } else {
            logger.error("Ошибка доступа!");
            httpRequest.getRequestDispatcher(ConstantsPath.ERROR_PAGE).forward(request, response);
        }
    }

    private static Map<Class<? extends Command>, String> adminAction = new ConcurrentHashMap<>();
    private static Map<Class<? extends Command>, String> userAction = new ConcurrentHashMap<>();

    static {
        //adminActions
        adminAction.put(HomeCommand.class, "");
        adminAction.put(LoginCommand.class, "");
        adminAction.put(LogoutCommand.class, "");
        adminAction.put(MenuCommand.class, "");
        adminAction.put(RegistrationCommand.class, "");
        adminAction.put(RegistrPageCommand.class, "");
        // will be complete

        //userActions
        userAction.put(HomeCommand.class, "");
        userAction.put(LoginCommand.class, "");
        userAction.put(LogoutCommand.class, "");
        userAction.put(MenuCommand.class, "");
        userAction.put(RegistrationCommand.class, "");
        userAction.put(RegistrPageCommand.class, "");
        //also will be complete
    }
}
