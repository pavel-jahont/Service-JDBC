package com.gmail.jahont.pavel.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gmail.jahont.pavel.repository.model.Person;

public interface PersonRepository {

    Person add(Connection connection, Person person) throws SQLException;

    int getCount(Connection connection) throws SQLException;

    List<Person> findAll(Connection connection) throws SQLException;

    Person findByName(Connection connection, String searchName) throws SQLException;

}
