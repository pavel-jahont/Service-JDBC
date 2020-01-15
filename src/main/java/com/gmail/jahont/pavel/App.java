package com.gmail.jahont.pavel;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.gmail.jahont.pavel.repository.model.Person;
import com.gmail.jahont.pavel.service.PersonService;
import com.gmail.jahont.pavel.service.impl.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();

        Person person = Person.newBuilder()
                .name("test")
                .age(10)
                .isActive(true)
                .build();
        personService.add(person);

        List<Person> people = personService.findAll();
        people.forEach(logger::info);
    }
}