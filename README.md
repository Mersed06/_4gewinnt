# Project Group 4 CSDC25

* Team: 4gewinnt

* Team members: 
- Mersed Keco
- Emil Tiric
- Jakob Lener
- Mathias Kerndl

* Project:
- Currency Converter (Console Application and GUI)

* How it works:
- Console Application:
A list of all available currencies is printed on the console. Then you get asked to type in the first currency, the second currency and the amount that you want to convert. 
In the console application, you have to use the three-letter abbreviation of the currencies. If your input is wrong (e.g. wrong currency or if the amount is not a number),
you get asked to repeat it. If your input is valid number, the amount will be converted and printed on the console.

- GUI:
The list of available currencies is saved in two dropdown lists, one for the first currency and another one for the second currency. You have to choose the currency from
the list. To input the amount, you have to use the text field with "amount" as the placeholder text. To convert your amount, use the button with the placeholder text "Convert".
The result is displayed between the second dropdown-list and the button. The program checks if you have chosen both currencies and entered the amount. It is not possible to
convert if you didn't choose both currencies and entered the amount, if the amount is not a number or if you chose the same currency two times. Error messages are being printed
instead of the amount.


* How to use:
- Console application: run the main method of the Main class
- GUI: run the main method of the GUI class
