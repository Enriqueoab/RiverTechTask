package com.rivertech.betgametask.wallet;

import com.rivertech.betgametask.wallet.service.WalletJpaListener;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.Builder;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EntityListeners;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Schema(description = "Player balance holder to bet with")
public class Wallet implements Serializable {

    private final static Long REGISTRATION_INITIAL_BALANCE = 1000L;

    @Serial
    private static final long serialVersionUID = -6447259658138103539L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Schema(description = "Credit amount available")
    @Builder.Default
    private Long balance = REGISTRATION_INITIAL_BALANCE;

}
