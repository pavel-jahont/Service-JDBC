package com.gmail.jahont.pavel.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.jahont.pavel.repository.PersonRepository;
import com.gmail.jahont.pavel.repository.model.Person;

public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public Person add(Connection connection, Person person) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO person(name, age, is_active) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setBoolean(3, person.getActive());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return person;
        }
    }

    @Override
    public int getCount(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement();
        ) {
            return statement.executeUpdate("SELECT COUNT(*) FROM person");
        }
    }

    @Override
    public List<Person> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM person")
        ) {
            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                Person person = getPerson(rs);
                persons.add(person);
            }
            return persons;
        }
    }

    @Override
    public Person findByName(Connection connection, String searchName) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE name=?");
        ) {
            statement.setString(1, searchName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getPerson(rs);
                }
            }
            return null;
        }
    }

    private Person getPerson(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Integer age = rs.getInt("age");
        Boolean isActive = rs.getBoolean("is_active");
        return Person.newBuilder()
                .id(id)
                .name(name)
                .age(age)
                .isActive(isActive)
                .build();
    }
}