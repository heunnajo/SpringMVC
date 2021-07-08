package hello.servlet.web.frontcontroller.v32;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV32 {
    ModelView process(Map<String,String> paramMap);
}
