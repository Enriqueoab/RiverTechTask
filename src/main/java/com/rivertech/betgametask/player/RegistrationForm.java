package com.rivertech.betgametask.player;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Form for a player registration")
public class RegistrationForm {

    @NotNull
    @Size(min = 2, max = 10)
    private String name;

    @NotNull
    @Size(min = 2, max = 10)
    private String surname;

    @NotNull
    @Size(min = 5, max = 12)
    private String userName;

}
