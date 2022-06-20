package com.exercise.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.exercise.model.FeatureConfig;
import com.exercise.model.Transform;
import com.exercise.model.TransformException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;

@Service
public class TransformServiceImpl implements TransformService {
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * accept feature configuration and JSON payload, process both and return transformed JSON output. 
	 *
	 * @param errorReturnedEvent event request
	 */
	@Override
	public String transformJson(FeatureConfig featureConfig) {
		JsonObject mergedObject = new JsonObject();
		try {
			Map<String, Object> properties = featureConfig.getAdditionalProperties();
			String jsonPayload = mapper.writeValueAsString(properties);
			JsonNode context = mapper.readTree(jsonPayload);
			List<Transform> trasnform = featureConfig.getTransforms();
			for (Transform transform : trasnform) { 
				if (transform.getEnabled()) {
				  String query = getQuery(transform);
					Expression expr = Parser.compileString(query);
					JsonNode actual = expr.apply(Collections.EMPTY_MAP, context.get("json"));
					merge(actual, mergedObject);
				}

			}

		} catch (JsonProcessingException e) {
			throw new TransformException(e.getMessage());
		}
		return mergedObject.toString();
	}

	private String getQuery(Transform transform) {
		
         return "{\n" +
            "  \"eventId\" : .eventId,\n" +	
            "  \""+transform.getName()+"\" : "+transform.getJsltExpression()+"\n" +
            "}\n";
	}
	
	/**
	 * Join result of multiple transformations.
	 *
	 * @param node 
	 * @param mergedObject 
	 */

	private void merge(JsonNode node, JsonObject mergedObject) {
		Iterator<Entry<String, JsonNode>> iterator = node.fields();
		while (iterator.hasNext()) {
			Entry<String, JsonNode> entry = iterator.next();
			String key = entry.getKey();
			JsonNode jNode  = entry.getValue();
			if(key != null) {
				mergedObject.addProperty(key, jNode.asText().replace("null", "").trim());	
			}
			
		}
	}

}
