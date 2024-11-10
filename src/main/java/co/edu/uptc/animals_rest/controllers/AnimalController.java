package co.edu.uptc.animals_rest.controllers;

import java.io.IOException;
import java.util.List;

import co.edu.uptc.animals_rest.exception.InvalidRangeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uptc.animals_rest.models.Animal;
import co.edu.uptc.animals_rest.services.AnimalService;

import static java.lang.Thread.sleep;


@RestController
@RequestMapping("/animal")
public class AnimalController {

 private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);

   @Autowired
    private AnimalService animalService;


//    @GetMapping("/all")
//    public List<Animal> getAnimalAll() throws IOException {
//        logger.info("getAnimalAll called");
//        return animalService.getAnimalAll();
//    }
//
//    @GetMapping("/range")
//    public List<Animal> getAnimal(@RequestParam int from, @RequestParam int to) throws IOException {
//        logger.info("getAnimal called with parameters: from = {}, to = {}", from, to);
//        return animalService.getAnimalInRange(from, to);
//    }
   
   @GetMapping("/all")
   public List<Object> getAnimalAll() throws IOException, InterruptedException {
       logger.info("getAnimalAll called");
       try {
           Thread.sleep(5000);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       return animalService.getAnimalAll();
   }

   /*@GetMapping("/range")
   public List<Object> getAnimal(@RequestParam int from, @RequestParam int to) throws IOException, InterruptedException {
       logger.info("getAnimal called with parameters: from = {}, to = {}", from, to);
       try {
           Thread.sleep(5000);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       return animalService.getAnimalInRange(from, to);
   }*/

    @GetMapping("/range")
    public List<Object> getAnimal(@RequestParam String from, @RequestParam String to) throws IOException, InterruptedException {
        logger.info("getAnimal called with parameters: from = {}, to = {}", from, to);
        try {
            int fromIndex = Integer.parseInt(from);
            int toIndex = Integer.parseInt(to);

            if (fromIndex < 0 || toIndex < 0) {
                logger.warn("Range values must be non-negative. Provided: from = {}, to = {}", from, to);
                throw new InvalidRangeException("Range values must be non-negative.");
            }

            return animalService.getAnimalInRange(fromIndex, toIndex);
        } catch (NumberFormatException e) {
            logger.error("Invalid input: from or to is not a number. from = {}, to = {}", from, to, e);
            throw new InvalidRangeException("Invalid input: from and to must be numbers.");
        } catch (InvalidRangeException e) {
            logger.error("Invalid range provided: from = {}, to = {}", from, to, e);
            throw e;
        }
    }

//    @GetMapping("/category/{category}")
//    public List<Animal> getAnimalsByCategory(@PathVariable String category) throws IOException {
//        logger.info("getAnimalsByCategory called with category: {}", category);
//        return animalService.getAnimalsByCategory(category);
//    }
//
//    @GetMapping("/name-length/{numberOfLetters}")
//    public List<Animal> getAnimalsByNameLength(@PathVariable int numberOfLetters) throws IOException {
//        logger.info("getAnimalsByNameLength called with length: {}", numberOfLetters);
//        return animalService.getAnimalsByNameLength(numberOfLetters);
//    }



}
