package com.example.springdocker;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@RestController
@RequestMapping("/spring-docker")
public class PokemonController {

    private final PokemonRepository pokemonRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/pokemon/{id}")
    public PokemonModel pokemonById(@PathVariable Long id) {
        return pokemonRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "The Pokemon with id=" + id + " doesn't exist."));
    }

    @GetMapping("/pokemon/all")
    Iterable<PokemonModel> all() {
        return pokemonRepository.findAll();
    }

    @GetMapping("/pokemon/random")
    public PokemonModel random() {
        int totalPokemons = (int) pokemonRepository.count();
        if (totalPokemons == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no pokemons.");
        } else {
            return pokemonRepository.findAll().get(new Random().nextInt(totalPokemons));
        }
    }

    @PostMapping("/pokemon/save")
    public PokemonModel save(@RequestBody PokemonDTO pokemon) {
        PokemonModel pokemonModel = modelMapper.map(pokemon, PokemonModel.class);
        return pokemonRepository.save(pokemonModel);
    }

}
