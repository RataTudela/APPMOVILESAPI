package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.OrdenItem;

public interface OrdenItemRepository extends JpaRepository<OrdenItem, Long> {}
