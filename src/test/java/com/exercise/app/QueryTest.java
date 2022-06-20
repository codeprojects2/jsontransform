package com.exercise.app;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.JsltException;
import com.schibsted.spt.data.jslt.Parser;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Test cases verifying queries against an input.
 */
@RunWith(Parameterized.class)
public class QueryTest extends TestBase {
  private static ObjectMapper mapper = new ObjectMapper();
  private String input;
  private String query;
  private String output;
  private Map<String, JsonNode> variables;

  public QueryTest(String input, String query, String output,
                   Map<String, JsonNode> variables) {
    this.input = input;
    this.query = query;
    this.output = output;
    this.variables = variables;
  }

  @Test
  public void check() {
    try {
      JsonNode context = mapper.readTree(input);

      Expression expr = Parser.compileString(query);
      JsonNode actual = expr.apply(variables, context);
      if (actual == null)
        throw new JsltException("Returned Java null");

      // reparse to handle IntNode(2) != LongNode(2)
      actual = mapper.readTree(mapper.writeValueAsString(actual));

      JsonNode expected = mapper.readTree(output);

      assertEquals("" + expected + " != " + actual + " in query " + query + ", input: " + input + ", actual class " + actual.getClass() + ", expected class " + expected.getClass(), expected, actual);
    } catch (Exception e) {
      throw new RuntimeException("Failure on query " + query + ": " + e, e);
    }
  }

  private static Map<String, JsonNode> toMap(JsonNode json) {
    Map<String, JsonNode> variables = new HashMap();
    if (json != null) {
      Iterator<String> it = json.fieldNames();
      while (it.hasNext()) {
        String field = it.next();
        variables.put(field, json.get(field));
      }
    }
    return variables;
  }
}