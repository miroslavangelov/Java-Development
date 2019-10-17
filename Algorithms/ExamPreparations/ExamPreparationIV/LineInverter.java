package Algorithms.ExamPreparations.ExamPreparationIV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LineInverter {
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int boardSize = Integer.parseInt(reader.readLine());
        board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            char[] boardData = reader.readLine().toCharArray();
            board[i] = boardData;
        }

        int inverts = 0;

        while (true) {
            int bestRow = -1;
            int bestNumberInRow = 0;

            for (int row = 0; row < board.length; row++) {
                int whiteFieldsInRow = 0;

                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] == 'W') {
                        whiteFieldsInRow++;
                    }

                    if (whiteFieldsInRow > bestNumberInRow) {
                        bestNumberInRow = whiteFieldsInRow;
                        bestRow = row;
                    }
                }
            }

            int bestCol = -1;
            int bestNumberInCol = 0;

            for (int col = 0; col < board.length; col++) {
                int whiteFieldsInCol = 0;

                for (int row = 0; row < board[col].length; row++) {
                    if (board[row][col] == 'W') {
                        whiteFieldsInCol++;
                    }

                    if (whiteFieldsInCol > bestNumberInCol) {
                        bestNumberInCol = whiteFieldsInCol;
                        bestCol = col;
                    }
                }
            }

            if (bestRow == -1 && bestCol == -1) {
                break;
            }

            if (bestNumberInRow > bestNumberInCol) {
                invertRow(bestRow);
            } else {
                invertCol(bestCol);
            }

            inverts++;
            if (inverts > boardSize * boardSize) {
                inverts = -1;
                break;
            }
        }

        System.out.print(inverts);
    }

    private static void invertRow(int row) {
        for (int i = 0; i < board.length; i++) {
            board[row][i] = invert(board[row][i]);
        }
    }

    private static void invertCol(int col) {
        for (int i = 0; i < board.length; i++) {
            board[i][col] = invert(board[i][col]);
        }
    }

    private static char invert(char element) {
        if (element == 'W') {
            return 'B';
        }

        return 'W';
    }
}
