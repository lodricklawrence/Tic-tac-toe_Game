package csu07419.ticTacToe;

public class TokenPosition {
    private int Row;
    private int Column;

    public TokenPosition(int row,int column){
        this.Row=row;
        this.Column=column;
    }

    public int getRow(){
        return Row;
    }
    public int getColumn(){
        return Column;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenPosition that = (TokenPosition) o;
        return Row == that.Row && Column == that.Column;
    }


}
