package com.stagegage.festivalService.dto;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.joda.time.DateTime;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
public class ShowDto {

    private final String artistName;
    private final DateTime startTime;
    private final DateTime endTime;

    public ShowDto(String artistName, DateTime startTime, DateTime endTime) {
        this.artistName = artistName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getArtistName() {
        return artistName;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public static ShowDto toDTO(DBObject showDBO) {
        if(showDBO == null)
            return new ShowDto(null, null, null);

        String name = (String) showDBO.removeField("name");
        DateTime startTime = DateTime.parse((String) showDBO.removeField("artistName"));
        DateTime endTime = DateTime.parse((String) showDBO.removeField("artistName"));

        return new ShowDto(name, startTime, endTime);
    }

    public DBObject toDBO() {
        return new BasicDBObject()
                .append("name", artistName)
                .append("startTime", startTime.toString())
                .append("endTime", endTime.toString());
    }
}
