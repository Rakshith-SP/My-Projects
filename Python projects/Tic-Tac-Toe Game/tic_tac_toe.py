""" This code can be changed by replacing the logic
with keeping the count of every move and deciding the
player's turn based on count whether it is odd or even.
This eliminates the use of some variables and
try-except block"""


import os


def board(values):
    """This method prints the board with values"""
    print("-------------------------")
    print("|       |       |       | \n|   "
          + values[7] + "   |   " + values[8]
          + "   |   " + values[9]
          + "   | \n|       |       |       |")
    print("-------------------------")
    print("|       |       |       | \n|   "
          + values[4] + "   |   " + values[5]
          + "   |   " + values[6]
          + "   | \n|       |       |       |")
    print("-------------------------")
    print("|       |       |       | \n|   "
          + values[1] + "   |   " + values[2]
          + "   |   " + values[3]
          + "   | \n|       |       |       |")
    print("-------------------------")


def start_game():
    """This method is the first and starts the game"""
    print("\nPlayer 1 uses the marker 'X' and Player 2 uses marker 'O'"
          "\nPlayer 1 will go first...\n")
    while 1:
        player1 = input("Enter the name of player 1 : ")
        if not player1.isalnum() or not player1[0].isalpha():
            print("Please enter a valid name which is alphabets or "
                  "alphanumeric that does not starts with a number "
                  "or a special character")
        else:
            break

    while 1:
        player2 = input("Enter the name of player 2 : ")
        if not player2.isalnum() or not player2[0].isalpha():
            print(
                "Please enter a valid name which is alphabets or "
                "alphanumeric that does not starts with a number "
                "or a special character")
        else:
            break
    dictionary = {player1: 'X', player2: 'O'}
    players = [player1, player2]
    os.system('cls')
    print(f"\n{player1} will go first with marker 'X'"
          f"\n{player2} will go next with marker 'O'\n")
    print("The 3x3 board is corresponding with the num pad on your keyboard")
    values = ['empty', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    board(values)
    values = ['empty', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
    return players, dictionary, values


def take_input(players, dictionary, values, turn):
    """ This method takes input from the user"""
    print(f"{players[turn]}'s turn")
    while 1:
        tile = int(input("Enter the tile number (1 to 9) : "))
        if tile not in range(1, 10) or values[tile] != ' ' or tile == 0:
            if tile in range(1, 10):
                print('The entered tile is already taken, '
                      'please select the empty tile')
            else:
                print('The entered tile does not exist, '
                      'please select the tile number between 1 to 9')
        else:
            break
    values[tile] = dictionary[players[turn]]
    # clear_output() from IPython.display import clear_output
    os.system('cls')
    board(values)


def run_logic(players, dictionary, values, turn):
    """ This method checks if there is any winner"""
    mark = dictionary[players[turn]]
    return ((values[7] == values[8] == values[9] == mark)
            or (values[4] == values[5] == values[6] == mark)
            or (values[1] == values[2] == values[3] == mark)
            or (values[7] == values[4] == values[1] == mark)
            or (values[8] == values[5] == values[2] == mark)
            or (values[9] == values[6] == values[3] == mark)
            or (values[7] == values[5] == values[3] == mark)
            or (values[1] == values[5] == values[9] == mark))


def play_again():
    """ This method asks the user whether to continue or not"""
    while 1:
        status = input("Do you want to play again? Y or N : ").lower()
        if status in ['y', 'n']:
            break
    return status == "y"


def main():
    """" This is the main method"""
    while 1:
        players, dictionary, values = start_game()
        turn = 0
        while 1:
            take_input(players, dictionary, values, turn)
            if not run_logic(players, dictionary, values, turn):
                turn = not turn
                try:
                    values.index(' ')
                except ValueError:
                    print("It's a tie!\nEnd Game")
                    break
            else:
                print(f'{players[turn]} has won the game')
                break

        if play_again():
            os.system('cls')
        else:
            os.system('cls')
            print("Thanks for playing! Ciao")
            break


main()
