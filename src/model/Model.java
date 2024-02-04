package model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Model implements Runnable {

    private List<State> visited;
    private State finish;
    private State st;
    private long start;
    private long end;
    public Model() {
        visited = new ArrayList<>();
    }

    private boolean visited(State s) {
        for (State i : visited) {
            if (i.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        State current = new State();
        PriorityQueue<State> frontier = new PriorityQueue<>();
        start = System.currentTimeMillis();
        st = current;
        frontier.add(current);
        while(!current.equals(State.goal)) {
            current = frontier.poll();
            for(State child : current.children()) {
                if(!visited(child)) {
                    visited.add(child);
                    frontier.add(child);
                }
            }
        }
        end = System.currentTimeMillis();
        //now current is the goal state, with a history of states that got it there
        this.finish = current;

    }
    public long time() {
        return end - start;
    }

    public State finish() {
        return finish;
    }

    public long count() {
        return this.visited.size();
    }

    public State start() {
        return st;
    }
}
