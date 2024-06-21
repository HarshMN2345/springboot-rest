package com.harshmahajan.Rest.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersonControlPerson {
    @GetMapping("v1/person")
    public PersonV1 getFirstVersion() {
        return new PersonV1("Bob Marley");
    }
    @GetMapping("v2/person")
    public PersonV2 getSecondVersion() {
        return new PersonV2(new Name1("Bob","marley"));
    }
    @GetMapping(path="person",params="v1")
    public PersonV1 getFirstVersionRequestParam() {
        return new PersonV1("Bob Marley");
    }
    @GetMapping(path="person",params="v2")
    public PersonV2 getSecondVersionRequestParam() {
        return new PersonV2(new Name1("Bob","marley"));
    }
}
