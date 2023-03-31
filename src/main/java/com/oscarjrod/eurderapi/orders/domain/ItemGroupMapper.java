package com.oscarjrod.eurderapi.orders.domain;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ItemGroupMapper {

    // ItemGroup => DTO
    public ItemGroupDto toDTO(ItemGroup myItemGroup) {
        return ItemGroupDto.createItemGroupDto(
                myItemGroup.getId(),
                myItemGroup.getOrder(),
                myItemGroup.getItem(),
                myItemGroup.getAmount(),
                myItemGroup.getShippingDate(),
                myItemGroup.getCurrentPrice()
        );
    }

    // DTO => ItemGroup
    public ItemGroup toItemGroup(@Valid ItemGroupDto itemGroupDto) {
        return ItemGroup.createItemGroup(
                itemGroupDto.getOrder(),
                itemGroupDto.getItem(),
                itemGroupDto.getAmount()
        );
    }

}
