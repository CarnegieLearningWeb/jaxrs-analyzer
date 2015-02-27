package com.sebastian_daschner.jaxrs_analyzer.analysis.project.methods.testclasses.response;

import com.sebastian_daschner.jaxrs_analyzer.model.elements.HttpResponse;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

public class TestClass28 {

    public Response method(final String id) {
        Supplier<Response> responseSupplier = TestClass28::response;
        return responseSupplier.get();
    }

    private static Response response() {
        final Response.ResponseBuilder builder = Response.status(Response.Status.ACCEPTED);
        builder.header("X-Test", "Hello World");
        return builder.build();
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse result = new HttpResponse();

        result.getStatuses().add(202);
        result.getHeaders().add("X-Test");

        return Collections.singleton(result);
    }

}
