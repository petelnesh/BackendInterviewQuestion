package ke.safaricom.hackathon.BackendInterviewQuestion;

import com.google.common.net.HttpHeaders;
import io.swagger.models.HttpMethod;
import ke.safaricom.hackathon.BackendInterviewQuestion.entities.Movies;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendInterviewQuestionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackendInterviewQuestionApplicationTests {
@Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port + "/api";
     }
	@Test
	public void contextLoads() {
	}
        
   

    @Test
    public void testGetMovieByUser() {
         String username = "peterson";
        Movies movies = restTemplate.getForObject(getRootUrl() + "/movies/get/"+username, Movies.class);
        System.out.println(movies);
        assertNotNull(movies);
    }

    @Test
    public void testAddMovie() {
          Movies movies = new Movies();
        movies.setTitle("Backend Devloper");
        movies.setDescription("This is role includes creating microservices");
        movies.setRating(4);
        ResponseEntity<Movies> postResponse = restTemplate.postForEntity(getRootUrl() + "/addmovie", movies, Movies.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateMovie() {
        String title = "Glass";
        Movies movies = restTemplate.getForObject(getRootUrl() + "/movies/update/" + title, Movies.class);
        movies.setTitle("Backend Devloper");
        movies.setDescription("This is role includes creating microservices");
        movies.setRating(4);
        movies.setRecommendation("This is role includes creating microservices");
        
        restTemplate.put(getRootUrl() + "/jobs/" + title, movies);
        Movies updatedMovie = restTemplate.getForObject(getRootUrl() + "/movies/update/" + title, Movies.class);
        assertNotNull(updatedMovie);
    }

    @Test
    public void testDeleteMovie() {
         String title= "Glass";
         Movies movies = restTemplate.getForObject(getRootUrl() + "/movies/delete/" + title, Movies.class);
         assertNotNull(title);
         restTemplate.delete(getRootUrl() + "/movies/delete/" + title);
         try {
              movies = restTemplate.getForObject(getRootUrl() + "/movies/delete/" + title, Movies.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }

}
