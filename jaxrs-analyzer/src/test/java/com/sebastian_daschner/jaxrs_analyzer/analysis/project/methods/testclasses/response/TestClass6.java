package com.sebastian_daschner.jaxrs_analyzer.analysis.project.methods.testclasses.response;

import com.sebastian_daschner.jaxrs_analyzer.model.elements.HttpResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClass6 {

    private InnerTestClass6 innerTestClass;

    public Response method(final String id) {
        try {
            innerTestClass.method(id);
            return Response.noContent().build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).header("X-Message", "The entity with identifier " + id + " was not found.").build();
        }
    }

    public static Set<HttpResponse> getResult() {
        final HttpResponse firstResult = new HttpResponse();
        final HttpResponse secondResult = new HttpResponse();

        firstResult.getStatuses().add(404);
        firstResult.getHeaders().add("X-Message");

        secondResult.getStatuses().add(204);

        return new HashSet<>(Arrays.asList(firstResult, secondResult));
    }

    private class InnerTestClass6 {

        private EntityManager entityManager;

        public void method(final String id) {
            final Object managedEntity = entityManager.find(Object.class, id);
            entityManager.remove(managedEntity);
        }

    }

}
