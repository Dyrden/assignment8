package com.example.opgave8.api;


import com.example.opgave8.dto.MonondoResponse;
import com.example.opgave8.service.MomondoService;
import org.springframework.web.bind.annotation.*;

@RestController()
public class MomondoController {

    MomondoService service;

    public MomondoController(MomondoService service) {
        this.service = service;
    }

    @GetMapping(value="name-info")
    public MonondoResponse nameInfo(@RequestParam String name) {
        return service.nameInfo(name);
    }

}
