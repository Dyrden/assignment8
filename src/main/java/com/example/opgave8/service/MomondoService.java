package com.example.opgave8.service;


import com.example.opgave8.dto.Age;
import com.example.opgave8.dto.Gender;
import com.example.opgave8.dto.MonondoResponse;
import com.example.opgave8.dto.Nationality;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MomondoService {



    Mono<Gender> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<Gender> gender = client.get()
            .uri("https://api.genderize.io?name="+name)
            .retrieve()
            .bodyToMono(Gender.class);
        return gender;
    }
    Mono<Nationality> getNationsForName(String name) {
        WebClient client = WebClient.create();
        Mono<Nationality> nations = client.get()
            .uri("https://api.nationalize.io?name="+name)
            .retrieve()
            .bodyToMono(Nationality.class);
        return nations;
    }
    Mono<Age> getAgeForName(String name) {
        WebClient client = WebClient.create();
        Mono<Age> age = client.get()
            .uri("https://api.agify.io?name="+name)
            .retrieve()
            .bodyToMono(Age.class);
        return age;
    }



    public MonondoResponse nameInfo(String name) {

        Mono<Gender> gender = getGenderForName(name);
        Mono<Nationality> nation = getNationsForName(name);
        Mono<Age> age = getAgeForName(name);

        Mono<MonondoResponse> rs = Mono.zip(gender,nation,age).map(t-> {
            MonondoResponse monondoResponse = new MonondoResponse();

            monondoResponse.setName(t.getT1().getName());
            monondoResponse.setGender(t.getT1().getGender());
            monondoResponse.setCountry(t.getT2().getCountry().get(0).getCountry_id());

            monondoResponse.setGenderProbability(t.getT1().getProbability());
            monondoResponse.setAge(t.getT3().getAge());
            monondoResponse.setAgeCount(t.getT3().getCount());
            monondoResponse.setCountryProbability(t.getT2().getCountry().get(0).getProbability());
            return monondoResponse;
        });

    return rs.block();
    }



}
