package com.harshmahajan.Rest.Versioning;

public class PersonV1 {
    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }

    private String name;

    public String getName() {
        return name;
    }

    public PersonV1(String name) {
       this.name = name;
   }
}
