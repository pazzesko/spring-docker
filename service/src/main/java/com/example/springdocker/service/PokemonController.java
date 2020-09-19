package com.example.springdocker.service;

import com.example.springdocker.persistence.PokemonDTO;
import com.example.springdocker.persistence.PokemonModel;
import com.example.springdocker.persistence.PokemonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
public class PokemonController {

    private final PokemonRepository pokemonRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/pokemon/{id}")
    public PokemonModel getPokemonById(@PathVariable Long id) {
        return pokemonRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "The Pokemon with id=" + id + " doesn't exist."));
    }

    @DeleteMapping("/pokemon/{id}")
    public PokemonModel deletePokemonById(@PathVariable Long id) {
        PokemonModel pokemonModel = pokemonRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "The Pokemon with id=" + id + " doesn't exist."));

        pokemonRepository.delete(pokemonModel);
        return pokemonModel;
    }

    @GetMapping("/pokemon/all")
    public List<PokemonModel> all() {
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pokemon/create")
    public PokemonModel create(@RequestBody @Valid PokemonDTO pokemon) {
        PokemonModel pokemonModel = modelMapper.map(pokemon, PokemonModel.class);
        return pokemonRepository.save(pokemonModel);
    }

}
