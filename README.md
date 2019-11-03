HW2 - An object-oriented design and implementation of an annotation framework and a verifier for a design pattern.
--
Name : Abhijeet Mohanty
--
### Overview
* In this homework, I write a custom annotation processor which helps me verify the elements of the Memento design pattern. I make use of rule sets and class level verifiers to achieve my goal.

### Development environment
* **OS :** MacOS Mojave
* **IDE :** IntelliJ IDEA 2018.1
* **Language used :** Java 1.8.121
* **Build tool used :**  Gradle 4.10.2

### Building the application
* As this application runs completely in compile time, it does not need to be run.
* Instructions
    * Clone the following repository : **abhijeet_mohanty_cs474_hw2**
    * Navigate to abhijeet_mohanty_cs474_hw2/ as `cd abhijeet_mohanty_cs474_hw2/`
    * Then build and compile the project as mentioned below :
    
        `> gradle clean compileJava test build`

### Design pattern - Memento design pattern
* The memento design pattern is a behavioural design pattern which allows the storage and retrieval of the state of an object.
* In order to implement the design pattern, there are 3 primary elements- the caretaker, the originator and the memento.
    * Memento: This is a class within which the state of an object is stored.
    * Originator: This is a class which creates a memento.
    * Caretaker: This is a class which stores a list of mementos and provides functionality to undo to a particular memento.
* Constraints
    * The `Memento`'s state within a memento should only be set through a constructor and not through a setter. Also it should contain a method to get the state.
    * The `Caretaker` should contain a memento field, save a memento method and restore a memento method. 
    * The `Originator` should contain a list of mementos and a method to restore a memento which returns a memento from the list of mementos. 
    

### Design pattern verification
* Annotations
    * In order to verify the above design pattern, I first annotate all my class level and their enclosing elements with the following annotations.
        * `@VerifyOriginator` : Annotates the class which represents an **Originator**
            * `@VerifyStateField` - Checks if a state field is defined with the right modifier.
            * `@VerifySetStateMethod` - Checks if a state field method is defined with the right modifier.
            * `@VerifySaveMementoStateMethod` - Checks if a save memento method is defined with the right modifier.
            * `@VerifyRestoreMementoStateMethod` - Checks if a restore memento method is defined with the right modifier.
        * `@VerifyCaretaker` : Annotates the class which represents an **Caretaker**
            * `@VerifyMementoField` - Checks if a memento field is defined with the right modifier.
            * `@VerifyAddMementoMethod` - Checks if an add memento method is defined with the right modifier.
            * `@VerifyRestoreMementoMethod`
        * `@VerifyMemento` : Annotates the class which represents an **Memento**
            * `@VerifyState` - Checks if a state field is defined with the right modifier.
            * `@VerifyMementoConstructor` - Checks if a memento constructor is defined with the right modifier.
            * `@VerifyStateGetter` - Checks if a state getter method is defined with the right modifier.
        * NOTE : In case of a `FIELD` it is expected to have the `private` modifier and in case of a `CONSTRUCTOR` or `METHOD` it is 
                 expected to use a `public` modifier     
* Rule sets
    * These classes work on elements of kind - `METHOD`, `CONSTRUCTOR` and `FIELD`.
    * I check whether it has the right kind of modifier - `private` for fields and `public` for methods and constructors.
    * For instance, consider the `CaretakerRuleSet` class. This class verifies each element annotated with `@VerifyCaretaker.*` and 
    creates an instance of `VerificationResult` which consists of the message and its kind to be printed by the `messager` of the 
    **processing environment** of the `MementoDesignPatternVerifierProcessor`.
    * It works similarly with other rule sets such as `MementoRuleSet` and `OriginatorRuleSet`.
* Class level verifiers
    * These classes work on elements of kind `CLASS`.
    * Here I verify if all the enclosing elements needed for CLASS element in the memento design pattern are defined or not.
    * For instance, consider the `CaretakerClassVerifier` class. This class verifies the presence of each element annotated with `@VerifyCaretaker.*` and 
      creates an instance of `VerificationResult` which consists of the message and its kind to be printed by the `messager` of the 
      **processing environment** of the `MementoDesignPatternVerifierProcessor`.
    * In case an element which is needed by the CLASS is not present, a warning is printed stating that the element is not defined for that class.   
    * It works similarly with other class level verifiers such as `MementoClassVerifier` and `OriginatorClassVerifier`.
    *
* Look up
    * The `VeriferAndRuleSetLookup` class couple of static final maps which determine the type of rule set and class level verifier to use based on the type of annotation.
    * `nameToRuleSetMap` : In the `process` method in the annotation processor, the `RuleSet` class to be used in order to verify an annotated element enclosed within a `CLASS` is looked up using this map.
    * `nameToVerifierMap` : In the `process` method in the annotation processor, the `Verifier` class to be used in order to determined if all enclosing elements required have been defined within a `CLASS` is looked up using this map.

### Results
* The results in essence are the outputs of `messager` which is collected using an instance of the `VerificationResult` class.
* There are a total of 3 implementations of the design pattern :
    * `Memento`, `Originator` and the `Caretaker`
    * `ImproperMemento`, `ImproperOriginator` and the `ImproperCaretaker`
    * `Command` which corresponds to the `Caretaker`, `Snapshot` to the `Memento` and the `Editor` to the `Originator`
* Then, the aforementioned annotations are added to each class and the elements it encloses.    
* In case an annotated element is defined incorrectly or is not present, a `warning` is logged otherwise a `note` is logged. 
* The following results produced by the annotation processor are displayed below are produced at compile time :

```
   Note: Method declaration saveSnapshot in enclosing element Editor annotated with VerifySaveMementoStateMethod is correct
   Note: Method declaration save in enclosing element Originator annotated with VerifySaveMementoStateMethod is correct
   Note: Method declaration undo in enclosing element Command annotated with VerifyRestoreMementoMethod is correct
   Note: Method declaration restore in enclosing element Caretaker annotated with VerifyRestoreMementoMethod is correct
   Note: Method declaration makeBackup in enclosing element Command annotated with VerifyAddMementoMethod is correct
   Note: Method declaration addMemento in enclosing element Caretaker annotated with VerifyAddMementoMethod is correct
   Note: Variable declaration state in enclosing element ImproperOriginator annotated with VerifyStateField is correct 
   Note: Variable declaration text in enclosing element Editor annotated with VerifyStateField is correct 
   Note: Variable declaration curX in enclosing element Editor annotated with VerifyStateField is correct 
   Note: Variable declaration curY in enclosing element Editor annotated with VerifyStateField is correct 
   Note: Variable declaration selectionWidth in enclosing element Editor annotated with VerifyStateField is correct 
   Note: Variable declaration state in enclosing element Originator annotated with VerifyStateField is correct 
   Note: Variable declaration mementos in enclosing element ImproperCaretaker annotated with VerifyMementoField is correct 
   Note: Variable declaration backup in enclosing element Command annotated with VerifyMementoField is correct 
   Note: Variable declaration mementos in enclosing element Caretaker annotated with VerifyMementoField is correct 
   Note: Method declaration setText in enclosing element Editor annotated with VerifySetStateMethod is correct
   Note: Method declaration setCursor in enclosing element Editor annotated with VerifySetStateMethod is correct
   Note: Method declaration setSelectionWidth in enclosing element Editor annotated with VerifySetStateMethod is correct
   Note: Method declaration setState in enclosing element Originator annotated with VerifySetStateMethod is correct
   Note: Method declaration getState in enclosing element Memento annotated with VerifyStateGetter is correct
   Note: Method declaration restore in enclosing element Snapshot annotated with VerifyStateGetter is correct
   Note: Method declaration createSnapshot in enclosing element Editor annotated with VerifyRestoreMementoStateMethod is correct
   Note: Method declaration restore in enclosing element Originator annotated with VerifyRestoreMementoStateMethod is correct
   warning: Variable declaration state in enclosing element ImproperMemento annotated with VerifyState should not have public modifier.
   Note: Variable declaration state in enclosing element Memento annotated with VerifyState is correct 
   Note: Variable declaration text in enclosing element Snapshot annotated with VerifyState is correct 
   Note: Variable declaration curX in enclosing element Snapshot annotated with VerifyState is correct 
   Note: Variable declaration curY in enclosing element Snapshot annotated with VerifyState is correct 
   Note: Variable declaration selectionWidth in enclosing element Snapshot annotated with VerifyState is correct 
   warning: Method setState in enclosing element ImproperMemento annotated with VerifyStateSettershould not be defined and field variables should be initialized only through a constructor.
   Note: Constructor  in enclosing element Memento annotated with VerifyMementoConstructor is correct
   Note: Constructor  in enclosing element Snapshot annotated with VerifyMementoConstructor is correct
   warning: No element annotated with VerifySetStateMethod is defined in enclosing element ImproperOriginator
   warning: No element annotated with VerifySaveMementoStateMethod is defined in enclosing element ImproperOriginator
   warning: No element annotated with VerifyRestoreMementoStateMethod is defined in enclosing element ImproperOriginator
   warning: No element annotated with VerifyAddMementoMethod is defined in enclosing element ImproperCaretaker
   warning: No element annotated with VerifyRestoreMementoMethod is defined in enclosing element ImproperCaretaker
   warning: No element annotated with VerifyMementoConstructor is defined in enclosing element ImproperMemento
   warning: No element annotated with VerifyStateGetter is defined in enclosing element ImproperMemento
```

### Future improvements and ideas
* A compile time usage of reflections which can verify the order in which the elements of my design pattern are used and
to analyze the exact return types.
* An attempt to build a generic design pattern verifier.


