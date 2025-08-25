package my.rpg.model.movementComponent;

import my.rpg.model.map.Map;

public class MovementComponent {
    private int x, y;

    public MovementComponent(){
    }

    public void setPosition(int newX, int newY){
        x = newX;
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param direction Direction of movement
     * @return False if the movement is not valid, True if it is.
     */
    public boolean move(MovementDirection direction){
        if (Map.getInstance() == null){
            throw new IllegalStateException("Map is not initialized, can't move");
        }
        int size = Map.getInstance().getSize();

        switch (direction){
            case North -> {
                if (y == size - 1)
                    return false;
                y++;
            }
            case South -> {
                if (y == 0)
                    return false;
                y--;
            }
            case East -> {
                if (x == size - 1)
                    return false;
                x++;
            }
            case West -> {
                if (x == 0)
                    return false;
                x--;
            }
            default -> {
                System.out.println("Direction not recognized");
                return false;
            }
        }
        return true;
    }
}
