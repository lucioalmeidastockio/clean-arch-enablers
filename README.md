# ☕ clean-arch-enablers
This lib is meant to make implementing apps with clean arch easier. :pinched_fingers:
<br>

## Why bother? 🤔

🤕 Implementing clean architectured systems might require the construction of many components from scratch only for providing a foundation which the actual application will be developed upon. This consumes time and effort, a lot. 

💡 Turns out all boilerplate code is very repetitive when you reach a matured architecture of components! So wouldn't it be nice to be able to abstract it away into a layer with all the boilerplate done only once and then just reuse it whenever and wherever needed, on any project? 

Nice indeed... and now possible!

## Introducing the _clean_arch_enablers_ library! 🎆

The main idea of clean arch is to shield the logical core of a system from the _nothing-to-do-with-the-business_ components that still are necessary for building the application anyways. The way of doing that is designing the system with those external components being loosely coupled to the heart of it. 

<br>

![high-level-clean-arch-components](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/7-readme-content/high-level-clean-arch-components.png)

<br>

The main components at a high level of abstraction are:

- - - -
- ### Core layer (_specific implementations of use-case-business-rules, not depending on technologies_)
  - #### 🚪Ports — _Slots to put external components in without making the core layer know which specific technologies were used at the outside-of-the-business-rules level_

<br>

- ### External layer (_specific implementations of technologies needed for the application to run properly_)
  - #### 🔌Adapters — _Responsible for coupling the external components and injecting themselves into their respective Ports_  
- - - -

The _clean_arch_enablers_ takes place at the Core layer, so let's dive into the specifics of it:

Inside the Core layer we will find a whole catalog of business rules. It is there where all the logic of the application might live at. This means every business entity should be found there, with their respective functionalities. Additionally, these functionalities must be provided somehow to the external world, otherwise it wouldn't make sense to build the application at all. This is where the _Use Case_ component comes into play.

<br>

![introducing use case components](https://github.com/lucioalmeidastockio/clean-arch-enablers/blob/7-readme-content/diving-into-core-layer-pt1.png)

<br>

Without use cases there is no application at all. They are the motive behind the project itself. It is when you realize you need an aplication _to do something_ that it becomes clear the necessity of having that application created. Those expected "actions" are what we call by _Use Cases_ in this lib. The _Use Case_ component in clean arch is responsible for gathering all entity functionalities needed in a specific application use case.
