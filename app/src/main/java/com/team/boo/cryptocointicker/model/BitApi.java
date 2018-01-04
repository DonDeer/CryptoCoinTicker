
package com.team.boo.cryptocointicker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BitApi {

    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("bid")
    @Expose
    private String bid;
    @SerializedName("ask")
    @Expose
    private String ask;
    @SerializedName("last_price")
    @Expose
    private String lastPrice;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BitApi() {
    }

    /**
     * 
     * @param timestamp
     * @param volume
     * @param lastPrice
     * @param high
     * @param mid
     * @param low
     * @param ask
     * @param bid
     */
    public BitApi(String mid, String bid, String ask, String lastPrice, String low, String high, String volume, String timestamp) {
        super();
        this.mid = mid;
        this.bid = bid;
        this.ask = ask;
        this.lastPrice = lastPrice;
        this.low = low;
        this.high = high;
        this.volume = volume;
        this.timestamp = timestamp;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("mid", mid).append("bid", bid).append("ask", ask).append("lastPrice", lastPrice).append("low", low).append("high", high).append("volume", volume).append("timestamp", timestamp).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(timestamp).append(volume).append(lastPrice).append(high).append(mid).append(low).append(ask).append(bid).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BitApi) == false) {
            return false;
        }
        BitApi rhs = ((BitApi) other);
        return new EqualsBuilder().append(timestamp, rhs.timestamp).append(volume, rhs.volume).append(lastPrice, rhs.lastPrice).append(high, rhs.high).append(mid, rhs.mid).append(low, rhs.low).append(ask, rhs.ask).append(bid, rhs.bid).isEquals();
    }

}
