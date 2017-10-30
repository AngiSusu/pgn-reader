import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {
    //return tag value for a specified game tag
    public static String tagValue(String tagName, String game) {
        if (game.indexOf(tagName) != -1) {
            String sub = game.substring(game.indexOf(tagName));
            String val = sub.substring(tagName.length() + 2, sub.indexOf("]")
                - 1);
            return val;
        }
        return "NOT GIVEN";
    }

    //cuts up giant game String into an array of moves (Strings)
    public static String[] makeMoves(String game) {
        String breakHalf = game.substring(game.indexOf("\n\n") + 2); //half
        String removeUglie = "";
        for (int k = 0; k < breakHalf.length(); k++) { //not-useful statements
            if (breakHalf.charAt(k) != '!' && breakHalf.charAt(k) != '?'
                && breakHalf.charAt(k) != '+' && breakHalf.charAt(k) != 'x'
                && breakHalf.charAt(k) != '*' && breakHalf.charAt(k) != '#') {
                removeUglie += Character.toString(breakHalf.charAt(k));
            }
        }
        if (removeUglie.contains("1-0") || removeUglie.contains("0-1")) {
            removeUglie = removeUglie.substring(0, removeUglie.length() - 4);
        }
        if (removeUglie.contains("1/2-1/2")) {
            removeUglie = removeUglie.substring(0, removeUglie.length() - 8);
        }
        String[] mov = removeUglie.split("\\s+"); //splits on whitespace
        int pdCount = 0;
        for (int i = 0; i < mov.length; i++) { //extraneous numbers
            if (mov[i].contains(".")) {
                pdCount++;
            }
        }
        String[] movs = new String[mov.length - pdCount];
        int movIndex = 0;
        for (int j = 0; j < movs.length; j++) {
            if (!(mov[movIndex].contains("."))) {
                movs[j] = mov[movIndex];
            } else {
                movs[j] = mov[movIndex + 1];
                movIndex++;
            }
            movIndex++;
        }
        //System.out.println(Arrays.toString(movs));
        return movs;
    }

    //non-crazy pawn moves
    public static String[][] pawnSimple(String[][] board, String[] moves, int i)
    {
        int row = toRow(moves, i);
        int col = toCol(moves, i);
        if (i % 2 == 0) { //white move
            for (int j = row + 1; j < 8; j++) {
                if (board[j][col].equals("P")) {
                    board[row][col] = "P";
                    board[j][col] = " ";
                    return board;
                }
            }
        } else {
            for (int k = row - 1; k >= 0; k--) {
                if (board[k][col].equals("p")) {
                    board[row][col] = "p";
                    board[k][col] = " ";
                    return board;
                }
            }
        }
        return board;
    }

    //YUHHHHHHH
    public static String[][] capturePawn(String[][] board, String[] moves, int
        i) {
        int toRow = toRow(moves, i);
        int toCol = toCol(moves, i);
        int fromCol = Character.getNumericValue(moves[i].charAt(0)) - 10;
        String piece = "";
        if (i % 2 == 0) { //white move
            piece = "P";
        } else { //black move
            piece = "p";
        }
        if (!board[toRow][toCol].equals(" ")) { //en passant
            board[toRow + 1][fromCol] = " ";
            board[toRow][toCol] = piece;
            return board;
        } else { //regular capture
            board[toRow][toCol] = piece;
            if (i % 2 == 0) {
                board[toRow + 1][fromCol] = " ";
                board[toRow + 1][toCol] = " ";
            } else {
                board[toRow - 1][toCol] = " ";
                board[toRow - 1][toCol] = " ";
            }
        }
        return board;
    }

    //a little pawn grew up!
    public static String[][] promo(String[][] board, String[] moves, int i) {
        String promotedPiece = Character.toString(moves[i].charAt(moves[i]
            .length() - 1));
        //System.out.println(promotedPiece);
        moves[i] = moves[i].substring(0, moves[i].length() - 2);
        //System.out.println(moves[i]);
        int toCol = toCol(moves, i);
        //System.out.println("tocol = " + toCol);
        if (moves[i].length() == 2) { //simple promo
            if (i % 2 == 0) { //white move
                board[0][toCol] = promotedPiece;
                board[1][toCol] = " ";
            } else { //black move
                board[7][toCol] = promotedPiece.toLowerCase();
                board[6][toCol] = " ";
            }
        }
        if (moves[i].length() == 3) { //capture promo
            int fromCol = Character.getNumericValue(moves[i].charAt(0)) - 10;
            //System.out.println("fromcol = " + fromCol);
            if (i % 2 == 0) { //white move
            //System.out.println(promotedPiece);
                board[0][toCol] = promotedPiece;
                board[1][fromCol] = " ";
            } else { //black move
                board[7][toCol] = promotedPiece.toLowerCase();
                board[6][fromCol] = " ";
            }
        }
        //System.out.println(Arrays.deepToString(board));
        return board;
    }

    //i like to move it move it
    public static String[][] moveNormal(String[][] board, String[] moves, int i)
    {
        String piece = moves[i].substring(0, 1);
        int toRow = toRow(moves, i);
        int toCol = toCol(moves, i);
        if (i % 2 != 0) { //black move
            piece = piece.toLowerCase();
        }
        for (int startRow = 0; startRow < 8; startRow++) {
            for (int startCol = 0; startCol < 8; startCol++) {
                if (piece.toUpperCase().equals("K")) {
                    if (board[startRow][startCol] .equals(piece)) {
                        board[toRow][toCol] = piece;
                        board[startRow][startCol] = " ";
                        return board;
                    }
                }
                if (piece.toUpperCase().equals("Q")) {
                    if (board[startRow][startCol].equals(piece)) {
                        if (startRow == toRow || startCol == toCol
                            || (Math.abs(startRow - toRow) == Math.abs(startCol
                            - toCol))) {
                            board[toRow][toCol] = piece;
                            board[startRow][startCol] = " ";
                            return board;
                        }
                    }
                }
                if (piece.toUpperCase().equals("R")) {
                    if (board[startRow][startCol] .equals(piece)) {
                        if (startRow == toRow || startCol == toCol) {
                            board[toRow][toCol] = piece;
                            board[startRow][startCol] = " ";
                            return board;
                        }
                    }
                }
                if (piece.toUpperCase().equals("B")) {
                    if (board[startRow][startCol] .equals(piece)) {
                        if (Math.abs(startRow - toRow) == Math.abs(startCol
                            - toCol)) {
                            board[toRow][toCol] = piece;
                            board[startRow][startCol] = " ";
                            return board;
                        }
                    }
                }
                if (piece.toUpperCase().equals("N")) {
                    if (board[startRow][startCol] .equals(piece)) {
                        if ((Math.abs(startCol - toCol) == 1 && Math.abs(
                            startRow - toRow) == 2) || (Math.abs(startCol
                            - toCol) == 2 && Math.abs(startRow - toRow) == 1)) {
                            board[toRow][toCol] = piece;
                            board[startRow][startCol] = " ";
                            return board;
                        }
                    }
                }
            }

        }
        return board;
    }

    //castling. because we love specific moves!
    public static String[][] castling(String[][] board, String[] moves, int i) {
        if (i % 2 == 0) {
            if (moves[i].equals("O-O-O")) {
                board[7][4] = " ";
                board[7][0] = " ";
                board[7][2] = "K";
                board[7][3] = "R";
            } else {
                board[7][4] = " ";
                board[7][7] = " ";
                board[7][6] = "K";
                board[7][5] = "R";
            }
        } else {
            if (moves[i].equals("O-O-O")) {
                board[0][4] = " ";
                board[0][0] = " ";
                board[0][2] = "k";
                board[0][3] = "r";
            } else {
                board[0][4] = " ";
                board[0][7] = " ";
                board[0][6] = "k";
                board[0][5] = "r";
            }
        }
        return board;
    }

    //finds board col to move piece to
    public static int toCol(String[] moves, int i) {
        return Character.getNumericValue(moves[i].charAt(moves[i].length() - 2))
            - 10;
    }

    //finds board row to move piece to
    public static int toRow(String[] moves, int i) {
        return Math.abs(Character.getNumericValue(moves[i].charAt(moves[i]
        .length() - 1)) - 8);
    }

    public static void printMatrix(String[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

    //returns FEN...AT LONG LAAAASTTT
    public static String finalPosition(String game) {
        String[] moves = makeMoves(game);
        String[][] board = new String[][] {
            {"r", "n", "b", "q", "k", "b", "n", "r"},
            {"p", "p", "p", "p", "p", "p", "p", "p"},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {"P", "P", "P", "P", "P", "P", "P", "P"},
            {"R", "N", "B", "Q", "K", "B", "N", "R"}
        };
        for (int i = 0; i < moves.length; i++) {
            if (moves[i].contains("=") && (moves[i].length() == 4
                || moves[i].length() == 5)) {
                board = promo(board, moves, i);
                //System.out.println(Arrays.deepToString(board));
            } else {
                if (moves[i].contains("O")) {
                    board = castling(board, moves, i);
                }
                if (moves[i].length() == 2) {
                    board = pawnSimple(board, moves, i);
                    //System.out.println(Arrays.deepToString(board));
                }
                if (moves[i].length() == 3 && Character.isUpperCase(moves[i]
                    .charAt(0))) {
                    board = moveNormal(board, moves, i);
                    //System.out.println(Arrays.deepToString(board));
                }
                if (moves[i].length() == 3 && Character.isLowerCase(moves[i]
                    .charAt(0))) {
                    board = capturePawn(board, moves, i);
                    //System.out.println(Arrays.deepToString(board));
                }
            }
        }
        return writeFEN(board);
    }

    //writes FEN-formatted String
    public static String writeFEN(String[][] board) {
        String fen = "";
        int whiteCount = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (!(board[r][c].equals(" "))) {
                    fen += board[r][c];
                } else {
                    whiteCount = 1;
                    while (c < 7 && board[r][c + 1].equals(" ")) {
                        whiteCount++;
                        c++;
                    }
                    fen += Integer.toString(whiteCount);
                }

            }
            if (r != 7) {
                fen += "/";
            }
        }
        return fen;
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    //a tiny blob of print statements for so many hours of conditionals
    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.format("Final Position: %s%n", finalPosition(game));
    }
}