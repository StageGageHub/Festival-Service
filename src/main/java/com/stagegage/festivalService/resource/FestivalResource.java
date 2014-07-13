package com.stagegage.festivalService.resource;

import com.stagegage.festivalService.dto.FestivalDto;
import com.stagegage.festivalService.dto.ShowDto;
import com.stagegage.festivalService.dto.response.FestivalResponse;
import com.stagegage.festivalService.service.FestivalService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Scott on 7/11/14.
 *
 * @author Scott Hendrickson
 */
@RestController
@RequestMapping("/festivals")
public class FestivalResource {

    @Autowired
    private FestivalService festivalService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FestivalResponse> getFestivals(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) String genre)
    {

        List<FestivalDto> festivalDtos = festivalService.getFestivals(name, genre);

        return FestivalResponse.getResponses(festivalDtos);
    }

    @RequestMapping(method = RequestMethod.POST)
    public FestivalResponse createFestival(@RequestParam(required = true) String name,
                                           @RequestParam(required = true) String startDate,
                                           @RequestParam(required = true) String endDate)
    {

        FestivalDto festivalDto = festivalService.createFestival(new FestivalDto(name, startDate, endDate));

        return new FestivalResponse(festivalDto);
    }

    @RequestMapping(value = "/{festivalName}", method = RequestMethod.GET)
    public FestivalResponse getFestivalById(@PathVariable String festivalName) {

        return new FestivalResponse(festivalService.getFestival(festivalName));
    }

    @RequestMapping(value = "/{festivalName}/shows", method = RequestMethod.GET)
    public FestivalResponse getFestivalShows(@PathVariable String festivalName) {

        return new FestivalResponse(festivalService.getFestivalShows(festivalName));
    }

    @RequestMapping(value = "/{festivalName}/shows", method = RequestMethod.PUT)
    public FestivalResponse addFestivalShow(@PathVariable String festivalName,
                                                @RequestParam String artistName,
                                                @RequestParam String startTime,
                                                @RequestParam String endTime)
    {

        return new FestivalResponse(festivalService.addFestivalShow(festivalName,
                           new ShowDto(UUID.randomUUID(),
                                artistName,
                                DateTime.parse(startTime),
                                DateTime.parse(endTime))));
    }



}
