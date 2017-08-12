package com.sme;

import groovy.json.JsonBuilder;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object model) throws Exception {
        return new JsonBuilder(model).toString();
    }
}
