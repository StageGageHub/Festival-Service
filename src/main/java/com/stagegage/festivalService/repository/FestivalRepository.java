package com.stagegage.festivalService.repository;

import com.mongodb.*;
import com.stagegage.festivalService.dto.FestivalDto;
import com.stagegage.festivalService.dto.ShowDto;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 7/11/14.
 *
 * @author Scott Hendrickson
 */
@Configuration
public class FestivalRepository {

    @Autowired
    private Environment env;

    private MongoConfig dbConfig;
    private DB db;
    private DBCollection festivals;

    private Log logger = LogFactory.getLog(getClass());


    public FestivalRepository() {
        dbConfig = new MongoConfig();

        this.db = dbConfig.createDB();
        if(db == null)
            System.out.println("Could not create DB");
        this.festivals = db.getCollection("festivals");

        // Make sure connection is ok
        db.getStats();
    }

    public List<FestivalDto> getFestivals(String name, String genre) {
        return getDtosFromCursor(festivals.find(new FestivalDto(name, null, null).toDBO()));
    }

    public List<FestivalDto> getAllFestivals() {
        return getDtosFromCursor(festivals.find());
    }

    private List<FestivalDto> getDtosFromCursor(DBCursor cursor) {
        List<FestivalDto> festivalDtos = new ArrayList<FestivalDto>();
        try {
            while(cursor.hasNext()) {
                DBObject festival = cursor.next();
                FestivalDto festivalDto = FestivalDto.toFestivalDTO(festival);

                // Clean up any prior mistakes
                // TODO: make these asynch requests
                if(festivalDto.getName() == null) {
                    festivals.remove(festival);
                    continue;
                }

                festivalDtos.add(festivalDto);
            }
        } finally {
            cursor.close();
        }
        return festivalDtos;
    }

    public FestivalDto createFestival(FestivalDto festivalDto) {
        // True for upsert, false for multi. We dont need to update all, as only one will match anyway
        festivals.update(festivalDto.toDBO(), festivalDto.toDBO(), true, false);

        return festivalDto;
    }

    public FestivalDto getFestivalByName(String festivalName) {
        return FestivalDto.toFestivalDTO(festivals.findOne(new BasicDBObject("name", festivalName), new BasicDBObject("name", "true").append("startDate", "true").append("endDate", "true").append("shows", "true")));

    }

    public FestivalDto addShowToFestival(String festivalName, ShowDto showDto) {
        List<ShowDto> showDtos = new ArrayList<ShowDto>();
        showDtos.add(showDto);
        festivals.update(new BasicDBObject("name", festivalName), new FestivalDto(festivalName, null, null, showDtos).toUpdateDBO());

        return getFestivalByName(festivalName);
    }
}
