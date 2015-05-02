package inc.webapp.interceptor;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import inc.XConstants;
import inc.db.model.User;

import java.util.Map;

public class AuthenticationInterceptor implements Interceptor {
    public void destroy() {

    }

    public void init() {

    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();

        User user = (User) sessionAttributes.get(XConstants.SESSION_ATTRIBUTE_KEY_USER);

        Action action = (Action) actionInvocation.getAction();

        if (user == null) {
            user = new User();
            user.setUserName(XConstants.USER_GUEST_NAME);
            user.setIsGuest(true);
        }

        if (action instanceof UserAware) {
            ((UserAware) action).setUser(user);
        }

        return actionInvocation.invoke();
    }
}
