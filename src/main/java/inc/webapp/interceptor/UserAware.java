package inc.webapp.interceptor;

import inc.db.model.User;

public interface UserAware {
    void setUser(User user);
}
