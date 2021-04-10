Descisions & Questions:
    - I decided that the code needed to be scalable as it  was likely to be used for multiple URLs along with this I added error checking to the text import in case the given url wasn't valid.
    - Also I assumed that there would be bigger text files used at some point and hence gave the ability to use larger files without large performance difference, hence computational performance was important.
    - I would have asked the use case for this code to better determine what programming language would be most useful and efficent to make the program within. This would also help to know whether to put more user friendly errors as I assumed the errors would only be viewed by developers and hence I didn't need custom exceptions.
    - I assumed there was no need to sort the characters found since all we want is the greatest value and hence wouldn't need a sorted map of characters to be produced, along with assuming that in the event of an equal most common characters we just output both characters to the user.
    - Finally I assumed that a word was any string that is seperated by a new line or space and hence any words with a hyphen counts as one long word in my own definition of a word.

Trade Offs:
    - Not using a Quick Sort algorythm in order to sort the outputted hash map of characters or using a sorted map instead of the hash map. This was because we have a maximum of 26 characters avaliable and hence there is no need to use a computationally more expensive sorting method since the difference wouldn't be worth changing to. Along with this it is much easier to see the process in the current method and hence less points of failure.
    - When programming I used Java as I felt this may be the most efficent but more importantly the dependability of Java's platform meant that no matter what OS you test the project on it should always work in the same way.
    - I think I would've prefered for such a small task like this to have done it in python as it would be much quicker to put together the program to scrap just one web page's text and then analyse that although it would've been more computationally expensive and hence if this was to be expanded Java in my opinion would be a better language to work in.
    - I returned the value of the most common first character as a string although this will take up more memory it allows us to parse errors to the user without it stopping the code. Although ideally I would've written my own custom errors for the program to throw it seemed like a needless extra for something that only scrapes one web page and hence I went with displaying them as a string error to keep the program from failing to compile.

Prerequisites:
    - None, I used all in-built java modules

How To Run:
    - Open console or equivalent and cd to the folder location
    - Then enter the command:
        javac Driver.java
    - This will generate the class files for you 
    - Finally enter the command:
        java Driver
    