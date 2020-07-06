package com.example.ppmtool.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("id")
@EntityListeners(AuditListener.class)
@MappedSuperclass
public abstract class AbstractDomain<ID extends Serializable> implements Serializable, IHasID<ID>, IHasObjectDescription, IAuditable {

    private static final long serialVersionUID = 1L;

    @Embedded
    private Audit audit;

    @Override
    public final int hashCode() {
        ID id = getId();
        return (id == null ? super.hashCode() : id.hashCode());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        boolean equals = false;
        if (this.getClass().isInstance(obj)) {
            ID id = getId();
            ID objId = (ID) ((IHasID<?>) obj).getId();
            if (id == null) {
                equals = (objId == null && super.equals(obj));
            } else {
                equals = id.equals(objId);
            }
        }
        return equals;
    }

    @Override
    public String getObjectDescription() {
        return getClass().getName() + ": [" + getId() + "]";
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public Audit getAudit() {
        return audit;
    }
}
