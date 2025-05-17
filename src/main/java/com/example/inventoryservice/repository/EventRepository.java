package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface EventRepository extends JpaRepository<Event,Long> {

}
