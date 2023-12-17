package org.example;

public class Animal {
    private final String name;
    private final String animalType;

    public Animal(String name,  String animalType) {
        this.name = name;
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return "It is a species of " + animalType ;
    }

    public String makeNoise(){
        return "No sound available.";
    };
}

class Mammal extends Animal {
    public Mammal(String name) {
        super(name, "Mammal");
    }

    @Override
    public String makeNoise() {
        return "It happily makes a Roar";
    }
}

class Reptile extends Animal {
    public Reptile(String name) {
        super(name, "Reptile");
    }

    @Override
    public String makeNoise() {
        return "It happily makes a Hiss";
    }
}

class Amphibian extends Animal {
    public Amphibian(String name) {
        super(name,  "Amphibian");
    }

    @Override
    public String makeNoise() {
        return "It happily makes a Croak";
    }
}
