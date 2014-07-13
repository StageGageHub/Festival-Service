package com.stagegage.festivalService.dto;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Scott on 7/11/14.
 *
 * @author Scott Hendrickson
 */
public class FestivalDto {

    private final UUID id;
    private final String name;
    private final DateTime startDate;
    private final DateTime endDate;
    private final List<ShowDto> shows;


    public FestivalDto(UUID id, String name, DateTime startDate, DateTime endDate, List<ShowDto> shows) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shows = shows;
    }

    public FestivalDto(String name, String startDate, String endDate) {
        Random random = new Random();

        this.id = UUID.randomUUID();
        this.name = name;
        this.startDate = ISODateTimeFormat.dateTime().parseDateTime(startDate);
        this.endDate = ISODateTimeFormat.dateTime().parseDateTime(startDate);
        this.shows = null;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }


    public List<ShowDto> getShows() {
        return shows;
    }

    public boolean isValid() {
        return (name !=  null) && (startDate != null) && (endDate != null);
    }

}
