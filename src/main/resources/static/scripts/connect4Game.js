/**
 * Created by tempvabon on 2016-12-25.
 * the JS file will have the logic of game playing
 * and the details of user and board in a scope object
 */


angular.module('ngConnect4', [])

    /* is a angular controller consists of angular functions and scope object */

    .controller('ngConnect4Ctrl', function($scope, $timeout,$http) {
        var RED = 1, BLACK = 2, MOVES = 0,
            boardWidth = 7, boardHeight = 6,
            tokenIndex = 0, IN_DROP = false;

        $scope.player = RED;

        /* is a angular function to start a new game that gives new board */
        $scope.newGame = function() {

            $http.get('http://localhost:8080/player/refreshGame/')
                .success(function(data) {
                    $scope.Data =data;
                }).error(function(data){
                alert("failed to refresh session of board");
            });

            MOVES = 0, tokenIndex = 0;
            $scope.player = RED;
            $scope.board = [];
            for (var row = 0; row < boardWidth; row++) {
                $scope.board[row] = [];
                for (var column = 0; column < boardHeight; column++) {
                    $scope.board[row][column] = 0;
                }
            }
        }
        $scope.newGame();

        /* is a angular function sets the color to column in the game board */

        $scope.setStyling = function(value) {
            if (value === RED)
                return {"backgroundColor": "red"};
            else if (value === BLACK)
                return {"backgroundColor":"black"};
            return {"backgroundColor": "white"};
        }

        /* is a angular function place the calculate the place in the board to drop*/

        $scope.placeToken = function(column) {
            if (!IN_DROP && $scope.board[column][0] === 0) {
                MOVES++;
                tokenIndex = 0;
                $scope.board[column][tokenIndex] = $scope.player;
                IN_DROP = true;
                dropToken(column, $scope.player);
                $scope.player = $scope.player === RED ? BLACK : RED;
            }
        }

        /* is a angular function drops the token in calculated position */

        function dropToken(column, player) {
            if ($scope.board[column][tokenIndex+1] === 0) {
                $timeout(function() {
                    $scope.board[column][tokenIndex] = 0;
                    $scope.board[column][++tokenIndex] = player;
                    dropToken(column, player);
                },75);
            } else {
                checkForWin(column, player);
                IN_DROP = false;
            }
        }

        /* is a angular function check for win */

        function checkForWin(column, player) {
            var i = tokenIndex, in_a_row = 1;

            // Check for win vertically
            while(i < boardHeight) {
                if ($scope.board[column][++i] === player) {
                    if (++in_a_row === 4)
                        return $timeout(function() {
                            gameOver(player);
                        },1);
                } else {
                    break;
                }
            }

            // Check for win horizontally
            var i = column, j = column, in_a_row = 1;
            while (--i >= 0) {
                if ($scope.board[i][tokenIndex] === player) {
                    in_a_row++;
                } else {
                    break;
                }
                if (in_a_row >= 4) {
                    return $timeout(function() {
                        gameOver(player);
                    },1);
                }
            }

            while (++j < boardWidth) {
                if ($scope.board[j][tokenIndex] === player) {
                    in_a_row++;
                } else {
                    break;
                }
                if (in_a_row >= 4) {
                    return $timeout(function() {
                        gameOver(player);
                    },1);
                }
            }

            // Check for diagonal win from left to right
            var i = column, j = column,
                ii = tokenIndex, jj = tokenIndex,
                in_a_row = 1;
            while (--i >= 0 && --ii >= 0) {
                if ($scope.board[i][ii] === player) {
                    in_a_row++;
                } else {
                    break;
                }
                if (in_a_row >= 4) {
                    return $timeout(function() {
                        gameOver(player);
                    },1);
                }
            }
            while (++j < boardWidth && ++jj < boardHeight) {
                if ($scope.board[j][jj] === player) {
                    in_a_row++;
                } else {
                    break;
                }
                if (in_a_row >= 4) {
                    return $timeout(function() {
                        gameOver(player);
                    },1);
                }
            }

            // Check for diagonal win from right to left
            var checkI = true, checkJ = true,
                i = column, j = column,
                ii = tokenIndex, jj = tokenIndex,
                in_a_row = 1;
            while (--i >= 0 && ++ii < boardHeight) {
                if ($scope.board[i][ii] === player) {
                    in_a_row++;
                } else {
                    break;
                }
                if (in_a_row >= 4) {
                    return $timeout(function() {
                        gameOver(player);
                    },1);
                }
            }
            while (++j < boardWidth && --jj >= 0) {
                if ($scope.board[j][jj] === player) {
                    in_a_row++;
                } else {
                    break;
                }
                if (in_a_row >= 4) {
                    return $timeout(function() {
                        gameOver(player);
                    },1);
                }
            }


            if (MOVES === boardWidth * boardHeight) {
                gameOver();
            }
        }

        /* is a angular function alerts for each game win and when board is full */
        function gameOver(player) {
            if (MOVES !== boardWidth * boardHeight) {
                winner = player === RED ? "Player-Red" : "player-Black";
                $http.put('http://localhost:8080/player/details/'+player)
                    .success(function(data) {
                        alert( winner +" game details saved in successfully");
                        $scope.Data =data;
                    }).error(function(data){
                    alert("failed to save player game details");
                });

            } if ( MOVES === boardWidth * boardHeight ) {

                $http.get('http://localhost:8080/player/getBoardWinner/')
                    .success(function(data) {
                        if(data===1)
                        {
                           winner = "Player-Red";
                        }
                        if(data===2)
                        {
                            winner = "Player-Black";
                        }
                        if(data=== -1)
                        {
                            winner = "No player";
                        }

                       alert(winner + " won the board, please start new game with fresh board");

                    }).error(function(data){
                    alert("failed to get board winner");
                });


            }
        }

    });