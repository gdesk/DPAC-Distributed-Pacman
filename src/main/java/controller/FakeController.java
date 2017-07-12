package controller;

import client.model.MatchResult;
import client.model.MatchResultImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class FakeController {

    public static List<MatchResult>  getmatches(){
        List<MatchResult> r = new ArrayList<>();
        r.add(new MatchResultImpl(true, 210));
        r.add(new MatchResultImpl(false, 410));
        r.add(new MatchResultImpl(true, 6210));
        r.add(new MatchResultImpl(true, 110));
        r.add(new MatchResultImpl(false, 10));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        r.add(new MatchResultImpl(true, 50));
        return  r;
    }
}
