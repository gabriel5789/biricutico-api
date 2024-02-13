package com.biricutico.drink.controller;

import com.biricutico.drink.database.DrinkDataMapper;
import com.biricutico.drink.database.DrinkRepository;
import com.biricutico.drink.interactor.*;
import com.biricutico.drink.presentation.DrinkRegisterPresenter;
import com.biricutico.drink.presentation.DrinkSearchPresenter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/drinks")
public class DrinkAPIController {
    /* Use cases */
    private DrinkSearchInputPort searchInputPort;
    private DrinkRegisterInputPort registerInputPort;

    @Autowired
    private DrinkRepository drinkRepository;

    @GetMapping
    public DrinkSearchResponse searchDrinks(@RequestParam(name = "dataOption") String dataOption,
                                            @RequestParam(name = "page") Integer page,
                                            @RequestParam(name = "pageSize") Integer pageSize,
                                            @RequestParam Map<String, String> filterParams) {
        filterParams.remove("dataOption");
        filterParams.remove("page");
        filterParams.remove("pageSize");

        List<DrinkSearchRequest.FilterOption> filterOptions = new LinkedList<>();

        filterParams.forEach((key, value) -> {
            DrinkSearchRequest.FilterOption filter = new DrinkSearchRequest.FilterOption();
            try {
                filter.setField(key.split("\\.")[0]);
                filter.setOperation(key.split("\\.")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                filter.setField(key);
                filter.setOperation("eq");
            }
            filter.setValue(value);
            filterOptions.add(filter);
        });

        DrinkSearchRequest req = new DrinkSearchRequest();
        req.setPage(page);
        req.setPageSize(pageSize);
        req.setDataOption(dataOption);
        req.setFilterOptions(filterOptions);
        return searchInputPort.searchDrinks(req);
    }

    @PostMapping
    public DrinkRegisterResponse saveDrink(@RequestBody DrinkRegisterRequest request) {
        return registerInputPort.saveDrink(request);
    }

    @PostConstruct
    public void init() {
        /* Inicialização de componentes */
        if (searchInputPort == null) {
            searchInputPort = new DrinkSearchUseCase(new DrinkSearchPresenter(), new DrinkDataMapper(drinkRepository));
        }
        if (registerInputPort == null) {
            registerInputPort = new DrinkRegisterUseCase(new DrinkRegisterPresenter(), new DrinkDataMapper(drinkRepository));
        }
    }
}
