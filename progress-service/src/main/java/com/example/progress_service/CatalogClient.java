package com.example.progress_service;

import com.example.progress_service.dtos.LessonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service")
public interface CatalogClient {
	@GetMapping("/api/lessons/{id}")
	LessonDto getLessonById(@PathVariable("id") Long id);
}
