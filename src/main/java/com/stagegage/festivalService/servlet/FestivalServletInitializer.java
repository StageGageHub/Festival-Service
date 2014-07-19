package com.stagegage.festivalService.servlet;

import com.stagegage.festivalService.Main;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by Scott on 7/19/14.
 *
 * @author Scott Hendrickson
 */
public class FestivalServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }
}
