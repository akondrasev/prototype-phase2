package inc.webapp.action;

import java.io.IOException;

public abstract class AjaxBaseAction extends BaseAction{

    protected String jsonResult;

    public String execute(){

        makeJson();

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        try {
            response.getWriter().write(jsonResult);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return NONE;
    }

    protected abstract void makeJson();

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }
}
