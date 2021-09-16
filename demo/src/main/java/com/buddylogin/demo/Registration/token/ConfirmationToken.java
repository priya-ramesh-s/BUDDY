package com.buddylogin.demo.Registration.token;

import com.buddylogin.demo.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {           //database

    @SequenceGenerator(
            name = "confirmation_sequence",                      //relation 2
            sequenceName = "confirmation_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )

    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;         // token created

    @Column(nullable = false)
    private LocalDateTime expiresAt;         // token 15 mins expiration

    private LocalDateTime confirmedAt;       // token confirmed via email link

    @ManyToOne                                //user can have many confirmation tokens
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )

    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;



    }
}
