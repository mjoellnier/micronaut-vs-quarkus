package org.acme.quickstart.model;

import java.util.Objects;

public class Animal {

    private String id;
    private String name;
    private String species;
    private int age;

    public Animal() {
    }

    public Animal(String id, String name, String species, int age) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal id(String id) {
        this.id = id;
        return this;
    }

    public Animal name(String name) {
        this.name = name;
        return this;
    }

    public Animal species(String species) {
        this.species = species;
        return this;
    }

    public Animal age(int age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Animal)) {
            return false;
        }
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id) && Objects.equals(name, animal.name)
                && Objects.equals(species, animal.species) && age == animal.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species, age);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", species='" + getSpecies() + "'"
                + ", age='" + getAge() + "'" + "}";
    }

}