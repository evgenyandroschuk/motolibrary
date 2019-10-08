package motolibrary.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ModelShortDetails {

    private int id;
    private String description;
    private Integer startYear;
    private Integer endYear;

    public ModelShortDetails(int id, String description, Integer startYear, Integer endYear) {
        this.id = id;
        this.description = description;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModelShortDetails that = (ModelShortDetails) o;

        return new EqualsBuilder()
            .append(id, that.id)
            .append(description, that.description)
            .append(startYear, that.startYear)
            .append(endYear, that.endYear)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(description)
            .append(startYear)
            .append(endYear)
            .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModelShortDetails{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", startYear=").append(startYear);
        sb.append(", endYear=").append(endYear);
        sb.append('}');
        return sb.toString();
    }
}
