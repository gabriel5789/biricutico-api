package com.biricutico;

import com.biricutico.drink.database.UnidadeMedida;
import com.biricutico.drink.database.UnidadeMedidaRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BiricuticoApplication {
    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public static void main(String[] args) {
        SpringApplication.run(BiricuticoApplication.class, args);
    }

    @Bean
    public InitializingBean sendDatabase() {
        return () -> {
            Arrays.stream(com.biricutico.drink.entity.UnidadeMedida.values())
                .map(com.biricutico.drink.entity.UnidadeMedida::getStringValue)
                .toList()
                .forEach(un -> {
                    unidadeMedidaRepository.save(new UnidadeMedida(un, null));
                });
        };
    }
}
