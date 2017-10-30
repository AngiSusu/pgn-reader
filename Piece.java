/**
*  A class to represent chess pieces, including pawns
*  @author asuwoto3
*/
public abstract class Piece {

    private Color color;

    /**
    *  Constructor that stores color value
    */
    public Piece(Color color) {
        this.color = color;
    }

    /**
    *  Returns the color of the piece
    */
    public Color getColor() {
        return color;
    }

    /**
    *  returns the algebraic letter name of a piece e.g. "K"
    */
    public abstract String algebraicName();

    /**
    *  Returns the FEN name of a piece e.g. "k", "K"
    */
    public abstract String fenName();

    /**
    *  Returns an array of squares that the piece could have moved from
    */
    public abstract Square[] movesFrom(Square square);
}