package com.example.vediosystem.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import com.example.vediosystem.domain.Barrage;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BarrageDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void addDanMu(Barrage barrage){
        mongoTemplate.insert(barrage,"danmu");
    }

    public List<Barrage> findAll(String vid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("vid").is(vid));

        Document source = new Document();
        source.put("locale", "zh");
        source.put("numericOrdering", true);
        Collation collation = Collation.from(source);
        query.collation(collation);

        PageRequest pageRequest = PageRequest.of(0,50);
        query.with(pageRequest);

        query.with(Sort.by(Sort.Direction.DESC, "time"));

        return mongoTemplate.find(query,Barrage.class,"danmu");
    }

}

