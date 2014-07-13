package com.stagegage.festivalService.repository.tables;

import com.stagegage.festivalService.dto.FestivalDto;
import org.joda.time.DateTime;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Scott on 7/11/14.
 *
 * @author Scott Hendrickson
 */
@Table(value = "festivals_by_name")
public class FestivalByNameRow {

    @PrimaryKey
    private final String name;

    @Column
    private final UUID id;
    @Column
    private final DateTime startDate;
    @Column
    private final DateTime endDate;

    public FestivalByNameRow(UUID id, String name, DateTime startDate, DateTime endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FestivalByNameRow(FestivalDto festivalDto) {
        this.id = festivalDto.getId();
        this.name = festivalDto.getName();
        this.startDate = festivalDto.getStartDate();
        this.endDate = festivalDto.getEndDate();
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

    public static List<FestivalDto> toDto(List<FestivalByNameRow> festivalRows) {
        List<FestivalDto> festivals = new ArrayList<FestivalDto>();
        for(FestivalByNameRow row : festivalRows) {
            festivals.add(toDto(row));
        }

        return festivals;
    }

    private static FestivalDto toDto(FestivalByNameRow festival) {
        return new FestivalDto(festival.getId(), festival.getName(), festival.getStartDate(), festival.getEndDate(), null);
    }
}
