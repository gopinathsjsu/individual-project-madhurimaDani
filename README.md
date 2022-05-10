# CMPE 202 Individual Project
#### Madhurima Subodh Dani (015261974)

#### About Application
Application maintains static database for inventory and card data in form of hashmap and set collections. It reads an input file containing order. With reference to the inventory data, application validates if the ordered quantity of certain item is available in inventory and if the ordered quantity of certain item satisfies its category cap. If validation fails, application writes appropriate message in a txt file. If order is valid, application calculates price per item, total price and writes these numbers in output csv file. Application reduces ordered quantity from the inventory hashmap. If given card number doesn't exists in the card set, application adds it.

#### Steps To Execute Application:
- Clone repository to local: git clone git@github.com:gopinathsjsu/individual-project-madhurimaDani.git
- Compile: javac -d ./myTarget  ./\*.java ./\*/\*java
- Execute: java -cp ./myTarget edu.sjsu.mdani.Billing
- Program prints inventory and card data on console
- Program asks to enter Input File name. Please provide absolute path if the input file is not in current directory.
- Program generates output and writes it at same location as your input file path with name SampleInput-Output.
- Program asks if you wish to continue, enter y/n. If we enter y, program asks for input file and if we enter n, program terminates.

#### Design Patterns Implemented:
- Singleton: InMemoryStorage class is used to create in memory cache for storing inventory and card data. Instance of this class is returned by calling its getInsance() method.
- Builder: OrderBuilder class used to build the Order object by calling its build method.
- Strategy: SuccessOutputStrategy and ErrorOutputStrategy classes implement OutputFileWriter interfaces's write method to print appropriate messages on console. ResultWriter class calls chosen strategy's write method to print appropriate message.
<br>

#### Class Diagram
![IndividualProjectClassDiagram drawio (1)](https://user-images.githubusercontent.com/51197183/167548207-9a40ba97-3228-40f5-b37d-6389f2ab6d5b.png)


#### Snippets:

##### Case 1] Valid Order
Program Execution & Output
<br>
<img width="800" alt="Pasted Graphic 1" src="https://user-images.githubusercontent.com/51197183/167529414-fe186faf-55b0-47e0-b969-fb7edd6f7899.png">

##### Case 2] Item ordered more than permitted
Sample Input:
<br>
<img width="664" alt="Pasted Graphic 5" src="https://user-images.githubusercontent.com/51197183/167529558-70d2b572-223a-444f-bdf2-0a526ef935a4.png">
<br>
Program Execution & Output:
<img width="887" alt="Pasted Graphic" src="https://user-images.githubusercontent.com/51197183/167529573-f1a47d2d-163c-4b36-9cf0-b1f9f2901d13.png">


##### Case 3] Valid Order with Card Number not present in cache
Sample Input:
<br>
<img width="588" alt="nadhurin" src="https://user-images.githubusercontent.com/51197183/167529735-bf4bbcfc-23c1-4d9d-b280-4bfd91e6d469.png">
<br>
Program Execution & Output:
<img width="824" alt="Pasted Graphic 2" src="https://user-images.githubusercontent.com/51197183/167529796-3f29a2ae-1ab6-4568-a906-bef0906f4b7a.png">
<img width="654" alt="Pasted Graphic 4" src="https://user-images.githubusercontent.com/51197183/167529805-ff3746d2-d7c1-4c1b-9aba-418bd27ff9a7.png">


##### Case 4] Item not available in required quantity
Updated Inventory.csv and made pen quantity as 8 instaed of 400
<br>
<img width="330" alt="Clothes, Essentials, 100,20" src="https://user-images.githubusercontent.com/51197183/167529900-071ebe88-8918-4283-a755-3c983e5dcbbe.png">
<br>
Sample Input
<br>
<img width="362" alt="cards csv X = inventory csv" src="https://user-images.githubusercontent.com/51197183/167529912-eb1166f9-b2ff-42f6-930d-f54f4ed7a678.png">
<br>
Program Execution:
<br>
<img width="1044" alt="Pasted Graphic 7" src="https://user-images.githubusercontent.com/51197183/167529927-9173700f-4a6e-447e-a26e-d757ee0c15da.png">
<br>
Output for first iteration:
<br>
<img width="323" alt="Item, Quantity, Price, TotalPrice" src="https://user-images.githubusercontent.com/51197183/167529937-e4527f33-003c-4e76-9b9c-fd50edd1da6e.png">
<br>
Output for second iteration:
<br>
<img width="550" alt="Inventory csv" src="https://user-images.githubusercontent.com/51197183/167529963-44210b57-5ae2-4eee-9303-b50ef15aaa3b.png">





