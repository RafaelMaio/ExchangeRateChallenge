package com.exchange_rate.exchangeratechallenge.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
public class APIConfig {

    //Template for constructing ExchangeRateLatest class directly
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //Swagger configuration
    @Bean
    public OpenAPI customSchemasOpenAPI() {
        Schema exchangeRate_All_Schema = new Schema<Map<String, Double>>()
                .addProperties("currency", new NumberSchema());

        Schema exchangeRate_CACB_Schema = new Schema<Map<String, Double>>()
                .addProperties("Rate", new NumberSchema());

        Schema valueConversion_CACB_Schema = new Schema<Map<String, Double>>()
                .addProperties("Conversion", new NumberSchema());

        return new OpenAPI()
                .info(new Info()
                        .title("Exchange rate challenge")
                        .description("Currencies exchange rates and conversions")
                        .version("1.0")
                        .license(new License().name("GNU/GPL").url("https://www.gnu.org/licenses/gpl-3.0.html"))
                )
                .components(new Components()
                        .addSchemas("Exchange Rate All", exchangeRate_All_Schema)
                        .addSchemas("Exchange Rate CA-CB" , exchangeRate_CACB_Schema)
                        .addSchemas("Value Conversion CA-CB", valueConversion_CACB_Schema)
                );
    }

    //Support redis on docker
    @Value("${spring.cache.redis.host}")
    private String host;
    @Value("${spring.cache.redis.port}")
    private Integer port;
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        return new JedisConnectionFactory(config);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
