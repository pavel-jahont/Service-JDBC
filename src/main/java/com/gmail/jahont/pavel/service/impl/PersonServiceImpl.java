package com.gmail.jahont.pavel.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.gmail.jahont.pavel.repository.ConnectionRepository;
import com.gmail.jahont.pavel.repository.PersonRepository;
import com.gmail.jahont.pavel.repository.impl.ConnectionRepositoryImpl;
import com.gmail.jahont.pavel.repository.impl.PersonRepositoryImpl;
import com.gmail.jahont.pavel.repository.model.Person;
import com.gmail.jahont.pavel.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = new ConnectionRepositoryImpl();
    private PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public Person add(Person person) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                person = personRepository.add(connection, person);
                connection.commit();
                return person;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Person> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Person> people = personRepository.findAll(connection);
                connection.commit();
                return people;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

}