package io.quarkus.ts.many.extensions;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServiceAdorable {
    public String process(String name) {
        return "Adorable - " + name + " - done";
    }
}
