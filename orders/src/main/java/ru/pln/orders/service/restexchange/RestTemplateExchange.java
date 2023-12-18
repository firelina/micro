package ru.pln.orders.service.restexchange;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.pln.orders.models.ProductDTO;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RestTemplateExchange implements IRestExchange{
    @Override
    public List<ProductDTO> exchange(Collection<Integer> ids) {
        String baseUrl = "http://localhost:8000/products/products";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Collection<Integer>> request = new HttpEntity<>(ids);
        ResponseEntity<List> response = null;
        try {

            // вызов сервисы
            response = restTemplate.exchange(baseUrl, HttpMethod.GET, request, List.class);

            if (response.getStatusCode() == HttpStatus.OK) { // если статус был 200
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                List<LinkedHashMap> result = response.getBody();
//                return result.stream().map(p -> ProductDTO.builder().productId((Integer) p.get("id")).title((String) p.get("title")).build()).collect(toList());
                return result.stream()
                        .map(p -> mapper.convertValue(p, ProductDTO.class))
                        .peek(p -> {
                    p.setProductId(p.getId());
                    p.setId(null);
                }).collect(toList());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
