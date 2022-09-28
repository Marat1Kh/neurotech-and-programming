package com.epam.rd.autocode.concurrenttictactoe;

public interface Player extends Runnable{
    static Player createPlayer(final TicTacToe ticTacToe, final char mark, PlayerStrategy strategy) {
        return new player1(ticTacToe, mark, strategy);
    }
    }
    class player1 implements Player{
    TicTacToe board;
    char playerself;
    PlayerStrategy strategy;
    public player1(TicTacToe ticTacToe, char mark, PlayerStrategy strategy){
        this.board = ticTacToe;
        this.playerself = mark;
        this.strategy = strategy;
    }
        private boolean last(char[][] board1) {
            for (int i = 0; i < 3; i++) {
                if (board1[i][0] == 'O' && board1[i][1] == 'O' && board1[i][2] == 'O') return true;
                if (board1[i][0] == 'X' && board1[i][1] == 'X' && board1[i][2] == 'X') return true;
            }
            for (int i = 0; i < 3; i++) {
                if (board1[0][i] == 'O' && board1[1][i] == 'O' && board1[2][i] == 'O') return true;
                if (board1[0][i] == 'X' && board1[1][i] == 'X' && board1[2][i] == 'X') return true;
            }

            if (board1[0][0] == 'O' && board1[1][1] == 'O' && board1[2][2] == 'O') return true;
            if (board1[0][0] == 'X' && board1[1][1] == 'X' && board1[2][2] == 'X') return true;

            if (board1[0][2] == 'O' && board1[1][1] == 'O' && board1[2][0] == 'O') return true;
            if (board1[0][2] == 'X' && board1[1][1] == 'X' && board1[2][0] == 'X') return true;

            boolean end = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1[i][j] == ' ') {
                        end = false;
                        break;
                    }
                }
            }
            return end;
        }
        @Override
        public void run() {
            while (!last(board.table())) {
                if (board.lastMark() != playerself) {
                    Move m = strategy.computeMove(playerself, board);
                    if ((board.table()[m.row][m.column] == ' ') && (!last(board.table()))) {
                        board.setMark(m.row, m.column, playerself);
                    }
                }
            }
            Thread.yield();
        }
        }


