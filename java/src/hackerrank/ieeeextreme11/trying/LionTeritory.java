/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.trying;

import java.util.HashSet;
import java.util.Set;

public class LionTeritory {
}

class Point {

    int x;
    int y;
    int overlaps = 0;
    private Set<Lion> lions = new HashSet<>();

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addLion(Lion lion) {
        lions.forEach(Lion::notifyNewLion);
        lions.add(lion);
        overlaps++;
    }
}

class Lion {

    int x;
    int y;
    int overlaps = 0;

    public Lion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void notifyNewLion() {
        overlaps++;
    }
}