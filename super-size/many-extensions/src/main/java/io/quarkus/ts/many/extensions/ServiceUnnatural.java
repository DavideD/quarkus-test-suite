package io.quarkus.ts.many.extensions;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServiceUnnatural {
    public String process(String name) {
        return "Unnatural - " + name + " - done";
    }
}
