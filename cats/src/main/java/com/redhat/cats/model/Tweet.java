package com.redhat.cats.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public String name;
	public String text;
	@Column(length = 1024)
	public String url;
	public long tweetCount;
	public long imageCount;
	public String now;
	public String upTime;

}
