/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sebastian_daschner.jaxrs_analyzer.analysis.results.testclasses.typeanalyzer;

import com.sebastian_daschner.jaxrs_analyzer.model.rest.TypeRepresentation;
import com.sebastian_daschner.jaxrs_analyzer.model.types.Type;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * @author Sebastian Daschner
 */
public class TestClass1 {

    private static String PRIVATE_FIELD;
    public static String PUBLIC_FIELD;
    private String privateField;
    protected String protectedField;
    public String publicField;

    public String getTest() {
        return null;
    }

    public int getInt() {
        return 0;
    }

    public static String getStaticString() {
        return null;
    }

    public String string() {
        return null;
    }


    public static TypeRepresentation getResult() {
        final TypeRepresentation representation = new TypeRepresentation(new Type(TestClass1.class.getName()));

        final JsonObject jsonObject = Json.createObjectBuilder().add("publicField", "string").add("test", "string").add("int", 0).build();

        representation.getRepresentations().put("application/json", jsonObject);
        return representation;
    }

}
