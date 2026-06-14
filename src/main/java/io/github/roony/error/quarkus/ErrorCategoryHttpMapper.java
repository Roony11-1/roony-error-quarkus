package io.github.roony.error.quarkus;

import io.github.roony.error.core.ErrorCategory;

public interface ErrorCategoryHttpMapper 
{
    int toHttpStatus(ErrorCategory category);
}