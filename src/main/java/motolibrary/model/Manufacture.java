package motolibrary.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Manufacture implements Comparable {

    private Long id;
    private Long resourceId;
    private String description;
    private String country;

    public Manufacture(Long id, Long resourceId, String description, String country) {
        this.id = id;
        this.resourceId = resourceId;
        this.description = description;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public long getResourceId() {
        return resourceId;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Manufacture that = (Manufacture) o;

        return new EqualsBuilder()
            .append(id, that.id)
            .append(resourceId, that.resourceId)
            .append(description, that.description)
            .append(country, that.country)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(resourceId)
            .append(description)
            .append(country)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("resourceId", resourceId)
            .append("description", description)
            .append("country", country)
            .toString();
    }


    @Override
    public int compareTo(Object o) {
        if (this.equals(o)) {
            return 0;
        }
        return this.getDescription().compareTo(((Manufacture)o).getDescription());
    }
}
