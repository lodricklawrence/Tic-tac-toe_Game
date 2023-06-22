package csu07419.ticTacToe;

public class PlayBoard {
    final int rows=5;
    final int columns=8;
    char[][] charArray=new char[rows][columns];


    public PlayBoard() {
        // Initialize the board with blank spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                charArray[i][j] = ' ';
            }
        }
    }

    public boolean checkFreeSpace(TokenPosition pos) {
//returns true if the position specified in pos is available; false
//otherwise. If a space is not in bounds, then it is not available
        int row = pos.getRow();
        int column = pos.getColumn();
        return row >= 0 && row < rows && column >= 0 && column < columns && charArray[row][column] == ' ';
    }

    public void placeToken(TokenPosition pos, char player) {
//places the character in player on the position specified by pos and
//        should not be called if the space is unavailable.
        if (checkFreeSpace(pos)) {
            int row = pos.getRow();
            int column = pos.getColumn();
            charArray[row][column] = player;
        } else {
            throw new IllegalArgumentException("There is no free space in " + pos + " as it is already occupied.");
        }

    }

    public boolean checkWinner(TokenPosition lastPos) {
//this function will check to see if the lastPos placed resulted in a
//        winner. If so it will return true, otherwise false.
//Passing in the last position will help limit the possible places to
//                check for a win condition since you can assume that any win condition
//        that did not include the most recent play made would have been caught
//        earlier.
        //You may call other methods to complete this method
        char player = TokenAtPosition(lastPos);
        return checkHorizontalWin(lastPos,player) || checkVerticalWin(lastPos,player) || checkDiagonalWin(lastPos,player);
    }

    public boolean checkTie(){
//this function will check to see if the game has resulted in a tie. A
//        game is tied if there are no free board positions remaining. You do
//        not need to check for any potential wins because we can assume that
//        the players were checking for win conditions as they played the game.
//            It will return true if the game is tied and false otherwise.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (charArray[i][j] == ' ') {
                    return false; // If there is a free space, the game is not tied
                }
            }
        }
        return true; // All spaces are occupied, the game is tied
    }

    public boolean checkHorizontalWin(TokenPosition lastPos, char player){
//checks to see if the last token placed resulted in 5 in a row
//        horizontally. Returns true if it does, otherwise false
        int row = lastPos.getRow();
        int column = lastPos.getColumn();

        int count = 1;
        int left = column - 1;
        while (left >= 0 && charArray[row][left] == player) {
            count++;
            left--;
        }

        int right = column + 1;
        while (right < columns && charArray[row][right] == player) {
            count++;
            right++;
        }

        return count >= 5;
    }

    public boolean checkVerticalWin(TokenPosition lastPos, char player) {
//checks to see if the last token placed resulted in 5 in a row
//        vertically. Returns true if it does, otherwise false
        int row = lastPos.getRow();
        int column = lastPos.getColumn();

        int count = 1;
        int up = row - 1;
        while (up >= 0 && charArray[up][column] == player) {
            count++;
            up--;
        }

        int down = row + 1;
        while (down < rows && charArray[down][column] == player) {
            count++;
            down++;
        }

        return count >= 5;
    }

    public boolean checkDiagonalWin(TokenPosition lastPos, char player) {
//checks to see if the last token placed resulted in 5 in a row
//        diagonally. Returns true if it does, otherwise false
        //Note: there are two diagonals to check
        int row = lastPos.getRow();
        int column = lastPos.getColumn();

        int count = 1;

        // Check diagonal from top-left to bottom-right
        int upLeftRow = row - 1;
        int upLeftColumn = column - 1;
        while (upLeftRow >= 0 && upLeftColumn >= 0 && charArray[upLeftRow][upLeftColumn] == player) {
            count++;
            upLeftRow--;
            upLeftColumn--;
        }

        int downRightRow = row + 1;
        int downRightColumn = column + 1;
        while (downRightRow < rows && downRightColumn < columns && charArray[downRightRow][downRightColumn] == player) {
            count++;
            downRightRow++;
            downRightColumn++;
        }

        if (count >= 5) {
            return true;
        }

        // Check diagonal from top-right to bottom-left
        count = 1;

        int upRightRow = row - 1;
        int upRightColumn = column + 1;
        while (upRightRow >= 0 && upRightColumn < columns && charArray[upRightRow][upRightColumn] == player) {
            count++;
            upRightRow--;
            upRightColumn++;
        }

        int downLeftRow = row + 1;
        int downLeftColumn = column - 1;
        while (downLeftRow < rows && downLeftColumn >= 0 && charArray[downLeftRow][downLeftColumn] == player) {
            count++;
            downLeftRow++;
            downLeftColumn--;
        }

        return count >= 5;

    }

    public char TokenAtPosition(TokenPosition pos){
        //returns what is in the game board at position pos
        //If no token is there, it returns a blank space char.
        int row = pos.getRow();
        int column = pos.getColumn();
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            return charArray[row][column];
        }
        return ' ';
    }

    public boolean isTokenAtPos(TokenPosition pos, char player) {
//returns true if the player is at pos; otherwise, it returns false
//Note: this method will be implemented very similarly to
//        TokenAtPosition, but it's asking a different question. We only know
//        they will be similar because we know game board will contain a 2D
//        array. If the data structure were to change in the future, these two
//        methods could be radically different.
        int row = pos.getRow();
        int column = pos.getColumn();
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            return charArray[row][column] == player;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  0 1 2 3 4 5 6 7\n");
        for (int row = 0; row < rows; row++) {
            sb.append(row).append("|");
            for (int column = 0; column < columns; column++) {
                sb.append(charArray[row][column]).append("|");
            }
            sb.append("\n");
        }
        return sb.toString();

    }
}
