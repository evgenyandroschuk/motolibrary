package motolibrary.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ResourceCode {

    private long resourceCode;
    private int langId;
    private String description;

    public ResourceCode(long resourceCode, int langId, String description) {
        this.resourceCode = resourceCode;
        this.langId = langId;
        this.description = description;
    }

    public long getResourceCode() {
        return resourceCode;
    }

    public int getLangId() {
        return langId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceCode that = (ResourceCode) o;

        return new EqualsBuilder()
            .append(resourceCode, that.resourceCode)
            .append(langId, that.langId)
            .append(description, that.description)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(resourceCode)
            .append(langId)
            .append(description)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("resourceCode", resourceCode)
            .append("langId", langId)
            .append("description", description)
            .toString();
    }
}
