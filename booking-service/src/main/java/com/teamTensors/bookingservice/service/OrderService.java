package com.teamTensors.bookingservice.service;

import com.teamTensors.bookingservice.dto.InventoryResponse;
import com.teamTensors.bookingservice.dto.OrderLineItemsDto;
import com.teamTensors.bookingservice.dto.OrderRequest;
import com.teamTensors.bookingservice.model.Order;
import com.teamTensors.bookingservice.model.OrderLineItems;
import com.teamTensors.bookingservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest)  {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // to get a list of codes
//        List<String> skuCodes = order.getOrderLineItemsList().stream()
//                .map(orderLineItem -> orderLineItem.getSkuCode())
//                .toList();

        // call inventory service, and place order if product is in stock
        InventoryResponse [] inventoryResponsesArray = webClientBuilder.build().get()
                        .uri("http://tripShedule-service/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                                .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                .block(); // make Synchronized request

        boolean allProductsInStocks = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock);

        if(allProductsInStocks) {
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock, Please try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
