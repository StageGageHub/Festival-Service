package com.stagegage.festivalService.dto.response;

import com.stagegage.festivalService.dto.FestivalDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
public class FestivalResponse {

    private final String name;
    private final String startDate;
    private final String endDate;
    private final List<ShowResponse> shows;


    public FestivalResponse(String name, String startDate, String endDate, List<ShowResponse> shows) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shows = shows;
    }

    public FestivalResponse(FestivalDto dto) {
        if(dto != null) {
            this.name = dto.getName();
            if(dto.getStartDate() != null)
                this.startDate = dto.getStartDate().toString();
            else
                this.startDate = null;

            if(dto.getEndDate() != null)
                this.endDate = dto.getEndDate().toString();
            else
                this.endDate = null;

            this.shows = ShowResponse.getResponses(dto.getShows());
        } else {
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