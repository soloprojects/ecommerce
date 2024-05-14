package com.ecommerce.bookinventory.repository;

import com.ecommerce.bookinventory.model.BookModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends ElasticsearchRepository<BookModel, String> {

    @Query("{\"bool\": {\"should\": [{\"wildcard\": {\"title\": \"*?0*\"}}, {\"wildcard\": {\"author\": \"*?0*\"}}, {\"wildcard\": {\"genre\": \"*?0*\"}}, {\"wildcard\": {\"yearOfPublication\": \"*?0*\"}}]}}")
//    @Query("{\"bool\": {\"should\": [" +
//            "{\"match\": {\"title\": \"?0\"}}," +
//            "{\"match\": {\"author\": \"?0\"}}," +
//            "{\"match\": {\"yearOfPublication\": \"?0\"}}," +
//            "{\"match\": {\"genre\": \"?0\"}}" +
//            "]}}")
    //@Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"title\", \"author\",\"isbn_code\", \"genre\",\"yearOfPublication\"]}}")
    List<BookModel> multiMatchQuery(String queryString);

}
