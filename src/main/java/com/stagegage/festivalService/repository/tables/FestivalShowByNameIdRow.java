package com.stagegage.festivalService.repository.tables;

import com.stagegage.festivalService.dto.FestivalDto;
import com.stagegage.festivalService.dto.ShowDto;
import com.stagegage.festivalService.repository.tables.keys.FestivalShowKey;
import org.joda.time.DateTime;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
@Table(value = "festival_shows")
public class FestivalShowByNameIdRow {

    @PrimaryKey
    private FestivalShowKey primaryKey;

    @Column
    private String artistName;
    @Column
    private DateTime startDate;
    @Column
    private DateTime endDate;

    private FestivalShowByNameIdRow(FestivalShowKey primaryKey, String artistName, DateTime startDate, DateTime endDate) {
        this.primaryKey = primaryKey;
        this.artistName = artistName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FestivalShowByNameIdRow(String festivalName, UUID showId, String artistName,DateTime startDate, DateTime endDate) {
        this(new FestivalShowKey(festivalName, showId), artistName, startDate, endDate);
    }

    public FestivalShowByNameIdRow(String festivalName, ShowDto dto) {
        this.primaryKey = new FestivalShowKey(festivalName, dto.getShowId());
        this.artistName = dto.getArtistName();
        this.startDate = dto.getStartTime();
        this.endDate = dto.getEndTime();
    }

    public static FestivalDto toDto(FestivalDto festival, List<FestivalShowByNameIdRow> showRows) {
        if (showRows == null) return null;

        List<ShowDto> shows = new ArrayList<ShowDto>();
        for (FestivalShowByNameIdRow row : showRows) {
            shows.add(toDto(row));
        }

        return new FestivalDto(festival.getId(),
                                festival.getName(),
                                festival.getStartDate(),
                                festival.getEndDate(),
                                shows);

    }

    private static ShowDto toDto(FestivalShowByNameIdRow row) {
        return new ShowDto(row.primaryKey.getShowId(),
                            row.getArtistName(),
                            row.getStartDate(),
                            row.getEndDate());
    }

    public String getArtistName() {
        return artistName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }
}
