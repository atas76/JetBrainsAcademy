package cs.langs.java.codeorg.oop.other.enums;

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        System.out.println("Turn left");
        direction = direction.turnLeft();
    }

    public void turnRight() {
        System.out.println("Turn right");
        direction = direction.turnRight();
    }

    public void stepForward() {
        System.out.println("Step forward");
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
