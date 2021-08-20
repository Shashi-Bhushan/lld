package in.shabhushan.ticketbooking.repository;

import in.shabhushan.ticketbooking.enums.Language;
import in.shabhushan.ticketbooking.models.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = "classpath:data.sql")
public class MoviesRepositoryTest {
    @Autowired
    private MoviesRepository moviesRepository;

    @Test
    public void testMoviesRepo() {
        Movie firstInstance = moviesRepository.getById(1L);
        firstInstance.setDirector("Client Eastwood");

        Movie secondInstance = moviesRepository.getById(1L);
        secondInstance.setLanguage(Language.HINDI);

        moviesRepository.save(firstInstance);
        moviesRepository.save(secondInstance);

        assertEquals("select * from halls where cinema_id = ?" , moviesRepository.getById(1L).getDirector());
    }
}

/*

eyJncm91cHMiOlsiQWRtaW5pc3RyYXRvciIsIkNTRSJdLCJ0ZW5hbnRfaWQiOiJiNWRkYWZjNC01MWZiLTQyNWItYTM5Yy0wNDNlYTM2MWM3NTUiLCJzdWIiOiJzdWJqZWN0aWQxMjM0IiwidXNlci5lbWFpbCI6ImFiY2RAbmlsZS1nbG9iYWwuY29tIiwiYWxnIjoiSFMyNTYifQ
{
  "groups": [
    "Administrator",
    "CSE"
  ],
  "tenant_id": "b5ddafc4-51fb-425b-a39c-043ea361c755",
  "sub": "subjectid1234",
  "user.email": "abcd@nile-global.com",
  "alg": "HS256"
}

http://{{host}}:{{client_service_port}}/api/v1/client-cse-workflow/events/tenant/{{tenant}}/macAddress/24:15:10:2f:e7:d3?startTime=2021-08-19T07:14:00Z&endTime=
http://{{host}}:{{client_service_port}}/api/v1/client-cse-workflow/events/tenant/{{tenant}}/mac/{{macAddress}}?endTime=2021-08-10T00:00:00Z&startTime=2021-08-09T00:00:00Z
eyJncm91cHMiOlsiQ1NFIiwiQWRtaW5pc3RyYXRvciJdLCJ0ZW5hbnRfaWQiOiI1NjQ0ODk3Mi01ZjhiLTRmZmItYmRhYi0zNTMyOTcyZmMzNDgiLCJzdWIiOiJzdWJqZWN0aWQxMjM0IiwidXNlci5lbWFpbCI6ImFiY2RAbmlsZS1nbG9iYWwuY29tIiwiYWxnIjoiSFMyNTYifQ
{
  "groups": [
    "CSE",
    "Administrator"
  ],
  "tenant_id": "56448972-5f8b-4ffb-bdab-3532972fc348",
  "sub": "subjectid1234",
  "user.email": "abcd@nile-global.com",
  "alg": "HS256"
}
 */