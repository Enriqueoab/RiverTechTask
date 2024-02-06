package com.rivertech.betgametask.utils.exception;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)

@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Invalid request")
@ApiResponse(responseCode = "404", description = "Not found")
public @interface DefaultApiResponses { }
