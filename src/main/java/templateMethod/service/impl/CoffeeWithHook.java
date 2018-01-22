package templateMethod.service.impl;

import templateMethod.service.CaffeineBeverageWithHook;

public class CoffeeWithHook extends CaffeineBeverageWithHook {

    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    public boolean customerWantsCondiments() {
        String answer = getUserInput();
        if (null == answer || !answer.toLowerCase().startsWith("y")) {
            return false;
        }

        return false;
    }

    private String getUserInput() {
        return "no";
    }
}
