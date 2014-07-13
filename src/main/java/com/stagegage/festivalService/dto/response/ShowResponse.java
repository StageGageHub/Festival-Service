package com.stagegage.festivalService.dto.response;

import com.stagegage.festivalService.dto.ShowDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
public class ShowResponse {

    private final UUID showId;
    private final String artistName;
    private final String startTime;
    private final String endTime;

    public ShowResponse(UUID showId, String artistName, String startTime, String endTime) {
        this.showId = showId;
        this.artistName = artistName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ShowResponse(ShowDto dto) {
        this.showId = dto.getShowId();
        this.artistName = dto.getArtistName();
        this.startTime = dto.getStartTime().toString();
        this.endTime = dto.getEndTime().toString();
    }

    public static List<ShowResponse> getResponses(List<ShowDto> showDtos) {
        if (showDtos == null) return null;

        List<ShowResponse> showResponses = new ArrayList<ShowResponse>();
        for (ShowDto dto : showDtos) {
            showResponses.add(new ShowResponse(dto));
        }

        return showResponses;
    }

    public UUID getShowId() {
        return showId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
