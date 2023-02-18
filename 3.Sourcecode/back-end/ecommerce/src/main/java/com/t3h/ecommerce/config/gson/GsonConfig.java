package com.t3h.ecommerce.config.gson;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class GsonConfig {


    private final Gson gson;

    public GsonConfig(Gson gson) {
        this.gson = gson;
    }
}
