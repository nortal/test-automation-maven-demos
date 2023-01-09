package com.nortal.test.demo.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class MainPageObj {

    public SelenideElement header (){
        return $x("//nav/a");
    }

    public ElementsCollection rowsTodoTable (){
        return $$x("//div/table/tbody/tr");
    }

    public SelenideElement buttonRemoveTodo (){
        return $x("//div/table/tbody/tr[1]/td[4]/button");
    }

    public SelenideElement buttonAddNewTodo (){
        return $x("//div/button");
    }
}
