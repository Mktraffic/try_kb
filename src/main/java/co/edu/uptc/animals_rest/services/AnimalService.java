package co.edu.uptc.animals_rest.services;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.uptc.animals_rest.exception.InvalidRangeException;
import co.edu.uptc.animals_rest.models.Animal;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
@Service
public class AnimalService {
     private static final Logger logger = LoggerFactory.getLogger(AnimalService.class);
    @Value("${animal.file.path}")
    private String filePath;

    
//    public List<Animal> getAnimalInRange(int from, int to) throws IOException {
//        List<String> listAnimal = Files.readAllLines(Paths.get(filePath));
//        List<Animal> animales = new ArrayList<>();
//        
//        if (from < 0 || to >= listAnimal.size() || from > to) {
//            logger.warn("Invalid range: Please check the provided indices. Range: 0 to {}",listAnimal.size());
//             throw new InvalidRangeException("Invalid range: Please check the provided indices.");
//        }
//
//        for (String line : listAnimal) {
//            String[] parts = line.split(",");
//            if (parts.length == 2) {
//                String categoria = parts[0].trim();
//                String nombre = parts[1].trim();                
//                animales.add(new Animal(nombre, categoria));
//            }
//        }
//    
//        return animales.subList(from, to + 1);
//    }
//
//    public List<Animal> getAnimalAll() throws IOException {
//        List<String> listAnimal = Files.readAllLines(Paths.get(filePath));
//        List<Animal> animales = new ArrayList<>();
//        
//
//        for (String line : listAnimal) {
//            String[] parts = line.split(",");
//            if (parts.length == 2) {
//                String category = parts[0].trim();
//                String name = parts[1].trim();                
//                animales.add(new Animal(name, category));
//            }
//        }
//    
//        return animales;
//    }

    public List<Animal> getAnimalsByCategory(String category) throws IOException {
        List<String> listAnimal = Files.readAllLines(Paths.get(filePath));
        List<Animal> filteredAnimals = new ArrayList<>();

        for (String line : listAnimal) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String animalCategory = parts[0].trim();
                String animalName = parts[1].trim();
                if (animalCategory.equalsIgnoreCase(category)) {
                    filteredAnimals.add(new Animal(animalName, animalCategory));
                }
            }
        }

        return filteredAnimals;
    }

    public List<Animal> getAnimalsByNameLength(int length) throws IOException {
        List<String> listAnimal = Files.readAllLines(Paths.get(filePath));
        List<Animal> filteredAnimals = new ArrayList<>();

        for (String line : listAnimal) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String animalCategory = parts[0].trim();
                String animalName = parts[1].trim();
                if (animalName.length() < length) {
                    filteredAnimals.add(new Animal(animalName, animalCategory));
                }
            }
        }

        return filteredAnimals;
    }
    
    /*
    public List<Object> getAnimalInRange(int from, int to) throws IOException {
        List<String> listAnimal = Files.readAllLines(Paths.get(filePath));
        List<Animal> animales = new ArrayList<>();

        if (from < 0 || to >= listAnimal.size() || from > to) {
            logger.warn("Invalid range: Please check the provided indices. Range: 0 to {}",listAnimal.size());
             throw new InvalidRangeException("Invalid range: Please check the provided indices.");
        }

        for (String line : listAnimal) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String categoria = parts[0].trim();
                String nombre = parts[1].trim();
                animales.add(new Animal(nombre, categoria));
            }
        }

        List<Animal> animalesAux=animales.subList(from, to + 1);
        List<Object> response = new ArrayList<>();
        response.add("containerName: " + InetAddress.getLocalHost().getHostName());
        Date currentDate = new Date();
        response.add("fecha y hora (con milisegundos): " + currentDate + " (" + System.currentTimeMillis() + " ms)");
        response.addAll(animalesAux);

        return response;

    } */

    public List<Object> getAnimalInRange(int from, int to) throws IOException {
        List<String> listAnimal = Files.readAllLines(Paths.get(filePath));
        List<Animal> animales = new ArrayList<>();

        if (from < 0 || to >= listAnimal.size() || from > to) {
            logger.warn("Invalid range: Please check the provided indices. Range: 0 to {}", listAnimal.size());
            throw new InvalidRangeException("Invalid range: Please check the provided indices.");
        }

        for (String line : listAnimal) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String categoria = parts[0].trim();
                String nombre = parts[1].trim();
                animales.add(new Animal(nombre, categoria));
            }
        }

        List<Animal> animalesAux = animales.subList(from, to + 1);
        List<Object> response = new ArrayList<>();
        response.add("containerName: " + InetAddress.getLocalHost().getHostName());
        Date currentDate = new Date();
        response.add("fecha y hora (con milisegundos): " + currentDate + " (" + System.currentTimeMillis() + " ms)");
        response.addAll(animalesAux);

        return response;
    }

    public List<Object> getAnimalAll() throws IOException {
        List<String> listAnimal = Files.readAllLines(Paths.get(filePath));
        List<Animal> animales = new ArrayList<>();


        for (String line : listAnimal) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String category = parts[0].trim();
                String name = parts[1].trim();
                animales.add(new Animal(name, category));
            }
        }

        List<Object> response = new ArrayList<>();
        response.add("containerName: " + InetAddress.getLocalHost().getHostName());
        Date currentDate = new Date();
        response.add("fecha y hora (con milisegundos): " + currentDate + " (" + System.currentTimeMillis() + " ms)");
        response.addAll(animales);

        return response;


    }
}

