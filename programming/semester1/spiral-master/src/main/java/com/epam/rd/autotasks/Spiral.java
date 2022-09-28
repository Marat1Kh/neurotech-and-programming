package com.epam.rd.autotasks;
class Spiral {
    static int[][] spiral(int rows, int columns) {
        int r = rows;
        int c = columns;
        int s = 1;
        int[][] arr = new int[r][c];
        if((c > 1)|(r > 1)) {
            for (int y = 0; y < c; y++) {
                if(arr[0][y] == 0)
                    arr[0][y] = s;
                s++;
            }
            for (int x = 1; x < r; x++) {
                if(arr[x][c-1] == 0)
                    arr[x][c - 1] = s;
                s++;
            }
            for (int y = c - 2; y >= 0; y--) {
                if(arr[r - 1][y] == 0)
                    arr[r - 1][y] = s;
                s++;
            }
            for (int x = r - 2; x > 0; x--) {
                if(arr[x][0] == 0)
                    arr[x][0] = s;
                s++;
            }
            int a = 1;
            int d = 1;
            while (s < r * c) {
                while (arr[a][d + 1] == 0) {
                    arr[a][d] = s;
                    s++;
                    d++;
                }
                while (arr[a + 1][d] == 0) {
                    arr[a][d] = s;
                    s++;
                    a++;
                }
                while (arr[a][d - 1] == 0) {
                    arr[a][d] = s;
                    s++;
                    d--;
                }
                while (arr[a - 1][d] == 0) {
                    arr[a][d] = s;
                    s++;
                    a--;
                }
            }
            for (int x = 0; x < r; x++) {
                for (int y = 0; y < c; y++) {
                    if (arr[x][y] == 0) {
                        arr[x][y] = s;
                    }

                }
            }
        }else{arr[0][0] = 1;}

        return arr;
    }}