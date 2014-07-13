package com.stagegage.festivalService.repository.tables.keys;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Scott on 7/11/14.
 *
 * @author Scott Hendrickson
 */
@PrimaryKeyClass
public class FestivalShowKey implements Serializable {

    @PrimaryKeyColumn(name = "festival_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String festivalName;

    @PrimaryKeyColumn(name = "show_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID showId;

    public FestivalShowKey(String festivalName, UUID showId) {
        this.festivalName = festivalName;
        this.showId = showId;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public UUID getShowId() {
        return showId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((festivalName == null) ? 0 : festivalName.hashCode());
        result = prime * result + ((showId == null) ? 0 : showId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FestivalShowKey other = (FestivalShowKey) obj;
        if (festivalName == null) {
            if (other.festivalName != null)
                return false;
        } else if (!festivalName.equals(other.festivalName))
            return false;
        if (showId == null) {
            if (other.showId != null)
                return false;
        } else if (!showId.equals(other.showId))
            return false;
        return true;
    }
}
