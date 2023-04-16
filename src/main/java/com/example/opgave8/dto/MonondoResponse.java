package com.example.opgave8.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonondoResponse {

    String name;
    String gender;
    Double genderProbability;
    Integer age;
    Integer ageCount;
    String country;
    Double countryProbability;


    public Double getCountryProbability() {
        return countryProbability * 100;
    }

    public Double getGenderProbability() {
        return genderProbability * 100;
    }
}
