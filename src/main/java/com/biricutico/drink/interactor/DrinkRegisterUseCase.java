package com.biricutico.drink.interactor;

import com.biricutico.drink.entity.*;

import java.util.LinkedList;
import java.util.List;

public class DrinkRegisterUseCase implements DrinkRegisterInputPort {
    private final DrinkRegisterOutputPort outputPort;
    private final DrinkRegisterDataGateway dataGateway;

    @Override
    public DrinkRegisterResponse saveDrink(DrinkRegisterRequest request) {
        /* Validar input */
        Drink drink;
        List<Ingrediente> ingredientes = new LinkedList<>();
        List<Tag> tags = new LinkedList<>();
        List<Utensilio> utensilios = new LinkedList<>();

        try {
            if (request.getTags() != null) {
                request.getTags().forEach((key, value) -> {
                    tags.add(Tag.of(key, value));
                });
            }

            if (request.getUtensilios() != null) {
                request.getUtensilios().forEach(utensilio -> {
                    utensilios.add(Utensilio.of(utensilio));
                });
            }

            if (request.getIngredientes() != null) {
                request.getIngredientes().forEach(ingrediente -> {
                    ingredientes.add(
                            Ingrediente.of(
                                    ingrediente.getNome(),
                                    ingrediente.getQuantidade(),
                                    UnidadeMedida.enumValueOf(ingrediente.getUnidadeMedida())));
                });
            }

            drink = Drink.of(
                    null, request.getNome(), request.getFoto(), request.getDescricao(), request.getInstrucoesPreparo(),
                    utensilios, ingredientes, tags, request.getTeorAlcoolico(), request.getTaca());
        } catch (InvalidDomainObjectError e) {
            return outputPort.presentInvalidDomainObjectError(e);
        }

        /* TODO: Converter String base64 da imagem para byteArray */

        try {
            drink = dataGateway.saveDrink(drink);
        } catch (DataGatewayPersistenceError e) {
            return outputPort.presentDataGatewayPersistenceError(e);
        }

        /* TODO: Salvar imagem no S3 da AWS com nome do ID do drink */

        /* Salvar link da imagem na AWS no banco */
        try {
            drink = dataGateway.saveDrink(drink);
        } catch (DataGatewayPersistenceError e) {
            return outputPort.presentDataGatewayPersistenceError(e);
        }

        return outputPort.presentResponse(drink);
    }

    public DrinkRegisterUseCase(DrinkRegisterOutputPort outputPort, DrinkRegisterDataGateway dataGateway) {
        this.outputPort = outputPort;
        this.dataGateway = dataGateway;
    }
}
