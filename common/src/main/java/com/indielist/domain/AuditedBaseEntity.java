package com.indielist.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

/**
 * @author jsingh on 15-01-10.
 */
@MappedSuperclass
abstract public class AuditedBaseEntity<T> extends BaseEntity<T> {

    @Type(type = "com.indielist.domain.InstantAsTimestampUserType")
    @Column(name = "create_timestamp")
    protected Instant createTimestamp;

    @Type(type = "com.indielist.domain.InstantAsTimestampUserType")
    @Column(name = "update_timestamp")
    protected Instant updateTimestamp;

    public Instant getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Instant createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @PrePersist
    @PreUpdate
    public void updateAuditFields() {
        Instant now = Instant.now();
        if(createTimestamp == null) {
            setCreateTimestamp(now);
        }
        setUpdateTimestamp(now);
    }
}
