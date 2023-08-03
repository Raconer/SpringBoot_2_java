package com.spring.java.core.utils;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class FakerUtil {
    public static Faker faker = new Faker();

    public static int getPrice(int min, int max){
        return faker.number().numberBetween(min, max);
    }

    public static LocalDate getPast(){
        String dateString =  faker.date().past(10, TimeUnit.DAYS, "YYYY-MM-dd");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

}
