/**
*  Appropriately overwrites Piece for a Bishop piece
*  @author asuwoto3
*/
public class Bishop extends Piece {

    private Color color;

    /**
    *  Constructor for bishop
    */
    public Bishop(Color color) {
        super(color);
    }

    /**
    *  Returns algebraic name
    */
    public String algebraicName() {
        return "B";
    }

    /**
    *  Returns FEN name
    */
    public String fenName() {
        if (color == Color.WHITE) {
            return "B";
        } else {
            return "b";
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
