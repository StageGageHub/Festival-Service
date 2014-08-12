package com.stagegage.festivalService.dto;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.*;

/**
 * Created by Scott on 7/11/14.
 *
 * @author Scott Hendrickson
 */
public class FestivalDto {

    private final String name;
    private final DateTime startDate;
    private final DateTime endDate;
    private final List<ShowDto> shows;

    private static Log logger = LogFactory.getLog(FestivalDto.class);


    public FestivalDto(String name, DateTime startDate, DateTime endDate, List<ShowDto> shows) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shows = shows;
    }

    public FestivalDto(String name, String startDate, String endDate) {
        this.name = name;

        if(startDate != null) {
            this.startDate = ISODateTimeFormat.dateTime().parseDateTime(startDate);
        } else {
            this.startDate = null;
        }
        if(endDate != null) {
            this.endDate = ISODateTimeFormat.dateTime().parseDateTime(startDate);
        } else {
            this.endDate = null;
        }

        this.shows = null;
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

    public static FestivalDto toFestivalDTO(DBObject festivalDBO) {
        if(festivalDBO == null) {
            return new FestivalDto(null, null, null);
        }
        logger.debug(String.format("Mapping fesitval DTO from DBO: %s", festivalDBO.toString()));

        String name = (String) festivalDBO.removeField("name");
        DateTime startDate = DateTime.parse((String) festivalDBO.removeField("startDate"));
        DateTime endDate = DateTime.parse((String) festivalDBO.removeField("endDate"));
        ArrayList<DBObject> showDBOs = (ArrayList<DBObject>) festivalDBO.removeField("shows");

        List<ShowDto> shows = new ArrayList<ShowDto>();
        if(showDBOs != null) {
            for (DBObject dbo : showDBOs) {
                shows.add(new ShowDto(
                        (String)dbo.removeField("name"),
                        DateTime.parse((String) dbo.removeField("startTime")),
                        DateTime.parse((String) dbo.removeField("endTime"))));
            }
        }

        return new FestivalDto(name, startDate, endDate, shows);
    }

    public DBObject toDBO() {
        return new BasicDBObject()
                .append("name", name)
                .append("startDate", startDate.toString())
                .append("endDate", endDate.toString())
                .append("shows", getShowsDBOArrayList());
    }

    private ArrayList<DBObject> getShowsDBOArrayList() {
        ArrayList<DBObject> showDBOList = new ArrayList<DBObject>();

        if(shows == null) {
            return showDBOList;
        }

        for(ShowDto show : shows) {
            if(show == null) {
                continue;
            }

            showDBOList.add(show.toDBO());
        }

        return showDBOList;
    }

    public DBObject toUpdateDBO() {
        // Recall updates must start ALL with $ modifiers if you don't wanna overwrite
        DBObject showsDBO = new BasicDBObject("shows", new BasicDBObject("$each", getShowsDBOArrayList()));
        DBObject update = new BasicDBObject("$setOnInsert", new BasicDBObject("name", name).append("startDate", (startDate != null) ? startDate.toString() : null).append("endDate", (endDate != null) ? endDate.toString() : null));

        update.put("$push", showsDBO);

        return update;
    }
}
