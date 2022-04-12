package cesi.api.formationapi.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FirstApiController {

    @GetMapping("/all")
    public List<String> getAll() {
        return Arrays.asList("toto", "tata", "titi");
    }

    @GetMapping("/one/{id}")
    public String getOne(@PathVariable int id) {
        return String.valueOf(id);
    }

    @PostMapping("/post")
    public boolean post(@RequestBody Object postData) {

        return true;
    }
}
