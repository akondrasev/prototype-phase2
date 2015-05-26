package inc.webapp.action;

import org.apache.log4j.Logger;

import java.io.IOException;

public abstract class AjaxBaseAction extends BaseAction{
    private static Logger logger = Logger.getLogger(AjaxBaseAction.class);

    protected String jsonResult;

    public String execute() throws IOException {

        try {
            makeJson();
        } catch (Exception e){
            logger.error("Exception while executing ajax action ",e);
            return ERROR;
        }

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        try {
            if(jsonResult != null && logger.isDebugEnabled()){
                logger.debug(String.format("jsonResult: %s", jsonResult));
            }
            response.getWriter().write(jsonResult);
        } catch (IOException ioe) {
            logger.error("Exception while writing json result");
        }

        return NONE;
    }

    protected abstract void makeJson() throws IOException;

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }
}
