package com.example.inventoryservice.response;


import com.example.inventoryservice.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private Long eventId;
    private String name;
    private Long capacity;
    private Venue venue;
    private BigDecimal ticketPrice;


}
