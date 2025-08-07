package spring.cloud.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TestDto (

        @NotNull(message = "Id is required")
        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Message is required")
        String message
){
}
