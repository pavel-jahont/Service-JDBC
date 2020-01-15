package com.gmail.jahont.pavel.repository.model;

public class Person {

    private Integer id;
    private String name;
    private Integer age;
    private Boolean isActive;

    private Person(Builder builder) {
        id = builder.id;
        name = builder.name;
        age = builder.age;
        isActive = builder.isActive;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final class Builder {

        private Integer id;
        private String name;
        private Integer age;
        private Boolean isActive;

        private Builder() {}

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder age(Integer val) {
            age = val;
            return this;
        }

        public Builder isActive(Boolean val) {
            isActive = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}