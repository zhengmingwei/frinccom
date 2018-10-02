package com.cfcp.incc.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhyj on 16/5/30.
 */
public class DynamicAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private String ajaxFailureUrl;
    private String noAjaxFailureUrl;

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(this.isAjaxRequest(request)){
            this.setDefaultFailureUrl(ajaxFailureUrl);
        } else {
            String err = "?err=1";
            if (exception != null && exception instanceof CaptchaException)
                err = "?err=2";
            this.setDefaultFailureUrl(noAjaxFailureUrl+err);
        }
        super.onAuthenticationFailure(request, response, exception);
    }

    private boolean isAjaxRequest(HttpServletRequest request){
        String requestType = request.getHeader("X-Requested-With");
        return requestType != null;
    }

    public String getAjaxFailureUrl() {
        return ajaxFailureUrl;
    }

    public void setAjaxFailureUrl(String ajaxFailureUrl) {
        this.ajaxFailureUrl = ajaxFailureUrl;
    }

    public String getNoAjaxFailureUrl() {
        return noAjaxFailureUrl;
    }

    public void setNoAjaxFailureUrl(String noAjaxFailureUrl) {
        this.noAjaxFailureUrl = noAjaxFailureUrl;
    }
}
