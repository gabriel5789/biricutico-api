package com.biricutico.drink.database;

import com.biricutico.drink.entity.Drink;
import com.biricutico.drink.entity.Ingrediente;
import com.biricutico.drink.entity.Tag;
import com.biricutico.drink.entity.UnidadeMedida;
import com.biricutico.drink.entity.Utensilio;
import com.biricutico.drink.interactor.DataGatewayAccessError;
import com.biricutico.drink.interactor.DataGatewayPersistenceError;
import com.biricutico.drink.interactor.DrinkRegisterDataGateway;
import com.biricutico.drink.interactor.DrinkSearchDataGateway;
import org.springframework.dao.DataAccessException;

import java.util.LinkedList;
import java.util.List;

public class DrinkDataMapper implements DrinkSearchDataGateway, DrinkRegisterDataGateway {
    private final DrinkRepository drinkRepository;

    public DrinkDataMapper(DrinkRepository drinkRepository) {
        try {
            this.drinkRepository = drinkRepository;
        } catch (DataAccessException e) {
            throw new DataGatewayAccessError();
        }
    }

    @Override
    public List<Drink> searchByName(String name) {
        try {
            return drinkRepository.findByNomeContainingIgnoreCase(name).stream().map(this::mapToEntity).toList();
        } catch (DataAccessException e) {
            throw new DataGatewayAccessError();
        }
    }

    @Override
    public List<Drink> listAll() {
        try {
            return drinkRepository.findAll().stream().map(this::mapToEntity).toList();
        } catch (DataAccessException e) {
            throw new DataGatewayAccessError();
        }
    }

    @Override
    public Drink saveDrink(Drink drink) {
        try {
            return this.mapToEntity(drinkRepository.saveAndFlush(this.mapToDatabase(drink)));
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataGatewayPersistenceError();
        }
    }

    private Drink mapToEntity(com.biricutico.drink.database.Drink dbDrink) {
        List<Utensilio> utensilios = new LinkedList<>();
        dbDrink.getUtensilios().forEach(utensilio -> {
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

        return Drink.of(
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
        );
    }

    private com.biricutico.drink.database.Drink mapToDatabase(Drink entityDrink) {
        com.biricutico.drink.database.Drink dbDrink = new com.biricutico.drink.database.Drink(
                entityDrink.getId(), entityDrink.getNome(), entityDrink.getFoto(), entityDrink.getDescricao(),
                entityDrink.getInstrucoesPreparo(), entityDrink.getTaca(), entityDrink.getTeorAlcoolico(),
                null, null, null
        );

        List<com.biricutico.drink.database.Tag> tags = new LinkedList<>();
        entityDrink.getTags().forEach(tag -> {
            tags.add(new com.biricutico.drink.database.Tag(tag.getKey(), tag.getValue(), dbDrink));
        });

        List<com.biricutico.drink.database.Utensilio> utensilios = new LinkedList<>();
        entityDrink.getUtensilios().forEach(utensilio -> {
            utensilios.add(new com.biricutico.drink.database.Utensilio(utensilio.getNome(), dbDrink));
        });

        List<com.biricutico.drink.database.Ingrediente> ingredientes = new LinkedList<>();
        entityDrink.getIngredientes().forEach(ingrediente -> {
            ingredientes.add(new com.biricutico.drink.database.Ingrediente(
                    ingrediente.getNome(),
                    ingrediente.getQuantidade(),
                    new com.biricutico.drink.database.UnidadeMedida(UnidadeMedida.getStringValue(
                            ingrediente.getUnidadeMedida()), null),
                    dbDrink
            ));
        });

        dbDrink.setIngredientes(ingredientes);
        dbDrink.setUtensilios(utensilios);
        dbDrink.setTags(tags);

        return dbDrink;
    }
}
