# ☕ clean-arch-enablers
This lib is meant to make implementing apps with clean arch easier. :pinched_fingers:
<br>

## Why bother? 🤔

🤕 Implementing clean architectured systems might require the construction of many components from scratch only for providing a foundation which the actual application will be developed upon. This consumes time and effort, a lot. 

💡 Turns out all boilerplate code is very repetitive when you reach a matured architecture of components! So wouldn't it be nice to be able to abstract it away into a layer with all the boilerplate done only once and then just reuse it whenever and wherever needed, on any project? 

It would be nice indeed... and now it _is_ possible!

## Introducing the _clean_arch_enablers_ library! 🎆

The main idea of clean arch is to shield the logical core of a system from the _nothing-to-do-with-the-business_ components that still are necessary for building the application anyways. The way of doing that is designing the system with those external components being loosely coupled to the heart of it. 

<br>

![high-level-clean-arch-components](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/7-readme-content/high-level-clean-arch-components.png)

<br>

The main components at a high level of abstraction are:

- - - -
- ### Core layer (_specific implementations of use-case-business-rules, not depending on technologies_)
  - #### 🚪[Ports](https://github.com/lucioalmeidastockio/clean-arch-enablers/tree/7-readme-content/src/main/java/br/com/stockio/ports) — _Slots to put external components in without making the core layer know which specific technologies were used at the outside-of-the-business-rules level_

<br>

- ### External layer (_specific implementations of technologies needed for the application to run properly_)
  - #### 🔌Adapters — _Responsible for coupling the external components and injecting themselves into their respective Ports_  
- - - -

The _clean_arch_enablers_ takes place at the Core layer, so let's dive into the specifics of it:

Inside the Core layer we will find a whole catalog of business rules. It is there where all the logic of the application might live at. This means every business entity should be found there, with their respective functionalities. Additionally, these functionalities must be provided somehow to the external world, otherwise it wouldn't make sense to build the application at all. This is where the _[Use Case](https://github.com/lucioalmeidastockio/clean-arch-enablers/tree/7-readme-content/src/main/java/br/com/stockio/use_cases)_ component comes into play.

<br>

![introducing use case components](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/7-readme-content/diving-into-core-layer-pt1.png)

<br>

Without use cases there is no application at all. They are the motive behind the project itself. It is when you realize you need an aplication _to do something_ that it becomes clear the necessity of having that application created. Those expected "actions" are what we call by _Use Cases_ in this lib. The _Use Case_ component in clean arch is responsible for gathering all entity functionalities needed in a specific application use case.

It is through the _Use Cases_ that we expose what the Core layer can do. In another words, _Use Cases_ compose the Core API. Some call this by the name of _Primary Ports_, in this lib we just mainly call it by _Use Cases_.

A Use Case in this lib is a component which has:

#### As attributes
- [Metadata](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/7-readme-content/src/main/java/br/com/stockio/use_cases/metadata/UseCaseMetadata.java)
  - Name — automatically set by the name of the class extending the Use Case type
  - Description — data provided by the author of the Use Case
  - Protection status — weather the use case access is open or protected

<br>

- [Logger](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/7-readme-content/src/main/java/br/com/stockio/loggers/Logger.java) (_an architectural border for you to implement using your preferred logging provider, being able to use it inside your core application without making the core layer coupled to it_)

#### As behaviors
- An `execute` method
  - Public method to be called when intended to execute the use case. It internally calls the method which has the implementation of the specifc use case instance.
  - Some types of Use Case have no input, some have no output, some have both and some have none. Each type will be mentioned further down this documentation.
  - If the Use Case type accepts inputs, the input type must extend the [UseCaseInput](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/7-readme-content/src/main/java/br/com/stockio/use_cases/io/UseCaseInput.java) type, so the UseCase can call the `validateProperties` method from the UseCaseInput type, which validates the input object before passing it down to the Use Case internal logic execution.
  - Regardless of the specific type of the Use Case (if it has specific input, output, etc.), each one of them receives an object of Correlation, which serves the purpose of tracking each execution with an unique ID.
  - It calls the specific Use Case type Processor, so the execution lifecycle is tracked by a unique log message that is built from the starting moment to the ending moment of the execution, and includes exception messages if any is thrown.

<br>

- An `applyInternalLogic` method
  - Internal method to be called when the `execute` method is externally called. 
  - Its implementation is provided by the UseCase specific implementation class.
  - Not meant to be called outside of the UseCase types.
  - The Logger instance will be available within its scope, so feel free to use it as you wish.
