package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class PostTest {
    @Test
    public void newPostObjectGetsCorrectlyCreated_true() throws Exception {
        Post post = new Post("Day 1: Intro");
        assertEquals(true, post instanceof Post);
    }
    @Test
    public void postInstantiatesWithContent_true() throws Exception{
        Post post = new Post("Day 1: Intro");
        assertEquals("Day 1: Intro", post.getContent());
    }
    @After
    public void tearDown(){
        Post.clearAllPosts();
    }
    @Test
    public void allPostsAreCorrectlyDone_true(){
        Post post = new Post("Day1: Intro");
        Post otherPost = new Post("How to pair successfully");
        assertEquals(2, Post.getAll().size());
    }
    @Test
    public void allPostsContainsPosts_true(){
        Post post = new Post("Day 1: Intro");
        Post otherPost = new Post("How to pair successfully");
        assertTrue(Post.getAll().contains(post));
        assertTrue(Post.getAll().contains(otherPost));
    }
    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception{
        Post myPost = new Post("Day 1: Intro");
        assertEquals(false, myPost.getPublished());
    }
    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Post myPost = setupNewPost();
        assertEquals(LocalDateTime.now().getDayOfWeek(), myPost.getCreatedAt().getDayOfTheWeek());
    }
    public Post setupNewPost(){
        return new Post("Day 1: Intro");
    }
}