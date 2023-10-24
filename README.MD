# Kod test OP
Här kommer min lösning till kodtestet samt tankar om hur jag designade lösningen. Dokumentation samt referenser ingår för den som vill läsa om hur uppgiften löstes och designades. Jag har inte tidigare använt build tools i Java förutom Maven på grund av skolan, så den version av Java som körs är Java 10.

Om ni bara vill köra testfallen, skriv följande kommando i terminalen:
```shell
mvn package
```

För att köra main metoden, använd följande kommando i terminalen. För Windows-användare:
```shell
mvn exec:java -D"exec.mainClass"="com.example.App"
```

För Linux eller MacOS-användare, använd följande kommando i terminalen:
```shell
mvn exec:java -Dexec.mainClass="com.example.App"
```

Om ni vill kolla på logg filerna så finns de under logs mappen.

## Tankar kring uppgiften
Input: String av YYMMDDBBBC, YYMMDD-BBBC, YYMMDD+BBBC, YYYYMMDDBBBC, YYYYMMDD-BBBC, YYYYMMDD+BBBC
Output: Boolean + log av felen som uppstod

### Personnummer
Innehåller ett födelsedatum med 6-8 tecken i form av YYMMDD eller YYYYMMDD
Innehåller ett födelsenummer med 3 tecken i form av BBB
En delimiter av +, - eller inget där + signiferar en person som är >= 100 år gammal
Delimiter ignoreras om födelsedatum har längden 8
Check siffran är Luhns algo och returnerar en boolean samt ignorerar århundraden som 18, 19 och 20

Peronsnummer kommer att kolla följande:
- `FormCheck`: Kolla om formen är rätt på födelsedatum 6-8 tecken, kollar om det är 18, 19 och 20 århundrade.
- `DateCheck`: Kolla om månad <= 12 samt att dagarna 28, 30 och 31 är rätt fördelade för månaderna samt skottår datum. 
- `NumberCheck`: Kolla om formen på födelsenummer är korrekt där len inte är < 3 eller > 4
- `DelimiterCheck`: Kolla om en delimiter är med
- `LuhnCheck`: Kolla om check siffran på input stämmer överens med Luhns algoritm

### Samordningsnummer
Samma som personnummer bara att dagarna adderas med 60 så resten är samma förutom `DateCheck`. Till exempel så blir födelsedatum: 970605 --> 970665, samt att man kollar om dagarna är mellan 61 och 91.

### Organisationsnummer
kan inledas med århundrade 16 om det anges i 12-siffrigt format. Om det inte är 16 så fallerar test fallen, förstod inte riktigt vad texten försökte förmedla. Kollar även om mitten paret (århundrade ignoreras) är `>= 20`.

**Sammanfattnign:** Personnummer och Samordningsnummer skiljer sig år vid att kolla på dag. Organisationsnummer skiljer sig från både Person-/Samordningsnummer med hur de kollar dag och formen.

### ValidityCheck
ValidityCheck skall kolla om input string är validerad + logga all sne steg på vägen.

**Sammanfattning:** ValidityCheck är ett interface med en method för validering + en decorator klass kommer behövas för att logga sne stegen av vår övergripande template method class.

### Patterns
- Template method pattern
- Command pattern
- Decorator pattern

### Slut design
ValidityCheck är ett interface med en exikverings method.

Personnummer och Samordningsnummer är lika nog för att göra template method pattern med unik `DateCheck`. Det stod inget om att en organisation kan vara över 100år gammal så en `ageCheck` kommer bara att göras i template method av Personnummer och Samordningsnummer. Organisationsnummer skiljer i `DateCheck` och `FormatCheck` så det kommer behövas en övergripande template method för Person-/Samordnings- och Organisationsnummer.

Avsluta med att printa ut ValidityCheck genom att använda decorator pattern för att wrappa template method klassen som implementerar ValidityCheck. Detta utförs i main eller var det nu skall utföras. Kanske en Logger klass? kolla in på det

### Test 
Testa allt från metoderna separat, validering av test datan given sen kolla debug konsolen om det loggas.

### Synpunkter
Designen och implementation av metoderna var inte svårt efter designen var på plats. Att skriva test fallen först va kul enklare att debugga också än att bara tvinga på true. Det som ställde till lite med besvär var hur man skulle logga sne stegen, att kasta error här och där är inte det optimala sen förstod jag inte om det skall loggas i en fil eller i konsolen? ~~Är osäker på om jag lägger ned mer tid på att fixa en Log klass.~~ Jag valde att köra på båda två en för terminalen och en massa log filer. Det som sker i terminalen loggas också.

### Referenser
[Swedish Identity Info](https://swedish.identityinfo.net/)

[regex101](https://regex101.com/)

[Java Regular Expressions](https://www.w3schools.com/java/java_regex.asp#:~:text=The%20matcher()%20method%20is,if%20it%20was%20not%20found.)

[Matcher](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html)

[Pattern](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)

[Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm)

[Assert an Exception Is Thrown in JUnit 4 and 5](https://www.baeldung.com/junit-assert-exception)

[JUnit Test Exception Examples - How to assert an exception is thrown](https://www.codejava.net/testing/junit-test-exception-examples-how-to-assert-an-exception-is-thrown)

[How To Do Logging In Java](https://www.marcobehler.com/guides/java-logging)

[Logger entering() method in Java with Examples](https://www.geeksforgeeks.org/logger-entering-method-in-java-with-examples/)

[Java Exceptions - Try...Catch](https://www.w3schools.com/java/java_try_catch.asp#:~:text=Java%20try%20and%20catch,occurs%20in%20the%20try%20block.)

[how to format date using SimpleDateFormat](https://stackoverflow.com/questions/28616835/how-to-format-date-using-simpledateformat)

[Check If a String Is a Valid Date in Java](https://www.baeldung.com/java-string-valid-date)

[Java – Calculate Age from Date of Birth](https://howtodoinjava.com/java/calculate-age-from-date-of-birth/)

[How to use java.util.logging class in Java for Creating logs](https://www.youtube.com/watch?v=34552m80WrM)

[run main class of Maven project](https://stackoverflow.com/questions/9846046/run-main-class-of-maven-project)

[Java Logger does not print anything to console](https://stackoverflow.com/questions/37713212/java-logger-does-not-print-anything-to-console)

[Java<sup>TM</sup> Logging Overview](https://docs.oracle.com/javase/6/docs/technotes/guides/logging/overview.html)