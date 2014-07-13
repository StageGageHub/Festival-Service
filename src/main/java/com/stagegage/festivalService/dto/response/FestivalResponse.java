package com.stagegage.festivalService.dto.response;

import com.stagegage.festivalService.dto.FestivalDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
public class FestivalResponse {

    private final UUID id;
    private final String name;
    private final String startDate;
    private final String endDate;
    private final List<ShowResponse> shows;


    public FestivalResponse(UUID id, String name, String startDate, String endDate, List<ShowResponse> shows) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shows = shows;
    }

    public FestivalResponse(FestivalDto dto) {
        if(dto != null) {
            this.id = dto.getId();
            this.name = dto.getName();
            this.startDate = dto.getStartDate().toString();
            this.endDate = dto.getEndDate().toString();
            this.shows = ShowResponse.getResponses(dto.getShows());
        } else {
            this.id = null;
            this.name = null;
            this.startDate = null;
            this.endDate = null;
            this.shows = null;
        }
    }

    public static List<FestivalResponse> getResponses(List<FestivalDto> festivalDtos) {
        List<FestivalResponse> responses = new ArrayList<FestivalResponse>();
        for(FestivalDto dto : festivalDtos) {
            responses.add(new FestivalResponse(dto));
        }

        return responses;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<ShowResponse> getShows() {
        return shows;
    }
}