
package com.team.boo.cryptocointicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class Kursy {

    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("last")
    @Expose
    private Integer last;
    @SerializedName("bid")
    @Expose
    private Integer bid;
    @SerializedName("ask")
    @Expose
    private Double ask;
    @SerializedName("vwap")
    @Expose
    private Integer vwap;
    @SerializedName("average")
    @Expose
    private Integer average;
    @SerializedName("volume")
    @Expose
    private Double volume;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Kursy() {
    }

    /**
     *
     * @param min
     * @param vwap
     * @param max
     * @param last
     * @param volume
     * @param ask
     * @param bid
     * @param average
     */
    public Kursy(Double max, Double min, Integer last, Integer bid, Double ask, Integer vwap, Integer average, Double volume) {
        super();
        this.max = max;
        this.min = min;
        this.last = last;
        this.bid = bid;
        this.ask = ask;
        this.vwap = vwap;
        this.average = average;
        this.volume = volume;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Integer getVwap() {
        return vwap;
    }

    public void setVwap(Integer vwap) {
        this.vwap = vwap;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("max", max).append("min", min).append("last", last).append("bid", bid).append("ask", ask).append("vwap", vwap).append("average", average).append("volume", volume).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(min).append(vwap).append(max).append(last).append(volume).append(ask).append(bid).append(average).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Kursy) == false) {
            return false;
        }
        Kursy rhs = ((Kursy) other);
        return new EqualsBuilder().append(min, rhs.min).append(vwap, rhs.vwap).append(max, rhs.max).append(last, rhs.last).append(volume, rhs.volume).append(ask, rhs.ask).append(bid, rhs.bid).append(average, rhs.average).isEquals();
    }

}
