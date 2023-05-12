package cs.langs.java.codeorg.oop.other.enums;

class Move {

    public static void main(String[] args) {
        Robot robot = new Robot(1, 1, Direction.RIGHT);
        moveRobot(robot, 0, -1);
        System.out.println("X == " + robot.getX());
        System.out.println("Y == " + robot.getY());
    }

    // 'Complex' but efficient :-)
    public static void moveRobot(Robot robot, int toX, int toY) {
        int dx = toX - robot.getX();
        int dy = toY - robot.getY();

        if (dx != 0) {
            switch (robot.getDirection()) {
                case RIGHT:
                    if (dx < 0) {
                        robot.turnRight();
                        robot.turnRight();
                    }
                    break;
                case UP:
                    if (dx > 0) {
                        robot.turnRight();
                    } else {
                        robot.turnLeft();
                    }
                    break;
                case LEFT:
                    if (dx > 0) {
                        robot.turnRight();
                        robot.turnRight();
                    }
                    break;
                case DOWN:
                    if (dx > 0) {
                        robot.turnLeft();
                    } else {
                        robot.turnRight();
                    }
                    break;
            }
            for (int i = 0; i < Math.abs(dx); i++) {
                robot.stepForward();
            }
        }
        if (dy != 0) {
            switch (robot.getDirection()) {
                case RIGHT:
                    if (dy > 0) {
                        robot.turnLeft();
                    } else {
                        robot.turnRight();
                    }
                    break;
                case UP:
                    if (dy < 0) {
                        robot.turnRight();
                        robot.turnRight();
                    }
                    break;
                case LEFT:
                    if (dy > 0) {
                        robot.turnRight();
                    } else {
                        robot.turnLeft();
                    }
                    break;
                case DOWN:
                    if (dy > 0) {
                        robot.turnRight();
                        robot.turnRight();
                    }
                    break;
            }
            for (int i = 0; i < Math.abs(dy); i++) {
                robot.stepForward();
            }
        }
    }
}
