package com.epam.rd.autocode.concurrenttictactoe;

public interface TicTacToe {

    /**
     * Sets a mark in cell with specified coordinates.
     *
     * @param x    - x coordinate.
     * @param y    - y coordinate.
     * @param mark - mark to set.
     */
    void setMark(int x, int y, char mark);

    /**
     * Returns a COPY of current table with marks.
     * Note, edit of that copy should not affect the source TicTacToe object.
     *
     * @return a copy of current table.
     */
    char[][] table();

    /**
     * Returns last mark that was set in a table.
     *
     * @return last mark that was set in a table.
     */
    char lastMark();

    static TicTacToe buildGame() {
       return new Game();
    }

    class Game implements TicTacToe {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        volatile char lastmove = 'O';

        public Game() {

        }


        @Override
        public void setMark(int x, int y, char mark) {
            if (board[x][y] != ' ') throw new IllegalArgumentException();
            board[x][y] = mark;
            lastmove = mark;
        }

        @Override
        public char[][] table() {
            char boardgame[][] = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    boardgame[i][j] = board[i][j];
                }
            }
            return boardgame;
        }

        @Override
        public char lastMark() {
            return lastmove;
        }
    }
}