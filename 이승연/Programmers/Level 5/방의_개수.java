import java.util.*;

class Solution {
    public int[] dx = new int[]{-1,-1,0,1,1,1,0,-1};
    public int[] dy = new int[]{0,1,1,1,0,-1,-1,-1};
    public HashMap<Line, Boolean> visitedLine = new HashMap<>();
    public HashMap<Position, Boolean> visitedPosition = new HashMap<>();
    public int solution(int[] arrows) {
        int answer = 0;
        int x = 0;
        int y = 0;
        visitedPosition.put(new Position(0,0), true);
        for(int i=0; i<arrows.length; i++) {
            for(int j=0; j<2; j++) {
                int newX = x+dx[arrows[i]];
                int newY = y+dy[arrows[i]];

                Line line = new Line(x,y,newX,newY);
                Position position = new Position(newX, newY);
                if (!visitedLine.containsKey(line) && visitedPosition.containsKey(position)) {
                    answer++;
                }
                visitedLine.put(line, true);
                visitedPosition.put(position, true);
                x = newX;
                y = newY;
            }
        }

        return answer;
    }
}

class Line {
    int x1;
    int y1;
    int x2;
    int y2;

    public Line(int x1, int y1, int x2, int y2) {
        if (x1 < x2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        if (x1 == x2) {
            if (y1 > y2) {
                this.x1 = x2;
                this.y1 = y2;
                this.x2 = x1;
                this.y2 = y1;
            } else {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }
        }
        if (x1 > x2) {
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;
        }
    }

    public boolean equals(Object o) {
        Line l = (Line) o;
        if (l.x1==this.x1 && l.y1==this.y1 && l.x2==this.x2 && l.y2==this.y2) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }
}

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        Position p = (Position) o;
        return p.x==this.x && p.y==this.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }
}