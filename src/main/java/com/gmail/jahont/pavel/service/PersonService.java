package com.gmail.jahont.pavel.service;

import java.util.List;

import com.gmail.jahont.pavel.repository.model.Person;

public interface PersonService {

    Person add(Person person);

    List<Person> findAll();
}