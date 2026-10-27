package com.github.potap322.clientservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;

@Entity
@Table(name = "client_account")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    //храним это как varchar(1)
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "gender", length = 1)
    private ClientGender gender;

    //сохранение enum как строку в бд
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private ClientStatus status = ClientStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "create_dttm", nullable = false, updatable = false)
    private OffsetDateTime createDttm;

    @UpdateTimestamp
    @Column(name = "modify_dttm", nullable = false)
    private OffsetDateTime modifyDttm;

    public Client() {
    }

    public Client(String fullName, ClientGender gender) {
        this.fullName = fullName;
        this.gender = gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ClientGender getGender() {
        return gender;
    }

    public void setGender(ClientGender gender) {
        this.gender = gender;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(OffsetDateTime createDttm) {
        this.createDttm = createDttm;
    }

    public OffsetDateTime getModifyDttm() {
        return modifyDttm;
    }

    public void setModifyDttm(OffsetDateTime modifyDttm) {
        this.modifyDttm = modifyDttm;
    }


}
