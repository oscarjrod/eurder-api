package com.oscarjrod.eurderapi.items;

import com.oscarjrod.eurderapi.items.domain.Item;
import com.oscarjrod.eurderapi.items.domain.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ItemIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    @Test
    void givenAnItem_whenAddingItemToController_thenRepositoryContainsThatItem() {
        //GIVEN
        ItemDto itemDto = ItemDto.createItemDto(
                Item.createItem("Nintendo Switch", "4K OLED Model", new BigDecimal("500.0"), 10)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("developer", "password");

        HttpEntity<ItemDto> request = new HttpEntity<>(itemDto, headers);

        //WHEN
        ResponseEntity<Void> response = restTemplate.exchange(
                "/items",
                HttpMethod.POST,
                request,
                Void.class
        );

        //Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
