package com.exercise.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "transforms" })
@Generated("jsonschema2pojo")
public class FeatureConfig {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("transforms")
	private List<Transform> transforms = new ArrayList<>();
	
	@JsonIgnore
	@JsonProperty("json")
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("transforms")
	public List<Transform> getTransforms() {
		return transforms;
	}

	@JsonProperty("transforms")
	public void setTransforms(List<Transform> transforms) {
		this.transforms = transforms;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@Override
	public String toString() {
		return "FeatureConfig [id=" + id + ", name=" + name + ", transforms=" + transforms + ", additionalProperties="
				+ additionalProperties + ", json=" + additionalProperties + "]";
	}
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}