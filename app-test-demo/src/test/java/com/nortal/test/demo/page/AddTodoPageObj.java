package com.nortal.test.demo.page;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;

@Component
public class AddTodoPageObj {

    public SelenideElement header (){
        return $x("//div/h1");
    }

    public SelenideElement inputFieldDescription (){
        return $x("//div/form/fieldset[1]/input");
    }

    public SelenideElement buttonSaveTodo (){
        return $x("//div/form/button");
    }
}
