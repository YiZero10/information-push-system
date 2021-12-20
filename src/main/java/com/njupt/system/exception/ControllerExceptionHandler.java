package com.njupt.system.exception;

import com.njupt.system.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Elaine Huang
 * @date 2021/12/20 2:14 PM
 * @signature Do it while you can!
 */
@ControllerAdvice
@Controller
public class ControllerExceptionHandler implements ErrorController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @RequestMapping("/error")
    @ResponseBody
    public HttpResponse<Void> handleException(Exception uncaughtException){
        if (uncaughtException == null){
            return HttpResponse.failure(500,"No found exception!");
        }
        if (uncaughtException instanceof LocalRuntimeException){
            LocalRuntimeException localRuntimeException = (LocalRuntimeException) uncaughtException;
            return HttpResponse.failure(localRuntimeException.getError().getCode(),localRuntimeException.getMessage());
        }else{
            uncaughtException.printStackTrace();
            return HttpResponse.failure(500,uncaughtException.getMessage());
        }
    }

    public String getErrorPath() {return "/error";}
}