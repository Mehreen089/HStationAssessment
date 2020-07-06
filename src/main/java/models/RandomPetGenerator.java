package models;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomPetGenerator {

    private AddPetBody randomPetBody;
    Faker fakerObject = new Faker();

    public AddPetBody generateRandomPet() {
        randomPetBody = new AddPetBody(
                generateRandomId(),
                generateRandomName(),
                generateCategory(),
                generateTags(),
                generateRandomStatus());
        return randomPetBody;
    }

    private String generateRandomName() {
        return fakerObject.name().firstName();
    }

    private String generateRandomStatus() {
        List<String> statuses = Arrays.asList("available", "pending", "sold");
        Random rand = new Random();
        return statuses.get(rand.nextInt(statuses.size()));
    }
    private String generateRandomId() {
        Random rand = new Random();
        return Integer.toString(rand.nextInt((999999 - 2) + 1) + 2);
    }
    private Category generateCategory() {
        Category cat = new Category();
        cat.setId("1");
        cat.setName("cat1");
        return cat;
    }
    private Tags[] generateTags() {
        Tags tag = new Tags();
        tag.setId("1");
        tag.setName("tag1");
        return new Tags[]{ tag };
    }
}
