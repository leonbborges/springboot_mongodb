package javacourse.springboot.springboot_mongodb.services;

import javacourse.springboot.springboot_mongodb.domain.Post;
import javacourse.springboot.springboot_mongodb.repository.PostRepository;
import javacourse.springboot.springboot_mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PostService {

    @Autowired
    private PostRepository respository;

    public Post findById(String id){
        Optional<Post> obj = respository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id));
    }
    public List<Post> findByTitle(String text){
        return respository.searchTitle(text);

    }

    public List<Post> fullSearch(String text, Instant minDate, Instant maxDate){
        Date myDate = Date.from(maxDate);
        Date date = new Date(myDate.getTime() + 24*60*60*100);
        maxDate = date.toInstant();
        return respository.fullSearch(text, minDate, maxDate);
    }


}
