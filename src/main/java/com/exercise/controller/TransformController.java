package com.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.exercise.model.ErrorResponse;
import com.exercise.model.FeatureConfig;
import com.exercise.model.TransformException;
import com.exercise.service.TransformService;

@RestController
@RequestMapping(value = "/api/transform")
/*
 * REST API to retrieve ML features from a given JSON object using a supplied feature configuration.
 */
public class TransformController {
	public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	TransformService transformService;

	@PostMapping(path = "/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody(required = true) FeatureConfig config) {
		try {
			return ResponseEntity.ok(transformService.transformJson(config));
		}catch(TransformException e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
			return new ResponseEntity<>(gson.toJson(errorResponse), HttpStatus.BAD_REQUEST);
		}
		
	}
}
