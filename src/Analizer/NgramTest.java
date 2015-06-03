package Analizer;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by marcin on 6/3/15.
 */
public class NgramTest {

    @Test
    public void testAddPrefix() throws Exception {
        Ngram ngram = new Ngram("Prefix", "Sufix");
        assertEquals(ngram.getPrefix().get(0), "Prefix");
    }

    @Test
    public void testAddSufix() throws Exception {
        Ngram ngram = new Ngram("Prefix", "Sufix");
        assertEquals(ngram.getSufixes().get(0), "Sufix");
    }

    @Test
    public void testGetPrefix() throws Exception {
        Ngram ngram = new Ngram("Prefix", "Sufix");
        assertEquals(ngram.getPrefix().get(0), "Prefix");
    }

    @Test
    public void testGetSufixes() throws Exception {
        Ngram ngram = new Ngram("Prefix", "Sufix");
        assertEquals(ngram.getSufixes().get(0), "Sufix");
    }

    @Test
    public void testGetRandomSufix() throws Exception {
        Ngram ngram = new Ngram("Prefix", "Sufix");
        assert(ngram.getRandomSufix() != null);
    }
}