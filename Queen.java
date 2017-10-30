/**
*  Appropriately overwrites Piece for a Queen piece
*  @author asuwoto3
*/
public class Queen extends Piece {

    private Color color;

    /**
    *  Constructor for queen
    */
    public Queen(Color color) {
        super(color);
    }

    /**
    *  Returns algebraic name
    */
    public String algebraicName() {
        return "Q";
    }

    /**
    *  Returns FEN name
    */
    public String fenName() {
        if (color == Color.WHITE) {
            return "Q";
        } else {
            return "q";
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