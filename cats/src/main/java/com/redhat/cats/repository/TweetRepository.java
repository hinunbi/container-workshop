package com.redhat.cats.repository;

import org.springframework.data.repository.CrudRepository;

import com.redhat.cats.model.Tweet;

public interface TweetRepository extends CrudRepository<Tweet, Long> {
}
