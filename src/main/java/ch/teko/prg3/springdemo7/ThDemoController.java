package ch.teko.prg3.springdemo7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThDemoController {

    @GetMapping(value = {"/", "index.html"})
    public ModelAndView index() {
        CardData cardData = new CardData();
        cardData.setTitle("SpringDemo5");
        cardData.setText("Programmieren Teil 3");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("cardData", cardData);
        return modelAndView;
    }

    @GetMapping(value = {"/index2", "index2.html"})
    public ModelAndView index2() {

        // Collection ArrayList von Objekten vom Typ CardData
        List<CardData> cardDataList = new ArrayList<>();
        cardDataList.add(this.createCardData("SpringDemo5", "Programmieren Teil 3"));
        cardDataList.add(this.createCardData("SpringBoot", "JavaEE Framework"));
        cardDataList.add(this.createCardData("Teko", "5. Semester"));
        cardDataList.add(this.createCardData("2022", "Januar"));

        // Liste wird der view index2 übergeben
        ModelAndView modelAndView = new ModelAndView("index2");
        modelAndView.addObject("cardDataList", cardDataList);
        return modelAndView;
    }

    // Erstellen der Objekte von CardData ausgelagert
    private CardData createCardData(String title, String text) {
        CardData cardData = new CardData();
        cardData.setTitle(title);
        cardData.setText(text);
        return cardData;
    }

    @GetMapping(value = {"/contact", "contact.html"})
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView("contact");
        Contact contact = new Contact();
        modelAndView.addObject("contact", contact);
        return modelAndView;
    }

    @GetMapping(value = {"/login", "login.html"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping(value = "/submitContactForm")
    public ModelAndView submitForm(@ModelAttribute Contact contact) {
        System.out.printf("%s, %s, %s%n", contact.getName(), contact.getEmail(), contact.getMessage());
        ModelAndView modelAndView = new ModelAndView("processing");
        modelAndView.addObject("contactSubmit", contact);
        return modelAndView;
    }
}
