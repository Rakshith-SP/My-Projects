""" A simple Black Jack game """

import random

values = {'Two': 2, 'Three': 3, 'Four': 4, 'Five': 5, 'Six': 6,
          'Seven': 7, 'Eight': 8, 'Nine': 9, 'Ten': 10,
          'Jack': 10, 'Queen': 10, 'King': 10, 'Ace': 11}
suits = ('Hearts', 'Diamonds', 'Spades', 'Clubs')
ranks = ('Two', 'Three', 'Four', 'Five', 'Six',
         'Seven', 'Eight', 'Nine', 'Ten', 'Jack',
         'Queen', 'King', 'Ace')


class Card:

    def __init__(self, suit, rank):
        self.suit = suit
        self.rank = rank
        self.value = values[rank]

    def __str__(self):
        return f"{self.rank} of {self.suit}"


class Deck:

    def __init__(self):
        self.all_cards = []
        for suit in suits:
            for rank in ranks:
                self.all_cards.append(Card(suit, rank))

    def __str__(self):
        cards = 'The deck consists of : '
        for card in self.all_cards:
            cards += '\n' + card.__str__()
        return cards

    def shuffle(self):
        random.shuffle(self.all_cards)

    def deal_one_card(self):
        return self.all_cards.pop()


class Player:

    def __init__(self, name, balance):
        self.name = name
        self.balance = balance

    def __str__(self):
        return f"{self.name} with balance {self.balance}"


class Dealer:

    def __init__(self, balance):
        self.balance = balance

    def __str__(self):
        return f"Dealer has balance of {self.balance}"


def player_setup():
    while 1:
        name = input("Enter your name : ")
        if not name.isalnum() or not name[0].isalpha():
            print("Please enter a valid name which is alphabets or "
                  "alphanumeric that does not starts with a number "
                  "or a special character")
        else:
            break

    while 1:
        while 1:
            try:
                balance = int(input("\nEnter your total money to start this game : "))
            except ValueError:
                print("Enter an integer value")
            except:
                print("Something went wrong! Please enter an integer value")
            else:
                break

        if 499 >= balance or balance >= 50000:
            print("The minimum balance must be 500 and maximum of 50000 to enter this game. "
                  "Enter the right balance")
            continue
        break

    return name, balance


def setup_ace_value():
    while 1:
        while 1:
            try:
                ace_value = int(input("\nDo you want ACE to be 1 or 11 : "))
            except ValueError:
                print("Enter an integer value")
            except:
                print("Something went wrong! Please enter an integer value")
            else:
                break

        if ace_value not in [1, 11]:
            print("Enter the either 1 or 11")
            continue
        break

    values['ACE'] = ace_value
    print(f"ACE value is {values['ACE']}\n")


def setup_player_bet():
    while 1:

        while 1:
            try:
                bet = int(input(f"Enter your bet (balance : {player.balance}): "))
            except ValueError:
                print("Enter an integer value")
            except:
                print("Something went wrong! Please enter an integer value")
            else:
                break

        if bet <= 499:
            print("The minimum bet must be 500. Enter the correct bet")
            continue
        else:
            if bet > player.balance:
                print("You cannot bet this. You dont have balance in your account")
                continue
            else:
                break

    player.balance -= bet
    dealer.balance -= bet
    return bet


def player_lost_by_bust(bet):
    dealer.balance += bet * 2
    print(f'\nPlayer {player.name} lost the bet of {bet} by BUST')
    print(f"Player's remaining balance is {player.balance}")


def dealer_got_bust(bet):
    player.balance += bet * 2
    print('\nDealer got BUST')
    print(f'Player won the total bet of {bet * 2}')
    print(f"Player's total balance is {player.balance}")


def player_lost(bet, d_sum, p_sum):
    dealer.balance += bet * 2
    print(f"\nDealer has {d_sum}! and player {player.name} had {p_sum}")
    print(f'Player {player.name} lost the bet')
    print(f"Player's remaining balance is {player.balance}")


# Game Setup ----------------

player_name, player_balance = player_setup()
initial_balance = player_balance
player = Player(player_name, player_balance)
print(f"\nPlayer {player.name} with balance {player.balance} has entered the game!")
dealer = Dealer(50000)  # the balance must be 50,000 and should be changed after a player bets

game_decision = True

while game_decision:

    deck = Deck()
    deck.shuffle()

    setup_ace_value()
    player_bet = setup_player_bet()

    dealer_cards = []
    player_cards = []

    for i in range(2):
        dealer_cards.append(deck.deal_one_card())
        player_cards.append(deck.deal_one_card())

    print(f"\nDealer's face-up card : {dealer_cards[1]}")
    print(f"\nPlayer's cards are : {player_cards[0]} and {player_cards[1]}")

    # Game Logic -------------

    game_on = True
    is_hit = True
    is_stand = False

    player_sum = 0
    dealer_sum = 0

    for p_card in player_cards:
        player_sum += p_card.value

    for d_card in dealer_cards:
        dealer_sum += d_card.value

    while game_on:

        # Player's Turn
        print(f"\nPlayer : {player_sum}")

        if player_sum == 21:
            print(f"\nPlayer {player.name} got a BLACKJACK")
            print(f"Dealer's faced down card is : {dealer_cards[0]}")
            print(f"Dealer : {dealer_sum}")
            game_on = False
            if dealer_sum == 21:
                print("\nDealer's sum is also 21. Therefore its a tie")
                player.balance += player_bet
                dealer.balance += player_bet
            else:
                player.balance += player_bet * 2
                print(f'\nPlayer won the total bet of {player_bet * 2}')
                print(f"Player's total balance is {player.balance}")
            break

        while is_hit and game_on:
            player_decision = ''
            while player_decision not in ['h', 's', 'hit', 'stand']:
                player_decision = input('\nHit or Stand? (h/s) : ').lower()

            if player_decision in ('s', 'stand'):
                is_hit = False
                is_stand = True

            elif player_decision in ('h', 'hit'):
                picked_card = deck.deal_one_card()
                print(f'\nPicked card is {picked_card}')
                player_cards.append(picked_card)
                player_sum += picked_card.value
                print(f"Player : {player_sum}")

                if player_sum >= 21:
                    game_on = False
                    player_lost_by_bust(player_bet)
                    break

        # Intermediate
        print(f"\nDealer's faced down card is : {dealer_cards[0]}")
        print(f"Dealer : {dealer_sum}")

        if dealer_sum > player_sum:
            game_on = False
            player_lost(player_bet, dealer_sum, player_sum)

        elif dealer_sum == player_sum:
            game_on = False
            print("\nIt's a tie!")
            player.balance += player_bet
            dealer.balance += player_bet

        # Dealer's Turn
        while is_stand and game_on:

            d_picked_card = deck.deal_one_card()
            print(f"\nDealer picked : {d_picked_card}")
            dealer_cards.append(d_picked_card)
            dealer_sum += d_picked_card.value
            print(f"Dealer : {dealer_sum}")

            if dealer_sum >= 21:
                game_on = False
                dealer_got_bust(player_bet)
                break

            elif dealer_sum > player_sum:
                game_on = False
                player_lost(player_bet, dealer_sum, player_sum)

            elif dealer_sum <= player_sum:
                continue

    game_decision = ''
    while game_decision not in ['y', 'n', 'yes', 'no']:
        game_decision = input("\nDo you want to continue? Y/N : ").lower()

    if game_decision in ('y', 'yes'):
        if player.balance >= 500:
            print("\nYou can continue the game!")
        else:
            print("\nYou cannot play the game again! You don't have the minimum balance of 500")
            print("Enjoy your day! If you can!! xd")
            break
    else:
        print(f"\n\nYou had {initial_balance} and now you are going with {player.balance}")
        print("\nThanks for playing! Ciao")
        break
