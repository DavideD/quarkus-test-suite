package io.quarkus.ts.reactive.http;

import io.quarkus.hibernate.reactive.panache.common.ProjectedFieldName;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class BookDescription {
    public final String title;

    public BookDescription(@ProjectedFieldName("title") String title) {
        this.title = title;
    }
}
