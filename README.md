# Distributed Systems Coursework

Problem sets and in-class exercises from a Distributed Systems / concurrency course, focused on multithreaded Java (`Thread`/`Runnable`) for parallel data processing.

## Contents

- `PS1/` — `PS1.java` + `ArraySum.java`: reads a list of integers from a file, splits the array into ranges (as evenly as possible, with leftover elements distributed across the first threads), and sums each range in parallel using one `ArraySum` `Runnable` per thread, then joins all threads and combines partial sums into a final total. Run via `run.sh` (`javac PS1.java && java PS1 input.txt 12`).
- `inclass-10-1-2025/` — `Main.java`: the same parallel-partitioning pattern applied to files instead of array indices — recursively collects every file under a given directory, splits the file list across N threads (`Week7` runnable), and each thread sums the integers found in its assigned files, with per-thread and total results printed at the end. Test fixtures live in `Test/` (nested `Mart/`, `Tart/` subfolders with `.txt` files of numbers). Run via `run.sh` (`javac Main.java && java Main Test 2`).
- `Distributed/` — **Not code.** This is reference reading material only: lecture PDFs (processes, scheduling, locks, concurrency introduction) kept for study reference, not a project component.

## Stack

Java (JDK, no external libraries) — `Thread`/`Runnable`-based concurrency, no external frameworks.

## Setup & running

```
# PS1 — parallel array sum (args: inputfile numThreads)
cd PS1
sh run.sh
# equivalent manually:
javac PS1.java ArraySum.java
java PS1 input.txt 12

# inclass-10-1-2025 — parallel file sum (args: directory numThreads)
cd inclass-10-1-2025
sh run.sh
# equivalent manually:
javac Main.java
java Main Test 2
```
