package model;

import java.util.ArrayList;
import java.util.List;

public class State implements Comparable<State> {

    public static final State goal = new State(RubixCube.goal, null);

    /**
     * The RubixCube that this state represents.
     */
    private RubixCube cube;

    /**
     * The cost (number of steps) to get to this current state.
     */
    int cost;

    /**
     * The previous state, which is the direct parent of this state.
     */
    private State prev;

    public State() {
        this.cube = new RubixCube();
        this.cube.shuffle();
    }

    public State(RubixCube cube, State prev) {
        this.cube = cube;
        this.prev = prev;
    }

    public RubixCube getCube() {
        return cube;
    }

    public List<State> children() {
        List<RubixCube> cubes = this.cube.children();
        List<State> states = new ArrayList<>();
        for(RubixCube r : cubes) {
            State s = new State(r, this);
            s.cost = this.cost + 1;
            states.add(s);
        }
        return states;
    }

    public int compareTo(State other) {
        return (this.cube.h() + this.cost) - (other.cube.h() + other.cost);
    }
    @Override
    public String toString() {
        return cube.toString();
    }

    public boolean equals(State other) {
        if(this == other) {
            return true;
        }
        return this.cube.equals(other.cube);
    }
}
