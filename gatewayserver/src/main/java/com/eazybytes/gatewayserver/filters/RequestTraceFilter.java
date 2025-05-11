package com.eazybytes.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component
//Whenever you want to have this run for all kinds of requests, you need to implement it with GlobalFilter and @Component to be identified by your gateway server
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger= LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    //Mono, ServerWebExchange, GatewayFilterChain are from reactive module
    //Mono means returning single object, Flux - returning collection of objects
    //there can be any number of filters configured, custom, pre-defined filters, so for all of them to run, we return the same thing as we received
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        HttpHeaders headers=exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(headers)){
            logger.debug("eazyBank-Correlation-Id found in Request Trace Filter: {}",filterUtility.getCorrelationId(headers));
        }else{
            String correlationId=generateCorrelationId();
            exchange=filterUtility.setCorrelationId(exchange, correlationId);
            logger.debug("eazyBank-Correlation-Id generated in Request Trace Filter: {}",correlationId);
        }
        return chain.filter(exchange);
    }

    public String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders){
        return filterUtility.getCorrelationId(requestHeaders) != null;
    }
}
