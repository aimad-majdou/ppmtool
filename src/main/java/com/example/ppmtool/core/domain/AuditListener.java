package com.example.ppmtool.core.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void setCreatedOn(IAuditable auditable) {
        Audit audit = auditable.getAudit();

        if(audit == null) {
            audit = new Audit();
            auditable.setAudit(audit);
        }

        audit.setCreatedOn(LocalDateTime.now());
        // @todo see: https://vladmihalcea.com/how-to-emulate-createdby-and-lastmodifiedby-from-spring-data-using-the-generatortype-hibernate-annotation/
        audit.setCreatedBy(null);
    }

    @PreUpdate
    public void setUpdatedOn(IAuditable auditable) {
        Audit audit = auditable.getAudit();

        audit.setUpdatedOn(LocalDateTime.now());
        // @todo as createdBy
        audit.setUpdatedBy(null);
    }
}
