package com.rivertech.betgametask.player;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
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

    public RegistrationForm(String name, String surname, String userName) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
    }
}
