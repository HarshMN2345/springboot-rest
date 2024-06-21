package com.harshmahajan.Rest.Versioning;

public class PersonV2 {
    private Name1 name;

    public PersonV2(Name1 name) {
        this.name = name;
    }

    public Name1 getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV2{" +
                "name=" + name +
                '}';
    }
}
