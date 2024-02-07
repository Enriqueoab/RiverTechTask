package com.rivertech.betgametask.wallet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import java.io.Serial;
import lombok.Builder;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EntityListeners;
import io.swagger.v3.oas.annotations.media.Schema;
import com.rivertech.betgametask.wallet.service.WalletJpaListener;
import lombok.Setter;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(WalletJpaListener.class)
@Schema(description = "Player balance holder to bet with")
public class Wallet implements Serializable {

    private final static Long REGISTRATION_INITIAL_BALANCE = 1000L;

    @Serial
    private static final long serialVersionUID = -6447259658138103539L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Credit amount available")
    @Builder.Default
    private Long balance = REGISTRATION_INITIAL_BALANCE;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<WalletHistory> walletHistory;

}
