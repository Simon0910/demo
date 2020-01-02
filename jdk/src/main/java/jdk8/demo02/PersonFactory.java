package jdk8.demo02;

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}