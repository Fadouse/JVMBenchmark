# JVMBenchmark

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Test Categories](#test-categories)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

**JVMBenchmark** is a benchmarking tool developed to assess the performance implications of applying obfuscation techniques to Java applications. Built on JVM 17 and JDK 17, this project provides a suite of tests that measure various aspects of JVM performance before and after code obfuscation. Whether you're a developer aiming to secure your code or a performance engineer looking to understand the cost of obfuscation, JVMBenchmark offers valuable insights.

## Features

- **Comprehensive Test Suite:** Covers a wide range of JVM and Java language features.
- **Categorized Tests:** Organized into logical categories for easier analysis.
- **Easy Integration:** Simple setup process compatible with existing Java projects.
- **Detailed Reporting:** Generates detailed performance reports to highlight areas affected by obfuscation.
- **Extensible Framework:** Easily add new tests to accommodate specific benchmarking needs.

## Prerequisites

- **Java Development Kit (JDK) 17:** Ensure that JDK 17 is installed on your system.
- **Maven or Gradle:** For building and managing project dependencies.
- **Obfuscation Tool:** Any Java obfuscator compatible with your project (e.g., ProGuard, DexGuard).

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/JVMBenchmark.git
   cd JVMBenchmark
   ```

2. **Build the Project:**

   Using Maven:

   ```bash
   mvn clean install
   ```

   Using Gradle:

   ```bash
   gradle build
   ```

3. **Configure Your Obfuscator:**

   Set up your preferred obfuscation tool and configure it according to your project's requirements.

## Usage

1. **Run Baseline Tests:**

   Execute the test suite without applying obfuscation to gather baseline performance metrics.

   ```bash
   java -jar JVMBenchmark.jar
   ```

2. **Apply Obfuscation:**

   Obfuscate your Java application using your chosen obfuscation tool.

3. **Run Obfuscated Tests:**

   Execute the test suite on the obfuscated code to measure performance changes.

   ```bash
   java -jar JVMBenchmark.jar
   ```

4. **Compare Results:**

   Analyze the generated reports to identify performance impacts caused by obfuscation.

## Test Categories

JVMBenchmark includes a variety of tests categorized as follows:

- **Data Structure Tests**
    - `HashTableTest`

- **Sorting Tests**
    - `SortTest`

- **Garbage Collection Tests**
    - `GCTest`
    - `RealTimeGarbageCollectionTest`
    - `GarbageCollectorComparisonTest`

- **Class Loading & Unloading Tests**
    - `ClassLoadingTest`
    - `ClassUnloadingTest`

- **JIT Compilation Tests**
    - `JITCompilationTest`
    - `JITCompilerOptimizationTest`
    - `StartupAndJITWarmupTest`

- **Language Feature Tests**
    - `PatternMatchingTest`
    - `RecordsTest`
    - `SealedClassesTest`
    - `LambdaTest`
    - `IndyTest`

- **Reflection & Dynamic Proxy Tests**
    - `ReflectionTest`
    - `DynamicProxyTest`
    - `ReflectionAndDynamicProxyTest`

- **Thread & Concurrency Tests**
    - `ThreadCreationTest`
    - `SynchronizationTest`
    - `ThreadPoolTest`
    - `ThreadLocalTest`

- **Object & Memory Management Tests**
    - `ObjectCreationTest`
    - `EscapeAnalysisTest`
    - `MemoryUsageTest`

- **String & Text Operations Tests**
    - `StringOperationsTest`
    - `TextParsingTest`
    - `StringInterningTest`

- **Stream Operations Tests**
    - `SequentialStreamTest`
    - `ParallelStreamTest`
    - `StreamOperationsChainTest`

- **Serialization Tests**
    - `JavaSerializationTest`

- **Exception Handling Tests**
    - `ExceptionHandlingTest`

- **Cache Efficiency Tests**
    - `CacheEfficiencyTest`

- **Code Analysis & Instrumentation Overhead Tests**
    - `CodeAnalysisInstrumentationOverheadTest`

## Contributing

We welcome contributions from the community! Whether it's adding new tests, improving documentation, or reporting issues, your input is valuable.

1. **Fork the Repository**
2. **Create a Feature Branch**

   ```bash
   git checkout -b feature/YourFeature
   ```

3. **Commit Your Changes**
4. **Push to the Branch**

   ```bash
   git push origin feature/YourFeature
   ```

5. **Open a Pull Request**

Please ensure your code adheres to the project's coding standards and includes relevant tests.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any questions or suggestions, feel free to open an issue or contact [fadouse@turings.org](mailto:fadouse@turings.org).

---

*Happy Benchmarking! 🚀*
