package io.github.roony11_1.error.quarkus;

import io.github.roony.error.core.ErrorCategory;

public interface ErrorCategoryHttpMapper 
{
    int toHttpStatus(ErrorCategory category);
}