package com.ladecentro.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @CreatedDate
    @JsonProperty(value = "created_at", access = JsonProperty.Access.WRITE_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty(value = "updated_at", access = JsonProperty.Access.WRITE_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
