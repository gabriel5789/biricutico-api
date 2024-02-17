package com.biricutico.drink.interactor;

import com.biricutico.drink.entity.Drink;

import java.util.List;

public class DrinkSearchUseCase implements DrinkSearchInputPort {
    private final DrinkSearchOutputPort outputPort;
    private final DrinkSearchDataGateway dataGateway;

    @Override
    public DrinkSearchResponse searchDrinks(DrinkSearchRequest searchCriteria) {
        /* Validar input */
        if (searchCriteria == null || searchCriteria.getFilterOptions() == null)
            return outputPort.presentMissingSearchParametersError();

        List<Drink> results = null;

        if (searchCriteria.getFilterOptions().isEmpty()) {
            return outputPort.presentResults(dataGateway.listAll());
        }

        for (DrinkSearchRequest.FilterOption filterOption : searchCriteria.getFilterOptions()) {
            if (filterOption.getField().equals("nome")) {
                try {
                    results = dataGateway.searchByName(String.valueOf(filterOption.getValue()));
                } catch (DataGatewayAccessError e) {
                    return outputPort.presentDataAccessError();
                }
            }
        }

        return outputPort.presentResults(results);
    }

    public DrinkSearchUseCase(DrinkSearchOutputPort outputPort, DrinkSearchDataGateway dataGateway) {
        this.outputPort = outputPort;
        this.dataGateway = dataGateway;
    }
}
