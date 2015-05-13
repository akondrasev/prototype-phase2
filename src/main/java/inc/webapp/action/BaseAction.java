package inc.webapp.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import inc.XConstants;
import inc.db.model.User;
import inc.webapp.interceptor.UserAware;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, UserAware, ModelDriven<User> {
    private static Logger logger = Logger.getLogger(BaseAction.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected String submit;

    protected User user;
    protected Map<String, Object> session;

    protected Gson gson = new Gson();


    public String execute() throws Exception {
        return SUCCESS;
    }

    public void reflectionTask(){

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        if(logger.isDebugEnabled()){
            logger.debug(classLoader);
        }


        Field[] fields = XConstants.class.getDeclaredFields();

        for(Field field : fields){
            if(logger.isDebugEnabled()){
                logger.debug(field);
            }
        }

    }

    @Override
    public User getModel() {
        return user;
    }

    public User getUser(){
        return user;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
}
