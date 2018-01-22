package templateMethod.service.impl;

import templateMethod.service.CaffeineBeverageWithHook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TeaWithHook extends CaffeineBeverageWithHook {

    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Lemon");
    }

    @Override
    public boolean customerWantsCondiments() {
        String answer = getUserInput();
        if (null == answer || !answer.toLowerCase().startsWith("y")) {
            return false;
        }

        return true;
    }

    private String getUserInput() {
        return "yes";
    }
}
