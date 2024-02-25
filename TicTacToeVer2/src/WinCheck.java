import javax.swing.*;
class WinCheck {
    boolean isWinVertical = false;
    boolean isWinHorizontal = false;
    boolean isWinDiagonal = false;
    boolean isWin = false;
    int x= 0; int y=0;
    boolean checkWinner(int size, int winLine, JButton[][] buttonArray, JButton player) {
        getCoordinates(size, buttonArray, player);
        isWinVertical = (winCheckUp(x, y, winLine, buttonArray) || winCheckDown(x, y, winLine, buttonArray));
        isWinHorizontal = (winCheckLeft(x, y, winLine, buttonArray) || winCheckRight(x, y, winLine, buttonArray));
        isWinDiagonal = (winCheckUpLeft(x, y, winLine, buttonArray) || winCheckUpRight(x, y, winLine, buttonArray)
                || winCheckDownLeft(x, y, winLine, buttonArray) || winCheckDownRight(x, y, winLine, buttonArray) );
        isWin = isWinVertical || isWinHorizontal || isWinDiagonal ;
        resetCoordinates();
        return isWin;
    }
    private boolean winCheckRight(int x, int y, int winLine, JButton[][] buttonArray){
        try {
            for (int i = x; i < x + winLine; i++) {
                if (!buttonArray[y][x].getText().equals(buttonArray[y][i].getText())) {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    private boolean winCheckLeft(int x, int y, int winLine, JButton[][] buttonArray){
        try{
            for(int i = x; i > x-winLine; i--){
                if(!buttonArray[y][x].getText().equals(buttonArray[y][i].getText())){
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private boolean winCheckUp(int x, int y, int winLine, JButton[][] buttonArray){
        try{
            for(int i = y; i < y+winLine; i++){
                if(!buttonArray[y][x].getText().equals(buttonArray[i][x].getText())){
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private boolean winCheckDown(int x, int y, int winLine, JButton[][] buttonArray){
        try{
            for(int i = y; i > y-winLine; i--){
                if(!buttonArray[y][x].getText().equals(buttonArray[i][x].getText())){
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private boolean winCheckUpRight(int x, int y, int winLine, JButton[][] buttonArray){
        try{
            for(int i = y, z=x; i > y-winLine; i--, z++){
                if(!buttonArray[y][x].getText().equals(buttonArray[i][z].getText())){
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private boolean winCheckUpLeft(int x, int y, int winLine, JButton[][] buttonArray){
        try{
            for(int i = y, z=x; i > y-winLine; i--, z--){
                if(!buttonArray[y][x].getText().equals(buttonArray[i][z].getText())){
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private boolean winCheckDownRight(int x, int y, int winLine, JButton[][] buttonArray){
        try{
            for(int i = y, z=x; i < y+winLine; i++, z++){
                if(!buttonArray[y][x].getText().equals(buttonArray[i][z].getText())){
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private boolean winCheckDownLeft(int x, int y, int winLine, JButton[][] buttonArray){
        try{
            for(int i = y, z=x; i < y+winLine; i++, z--){
                if(!buttonArray[y][x].getText().equals(buttonArray[i][z].getText())){
                    return false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    private void resetCoordinates(){
        x = 0; y = 0;
    }
    private void getCoordinates(int size, JButton[][] buttonArray, JButton player){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (buttonArray[i][j] == player) {
                    y = i; x = j;
                }
            }
        }
    }
}
