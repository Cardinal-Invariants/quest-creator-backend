package com.github.gunghorse.pathes.quests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.github.gunghorse.pathes.quests.points.QuestPoint;
import com.github.gunghorse.pathes.quests.points.QuestStartPoint;

import com.github.gunghorse.pathes.Keys;

import com.github.gunghorse.pathes.user.User;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedList;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;


@NodeEntity
public class Quest {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    @JsonIgnoreProperties({"quest","playing","creatures"})
    @Relationship(type = "PLAYING", direction = INCOMING)
    private List<User> players = new LinkedList<>();

    @Relationship(type = Keys.PLAYING, direction = INCOMING)
    private List<User> players = new ArrayList<>();

    @JsonIgnoreProperties({"quest","playing","creatures"})
    @Relationship(type = Keys.CREATED_BY)
    private User creator;

    @JsonIgnoreProperties({"quest","children","parents"})
    @Relationship(type = "BELONGS_TO", direction = INCOMING)
    private List<QuestPoint> points = new LinkedList<>();

    @JsonIgnoreProperties({"quest","children","parents"})
    @Relationship(type = "STARTING_FROM")
    private QuestStartPoint startPoint;

    public Quest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setCreator(User creator){
        this.creator = creator;
        creator.addCreature(this);
    }

    public void addPlayer(User player){
        players.add(player);
    }


    public List<QuestPoint> getPoints() {
        return points;
    }

    public void setPoints(List<QuestPoint> points) {
        this.points = points;
    }

    public void addPoint(QuestPoint point){
        points.add(point);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
