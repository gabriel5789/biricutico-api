package com.biricutico.drink.database;

import com.biricutico.drink.entity.Drink;
import com.biricutico.drink.entity.Ingrediente;
import com.biricutico.drink.entity.Tag;
import com.biricutico.drink.entity.UnidadeMedida;
import com.biricutico.drink.entity.Utensilio;
import com.biricutico.drink.interactor.DrinkSearchDataGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class DrinkDataMapper implements DrinkSearchDataGateway {
    private final DrinkRepository drinkRepository;

    public DrinkDataMapper(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public List<Drink> searchByName(String name) {
        return this.mapAllToEntity(drinkRepository.findByNome(name));
    }

    @Override
    public List<Drink> listAll() {
        return this.mapAllToEntity(drinkRepository.findAll());
    }

    private List<Drink> mapAllToEntity(List<com.biricutico.drink.database.Drink> dbDrinks) {
        List<Drink> appDrinks = new LinkedList<>();

        for (com.biricutico.drink.database.Drink dbDrink : dbDrinks) {
            List<Utensilio> utensilios = new LinkedList<>();
            dbDrink.getUtensilios().forEach(utensilio ->  {
                utensilios.add(
                        Utensilio.of(utensilio.getNome())
                );
            });

            List<Ingrediente> ingredientes = new LinkedList<>();
            dbDrink.getIngredientes().forEach(ingrediente -> {
                ingredientes.add(
                        Ingrediente.of(ingrediente.getNome(), ingrediente.getQuantidade(), UnidadeMedida.valueOf(ingrediente.getUnidadeMedida().getNome()))
                );
            });

            List<Tag> tags = new LinkedList<>();
            dbDrink.getTags().forEach(tag -> {
                tags.add(
                        Tag.of(tag.getChave(), tag.getValor())
                );
            });

            appDrinks.add(
                    Drink.of(
                            dbDrink.getId(),
                            dbDrink.getNome(),
                            dbDrink.getFoto(),
                            dbDrink.getDescricao(),
                            dbDrink.getInstrucoesPreparo(),
                            utensilios,
                            ingredientes,
                            tags,
                            dbDrink.getTeorAlcoolico(),
                            dbDrink.getTaca()
                    )
            );
        } /* for (Drink dbDrink : dbDrinks) */

        return appDrinks;
    }
}
