package ru.pln.orders.service.restexchange;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.pln.orders.models.ProductDTO;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FeignExchange implements IRestExchange{
    private final IFeignExchange feignExchange;

    public FeignExchange(IFeignExchange feignExchange) {
        this.feignExchange = feignExchange;
    }

    @Override
    public List<ProductDTO> exchange(Collection<Integer> ids) {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return feignExchange.exchange().getBody().stream()
                .map(p -> mapper.convertValue(p, ProductDTO.class))
                .peek(p -> {
                    p.setProductId(p.getId());
                    p.setId(null);
                }).collect(toList());
    }
}
