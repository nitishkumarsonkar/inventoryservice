package com.example.inventoryservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueInventoryResponse {
    private Long id;
    private String name;
    private String address;
    private Long totalCapacity;

}
