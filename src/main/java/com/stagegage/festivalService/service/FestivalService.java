package com.stagegage.festivalService.service;

import com.stagegage.festivalService.dto.FestivalDto;
import com.stagegage.festivalService.dto.ShowDto;
import com.stagegage.festivalService.exception.InvalidFestivalDtoException;
import com.stagegage.festivalService.repository.FestivalRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Scott on 7/11/14.
 *
 * @author Scott Hendrickson
 */
@Component
public class FestivalService {

    @Autowired
    private FestivalRepository festivalRepository;

    Logger logger = Logger.getLogger(getClass());


    public List<FestivalDto> getFestivals(String name, String genre) {
        if(name != null || genre != null) {
            logger.info("getting filtered festivals from repo");
            return festivalRepository.getFestivals(name, genre);
        } else {
            logger.info("getting all festivals from repo");
            return festivalRepository.getAllFestivals();
        }
    }

    public FestivalDto createFestival(FestivalDto festivalDto) {
        if(festivalDto.isValid()) {
            return festivalRepository.createFestival(festivalDto);
        } else {
            throw new InvalidFestivalDtoException("Invalid Festival Dto");
        }
    }

    public FestivalDto getFestival(String festivalName) {
        return festivalRepository.getFestivalByName(festivalName);
    }


    public FestivalDto addFestivalShow(String festivalName, ShowDto showDto) {

        return festivalRepository.addShowToFestival(festivalName, showDto);

    }
}
