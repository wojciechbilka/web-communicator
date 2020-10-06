package com.communicator.webcommunicator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {
    @Id
    @Column(name = "series")
    private String series;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "last_used", nullable = false)
    private Date lastUsed;
}
