package com.stagegage.festivalService.dto;

import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
public class ShowDto {

    private final UUID showId;
    private final String artistName;
    private final DateTime startTime;
    private final DateTime endTime;

    public ShowDto(UUID showId, String artistName, DateTime startTime, DateTime endTime) {
        this.showId = showId;
        this.artistName = artistName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getShowId() {
        return showId;
    }

    public String getArtistName() {
        return artistName;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }
}
