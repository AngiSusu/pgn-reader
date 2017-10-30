/**
*  Appropriately overwrites Piece for a Knight piece
*  @author asuwoto3
*/
public class Knight extends Piece {

    private Color color;

    /**
    *  Constructor for knight
    */
    public Knight(Color color) {
        super(color);
    }

    /**
    *  Returns algebraic name
    */
    public String algebraicName() {
        return "N";
    }

    /**
    *  Returns FEN name
    */
    public String fenName() {
        if (color == Color.WHITE) {
            return "N";
        } else {
            return "n";
        }
    }

    /**
    *  Returns squares the piece could have moved from in an array
    */
    public Square[] movesFrom(Square square) {
        Square[] fleb = new Square[1];
        fleb[0] = new Square("b1");
        return fleb;
    }
}