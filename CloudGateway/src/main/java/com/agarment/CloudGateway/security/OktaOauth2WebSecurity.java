package com.agarment.CloudGateway.security;

/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Minimal explicit Security configuration.
 *
 * @author Rob Winch
 * @since 5.0
 */
@Configuration
@EnableWebFluxSecurity
public class OktaOauth2WebSecurity {
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/v1/order/**").hasAuthority("Admin")
//                        .requestMatchers("/v1/order/**").hasAuthority("Customer")
//                        .requestMatchers("/v1/product/**").hasAuthority("Admin")
//                        .requestMatchers("/v1/product/**").hasAuthority("Customer")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(withDefaults()))
                .httpBasic(withDefaults())
                .build();
    }

//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        // @formatter:off
//        http
//                .authorizeExchange((authorize) -> authorize
//                        .pathMatchers(HttpMethod.GET, "/v1/**").hasAuthority("Admin")
//                        .pathMatchers(HttpMethod.POST, "/v1/**").hasAuthority("Admin")
//                        .anyExchange().authenticated()
//                )
//                .oauth2ResourceServer((resourceServer) -> resourceServer
//                        .jwt(withDefaults())
//                );
//        // @formatter:on
//        return http.build();
//    }
}