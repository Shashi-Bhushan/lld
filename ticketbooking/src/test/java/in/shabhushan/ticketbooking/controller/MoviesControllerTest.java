package in.shabhushan.ticketbooking.controller;

import in.shabhushan.ticketbooking.controller.api.ShowsController;
import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.models.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = "classpath:data.sql")
class MoviesControllerTest {
    @Autowired
    private ShowsController moviesController;

    @Test
    void test() {
//        List<Movie> movies = moviesController.getShowsByCity(new ShowRequestDTO("Lord of the Rings (LOTR) The Fellowship of the Ring", City.BANGALORE));
//
//        assertEquals(2, movies.size());
    }
}
