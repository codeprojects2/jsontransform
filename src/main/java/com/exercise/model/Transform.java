package com.exercise.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "useInML", "enabled", "jsltExpression" })
@Generated("jsonschema2pojo")
public class Transform {


	@JsonProperty("name")
	private String name;
	@JsonProperty("useInML")
	private Boolean useInML;
	@JsonProperty("enabled")
	private boolean enabled;
	@JsonProperty("jsltExpression")
	private String jsltExpression;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("useInML")
	public Boolean getUseInML() {
		return useInML;
	}

	@JsonProperty("useInML")
	public void setUseInML(Boolean useInML) {
		this.useInML = useInML;
	}

	@JsonProperty("enabled")
	public boolean getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("jsltExpression")
	public String getJsltExpression() {
		return jsltExpression;
	}

	@JsonProperty("jsltExpression")
	public void setJsltExpression(String jsltExpression) {
		this.jsltExpression = jsltExpression;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	

	@Override
	public String toString() {
		return "Transform [name=" + name + ", useInML=" + useInML + ", enabled=" + enabled + ", jsltExpression="
				+ jsltExpression  + ", additionalProperties=" + additionalProperties + "]";
	}

}