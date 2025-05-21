package com.example.inventoryservice.controller;

import com.example.inventoryservice.response.EventInventoryResponse;
import com.example.inventoryservice.response.VenueInventoryResponse;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventory/event")
    public @ResponseBody List<EventInventoryResponse> getInventory() {
        return inventoryService.getAllEvents();
    }

    //venue information
    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueInventoryResponse getVenueById(@PathVariable Long venueId) {
         return inventoryService.getVenueById(venueId);
    }

    // get event by id
    @GetMapping("/inventory/event/{eventId}")
    public @ResponseBody EventInventoryResponse getEventById(@PathVariable Long eventId) {
        return inventoryService.getEventById(eventId);
    }

    // update inventory capacity
    @PutMapping("/inventory/event/{eventId}/capacity")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable Long eventId, @RequestParam Long bookedTicketsCount) {
        inventoryService.updateEventCapacity(eventId, bookedTicketsCount);
        return ResponseEntity.ok().build();
    }

}
