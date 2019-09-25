package motolibrary.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class MainModel implements Comparable  {

    private Long id;
    private Integer manufactureId;
    private String description;
    private Integer startYear;
    private Integer endYear;
    private String type;
    private String finalDrive;
    private String transmission;
    private String cc;
    private String power;
    private String torque;
    private String topSpeed;
    private String compression;

    private String rakeAngle;
    private String trail;

    private String brakesFront;
    private String brakesRear;

    private String tiresFront;
    private String tiresRear;

    private String length;
    private String width;
    private String height;
    private String seatHeight;
    private String wheelBase;

    private String fuelCapacity;
    private String fuelConsumption;

    private String dryWeight;
    private String wetWeight;


    public MainModel(Integer manufactureId, String description, Integer startYear, Integer endYear) {
        this.manufactureId = manufactureId;
        this.description = description;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFinalDrive() {
        return finalDrive;
    }

    public void setFinalDrive(String finalDrive) {
        this.finalDrive = finalDrive;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTorque() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public String getRakeAngle() {
        return rakeAngle;
    }

    public void setRakeAngle(String rakeAngle) {
        this.rakeAngle = rakeAngle;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    public String getBrakesFront() {
        return brakesFront;
    }

    public void setBrakesFront(String brakesFront) {
        this.brakesFront = brakesFront;
    }

    public String getBrakesRear() {
        return brakesRear;
    }

    public void setBrakesRear(String brakesRear) {
        this.brakesRear = brakesRear;
    }

    public String getTiresFront() {
        return tiresFront;
    }

    public void setTiresFront(String tiresFront) {
        this.tiresFront = tiresFront;
    }

    public String getTiresRear() {
        return tiresRear;
    }

    public void setTiresRear(String tiresRear) {
        this.tiresRear = tiresRear;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSeatHeight() {
        return seatHeight;
    }

    public void setSeatHeight(String seatHeight) {
        this.seatHeight = seatHeight;
    }

    public String getWheelBase() {
        return wheelBase;
    }

    public void setWheelBase(String wheelBase) {
        this.wheelBase = wheelBase;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getDryWeight() {
        return dryWeight;
    }

    public void setDryWeight(String dryWeight) {
        this.dryWeight = dryWeight;
    }

    public String getWetWeight() {
        return wetWeight;
    }

    public void setWetWeight(String wetWeight) {
        this.wetWeight = wetWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MainModel mainModel = (MainModel) o;

        return new EqualsBuilder()
            .append(id, mainModel.id)
            .append(manufactureId, mainModel.manufactureId)
            .append(description, mainModel.description)
            .append(startYear, mainModel.startYear)
            .append(endYear, mainModel.endYear)
            .append(type, mainModel.type)
            .append(finalDrive, mainModel.finalDrive)
            .append(transmission, mainModel.transmission)
            .append(cc, mainModel.cc)
            .append(power, mainModel.power)
            .append(torque, mainModel.torque)
            .append(topSpeed, mainModel.topSpeed)
            .append(compression, mainModel.compression)
            .append(rakeAngle, mainModel.rakeAngle)
            .append(trail, mainModel.trail)
            .append(brakesFront, mainModel.brakesFront)
            .append(brakesRear, mainModel.brakesRear)
            .append(tiresFront, mainModel.tiresFront)
            .append(tiresRear, mainModel.tiresRear)
            .append(length, mainModel.length)
            .append(width, mainModel.width)
            .append(height, mainModel.height)
            .append(seatHeight, mainModel.seatHeight)
            .append(wheelBase, mainModel.wheelBase)
            .append(fuelCapacity, mainModel.fuelCapacity)
            .append(fuelConsumption, mainModel.fuelConsumption)
            .append(dryWeight, mainModel.dryWeight)
            .append(wetWeight, mainModel.wetWeight)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(manufactureId)
            .append(description)
            .append(startYear)
            .append(endYear)
            .append(type)
            .append(finalDrive)
            .append(transmission)
            .append(cc)
            .append(power)
            .append(torque)
            .append(topSpeed)
            .append(compression)
            .append(rakeAngle)
            .append(trail)
            .append(brakesFront)
            .append(brakesRear)
            .append(tiresFront)
            .append(tiresRear)
            .append(length)
            .append(width)
            .append(height)
            .append(seatHeight)
            .append(wheelBase)
            .append(fuelCapacity)
            .append(fuelConsumption)
            .append(dryWeight)
            .append(wetWeight)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("manufactureId", manufactureId)
            .append("description", description)
            .append("startYear", startYear)
            .append("endYear", endYear)
            .append("type", type)
            .append("finalDrive", finalDrive)
            .append("transmission", transmission)
            .append("cc", cc)
            .append("power", power)
            .append("torque", torque)
            .append("topSpeed", topSpeed)
            .append("compression", compression)
            .append("rakeAngle", rakeAngle)
            .append("trail", trail)
            .append("brakesFront", brakesFront)
            .append("brakesRear", brakesRear)
            .append("tiresFront", tiresFront)
            .append("tiresRear", tiresRear)
            .append("length", length)
            .append("width", width)
            .append("height", height)
            .append("seatHeight", seatHeight)
            .append("wheelBase", wheelBase)
            .append("fuelCapacity", fuelCapacity)
            .append("fuelConsumption", fuelConsumption)
            .append("dryWeight", dryWeight)
            .append("wetWeight", wetWeight)
            .toString();
    }

    @Override
    public int compareTo(Object o) {
        if (this.equals(o)) {
            return 0;
        }
        return this.getDescription().compareTo(((MainModel)o).getDescription());
    }
}
