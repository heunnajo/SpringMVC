package hello.servlet.web.frontcontroller.v32.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v32.ControllerV32;

import java.util.Map;

public class MemberFormControllerV32 implements ControllerV32 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
