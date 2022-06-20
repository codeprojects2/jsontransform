package com.exercise.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.exercise.model.FeatureConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class TransformServiceImplTest {
	
	TransformServiceImpl transform = new TransformServiceImpl();
	private static ObjectMapper mapper = new ObjectMapper();
	String disabled =  " \"enabled\": false,\n";
	String enabled =  " \"enabled\": true,\n";
	String part1 = 
					"{\n"
					+ "  \"id\": 1,\n"
					+ "  \"name\": \"DeviceFeatures\",\n"
					+ "  \"transforms\": [\n"
					+ "    {\n"
					+ "      \"name\": \"device_os\",\n"
					+ "      \"useInML\": true,\n"
					+ "      \"enabled\": true,\n"
					+ "      \"jsltExpression\": \".device.osType\"\n"
					+ "    },\n"
					+ "    {\n"
					+ "      \"name\": \"device_description\",\n"
					+ "      \"useInML\": true,\n";
					
	String part2 = 	 "      \"jsltExpression\": \".device.osType + \\\" \\\" + .device.model\"\n"
					+ "    }\n"
					+ "  ],\n"
					+ "  \"json\": {\n"
					+ "    \"eventId\": \"878237843\",\n"
					+ "    \"device\": {\n"
					+ "      \"osType\": \"Linux\",\n"
					+ "      \"model\": \"Laptop\"\n"
					+ "    },\n"
					+ "    \"ip\": \"10.45.2.30\",\n"
					+ "    \"sessionId\": \"ads79uoijd098098\"\n"
					+ "  }\n"
					+ "}";
	
	
    @Test
	void testTransformDisabled() throws JsonMappingException, JsonProcessingException {
    	String expected = "{\"eventId\":\"878237843\",\"device_os\":\"Linux\"}";
    	FeatureConfig featureConfig = mapper.readValue(part1+disabled+part2, FeatureConfig.class);
    	transform.transformJson(featureConfig);
    	assertEquals( expected, transform.transformJson(featureConfig));

	}
    
    @Test
   	void testTransformEnabled() throws JsonMappingException, JsonProcessingException {
       	String expected = "{\"eventId\":\"878237843\",\"device_os\":\"Linux\",\"device_description\":\"Linux Laptop\"}";
       	FeatureConfig featureConfig = mapper.readValue(part1+enabled+part2, FeatureConfig.class);
       	transform.transformJson(featureConfig);
       	assertEquals( expected, transform.transformJson(featureConfig));

   	}
    
    @Test
	void testTransformEmpty() throws JsonMappingException, JsonProcessingException {
    	String expected = "{}";
    	FeatureConfig featureConfig = new FeatureConfig();
    	transform.transformJson(featureConfig);
    	assertEquals( expected, transform.transformJson(featureConfig));

	}

}
