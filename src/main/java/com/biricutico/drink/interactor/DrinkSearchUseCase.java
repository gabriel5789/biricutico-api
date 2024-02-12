package com.biricutico.drink.interactor;

import com.biricutico.drink.entity.Drink;

import java.util.List;

public class DrinkSearchUseCase implements DrinkSearchInputPort {
    DrinkSearchOutputPort outputPort;
    DrinkSearchDataGateway dataGateway;

    @Override
    public DrinkSearchResponse searchDrinks(DrinkSearchRequest searchCriteria) {
        /* Validate input */
        if (searchCriteria == null || searchCriteria.getFilterOptions() == null)
            return outputPort.presentMissingSearchParametersError();

        List<Drink> results = null;

        /* Process filters */
        if (searchCriteria.getFilterOptions().isEmpty()) {
            return outputPort.presentResults(dataGateway.listAll());
        }

        for (DrinkSearchRequest.FilterOption filterOption : searchCriteria.getFilterOptions()) {
            if (filterOption.getField().equals("nome_drink")) {
                results = dataGateway.searchByName(filterOption.getValue());
            }
        }

        return outputPort.presentResults(results);
    }

    public DrinkSearchUseCase(DrinkSearchOutputPort outputPort, DrinkSearchDataGateway dataGateway) {
        this.outputPort = outputPort;
        this.dataGateway = dataGateway;
    }
}
