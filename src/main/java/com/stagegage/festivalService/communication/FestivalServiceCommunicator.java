package com.stagegage.festivalService.communication;

import com.stagegage.festivalService.dto.response.FestivalResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scott on 7/13/14.
 *
 * @author Scott Hendrickson
 */
public class FestivalServiceCommunicator {

    public FestivalResponse getFestivalShows(String festivalName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("festivalName", festivalName);

        ResponseEntity<FestivalResponse> entity = template.getForEntity(
                "http://localhost:8080/festivals/" + URLEncoder.encode(festivalName),
                FestivalResponse.class, queryParams);

        return entity.getBody();
    }
}
