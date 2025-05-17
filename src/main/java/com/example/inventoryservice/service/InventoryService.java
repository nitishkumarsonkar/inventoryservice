package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.Event;
import com.example.inventoryservice.entity.Venue;
import com.example.inventoryservice.repository.EventRepository;
import com.example.inventoryservice.repository.VenueRepository;
import com.example.inventoryservice.response.EventInventoryResponse;
import com.example.inventoryservice.response.VenueInventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class InventoryService {

    private final EventRepository eventRepository;

    private final VenueRepository venueRepository;

    public InventoryService(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }



    public List<EventInventoryResponse> getAllEvents() {
        final List<Event> events =eventRepository.findAll();
        return events.stream().map(event ->EventInventoryResponse.builder()
                .name(event.getName())
                .capacity(event.getTotalCapacity())
                .venue(event.getVenue())
                .build()).toList();
    }

    public VenueInventoryResponse getVenueById(Long id) {
        final Venue venue = venueRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Venue not found with id: " + id));
        return VenueInventoryResponse.builder()
                .name(venue.getName())
                .address(venue.getAddress())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }

    public EventInventoryResponse getEventById(Long eventId) {
        final Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event not found with id: " + eventId));
        return EventInventoryResponse.builder()
                .eventId(event.getId())
                .name(event.getName())
                .capacity(event.getTotalCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .build();
    }

    public void updateEventCapacity(Long eventId, Long bookedTicketsCount) {
        final Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event not found with id: " + eventId));
        if (event.getLeftCapacity() < bookedTicketsCount) {
            throw new IllegalArgumentException("Not enough capacity available");
        }
        event.setLeftCapacity(event.getLeftCapacity() - bookedTicketsCount);
        eventRepository.save(event);
        log.info("Event capacity updated successfully for eventId: {}", eventId);

    }
}