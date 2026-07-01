# NVIDIA Interview — Complete Prep Guide (Priority Ordered)

Sources: peer guidance (Sanskruti, Gaurav, Abheerav, Akshay, Sri Sai) + 7 real GFG NVIDIA interview transcripts + 5 standalone problem pages.

---

## PRIORITY 1 — Operating Systems

### 1.1 OS Fundamentals
- What is an OS, its functions (process mgmt, memory mgmt, file mgmt, I/O mgmt, security)
- Multiprogramming vs Multitasking vs Time-Sharing vs Multiprocessing vs Multithreading — precise differences, diagrams
- Single-core vs Multi-core processor differences
- User mode vs Kernel mode — what triggers a switch, how it happens
- System calls — why required, examples (fork, exec, read, write)
- Booting process of Linux (asked in real transcript — stages: BIOS/UEFI → bootloader (GRUB) → kernel init → init/systemd)
- Can you change GRUB config? How?

### 1.2 Process & Thread Management
- Process vs Thread — full comparison
- Process image / memory layout of a process (text, data, BSS, heap, stack) — draw it
- Process states and state transition diagram
- What happens when you kill a parent process (orphan/zombie processes)
- Thread lifecycle (new → runnable → running → blocked → terminated)
- Kernel-level thread vs User-level thread — differences, pros/cons
- Thread switching vs Process switching — which is faster and why (no MMU/TLB flush for threads)
- Multithreading on a uniprocessor — is it useful? (yes — I/O overlap, responsiveness)
- Why does multiprocessing exist when multithreading already does? (fault isolation, true parallelism, security)
- Write code to create a thread in C (pthread_create)
- Design a data structure to implement multithreading (conceptual)

### 1.3 CPU Scheduling
- Scheduling algorithms: FCFS, SJF (preemptive & non-preemptive), Priority, Round Robin, Multilevel Queue, Multilevel Feedback Queue
- Multilevel Queue Scheduling vs Priority Scheduling — differences
- Practice: given arrival + burst times, draw Gantt chart for Preemptive SJF, compute avg waiting/turnaround time
- Priority inversion — what it is, how solved (priority inheritance)

### 1.4 Process Synchronization
- Critical Section problem — requirements (mutual exclusion, progress, bounded waiting)
- Semaphores — binary vs counting
- Wait() and Signal() — write the implementation from scratch
- Write a semaphore-based solution ensuring mutual exclusion for a critical section (code it)
- Mutex vs Semaphore
- IPC mechanisms: pipes, sockets, shared memory, message queues — how each works
- Given two processes wanting to read each other's variables — how do you implement it (shared memory / IPC)
- Deadlock — 4 necessary conditions, prevention, avoidance (Banker's algorithm), detection, recovery

### 1.5 Memory Management
- Memory Management Unit (MMU) — role
- Logical address vs Physical address — why logical addressing is needed
- Paging — one-level paging with diagram; multi-level paging
- Given page size + logical/physical address bit counts → compute number of pages and frames (practice numeric problems)
- TLB (Translation Lookaside Buffer) / look-aside buffer memory
- Segmentation vs Paging
- RAM vs ROM — why RAM is called "Random Access Memory"
- Volatile vs non-volatile memory; `volatile` keyword in C
- DMA (Direct Memory Access) — what it is, how it works, why it's needed
- Memory leaks — definition, example, how to avoid (valgrind, careful free())

### 1.6 Computer Organization (adjacent, came up repeatedly)
- CPU vs GPU — architectural differences (few powerful cores vs many simple cores, SIMD/SIMT)
- RISC vs CISC — differences with examples (ARM vs x86)
- ISA (Instruction Set Architecture) — is x86 an ISA? What does "different implementations of an ISA" mean (x86 implemented by both Intel and AMD)
- Little-endian vs Big-endian — write code to detect system endianness, write code to convert between them

**Resources**: CodeHelp OS cheatsheet (Akshay's rec), college PPTs, GFG OS section.

---

## PRIORITY 2 — C Language Internals

### 2.1 Memory Layout & Pointers
- Full memory layout of a C program: text/code segment, initialized data, uninitialized data (BSS), heap, stack — draw it
- Stack growth direction (upward/downward) — write a C program to determine stack growth direction experimentally
- Given arbitrary code, identify which variable lives in which memory segment (practice with sample snippets)
- Static variables — what they are, where stored (data segment, not stack), lifetime
- Static variable vs static method vs static class (C++/OOP context)
- Pointers — full review: pointer arithmetic, pointer to pointer, array of pointers, pointer to array
- Function pointers — syntax, write an example using one as a callback / passed as argument
- Callback functions — concept and example
- Dangling pointer bug: recognize why returning a pointer to a local stack array is broken —
  ```c
  char* func(){ char name[] = "name"; return name; } // BUG: name is stack-local, freed on return
  ```

### 2.2 Dynamic Memory
- malloc, calloc, realloc, free — differences and internals
- Write your own malloc-like function that always returns a 16-byte-aligned address
- What happens internally when you call free() (memory returned to OS/allocator's free list)
- Struct memory alignment/padding — why members aren't packed tightly (single-machine-cycle fetch requirement)
- Memory leaks — causes and prevention (revisit from OS section, but from C allocation angle)

### 2.3 Language Mechanics
- `++` and `--` operators — trace output of tricky pre/post increment expressions (classic MCQ trap)
- Recursion — how it works internally (call stack, activation records), drawbacks (stack overflow, overhead)
- Static variable behavior inside recursive functions — trace output
- Macro vs Function — difference in terms of compile-time vs runtime expansion, stack usage, instruction pointer
- Unsigned vs signed int concepts — overflow/underflow behavior, bit representation
- Implement memcpy from scratch (pointer manipulation)
- Compute average of two large numbers without overflow: `A + (B-A)/2` instead of `(A+B)/2`
- Implement ternary operator without conditionals:
  - `(!!a)*b + (!a)*c`
  - array-indexing trick: `int arr[] = {c, b}; return arr[a];`
- Reverse bits of an unsigned integer (bit manipulation)
- Odd/even check using bit manipulation (`x & 1`) instead of `% 2`
- Zigzag array pattern detection (inc/dec/inc/dec) — e.g. sequence 7,2,8,4

### 2.4 C++ OOP (recurring across nearly every transcript)
- Virtual functions — what they are, how implemented internally (vtable / virtual pointer table) — be ready to explain `p->fun_ptr->fun()` mechanism
- Pure virtual functions & abstract classes — why used, code example
- Virtual destructors — why needed (avoid memory leaks in polymorphic deletion)
- Inheritance — types, implementation in C (simulate with function pointers/struct composition) and C++
- Polymorphism — compile-time (overloading/templates) vs runtime (virtual functions) — give code examples of each
- Function templates — what happens when a template is instantiated
- Singleton class — implement it in code
- Constructors/destructors, storage classes in C++ context

**Resources**: GFG "Memory Layout of a C Program", "C Program to Find Direction of Growth of Stack", pointer/function-pointer articles.

---

## PRIORITY 3 — Projects (Yours, Deeply)

Both Akshay and the transcripts stress this is often decisive.

- For **every** project on your resume, prepare:
  - What problem it solves and why you chose that specific tech stack
  - Key implementation challenges you personally solved (not generic features)
  - Full "why" chain: why this algorithm/library/architecture over alternatives
  - Be ready to **write live code** for a core function/module from the project if asked
  - Any ML-specific project → be ready for basic ML questions: perceptron vs neural network, what deep learning is/learns, supervised vs unsupervised learning implementation basics
  - Any hardware/orbital-mechanics/simulation-style project → be ready for domain-specific deep dives (interviewers latch onto anything unusual and go deep)
- Practice a crisp "why NVIDIA" narrative that's honest about your AI/ML orientation (per Abheerav — NVIDIA hires broadly: AI products, systems, web dev/internal tooling — don't force a systems narrative you don't have)

---

## PRIORITY 4 — DSA (Easy–Medium Only)

Confirmed easier than typical FAANG bars — don't over-grind hard problems.

### Core topics
- Arrays/Strings: substring search, first non-repeating character in O(n), pair with given sum (start O(n²), optimize to O(n log n) via sort + binary search — practice this exact upgrade path live)
- Linked Lists: find the middle node, find the 3/4th node, reverse a linked list, detect cycle
- Trees: find max depth/height of a binary tree, basic traversals (in/pre/post-order), BST properties
- Sorting: know time/space complexity of each; which uses the fewest swaps (Selection sort — fewest swaps, O(n))
- Searching: binary search — prove its O(log n) complexity; explain why it's *not* suitable for linked lists (no O(1) random access)
- Graphs: BFS, DFS, Minimum Spanning Tree (Prim's, Kruskal's), Shortest path (Dijkstra's) — know the algorithm and complexity, not necessarily implement from scratch
- Matrix: rotate a matrix by 180 degrees
- Trie: Word Boggle problem (find dictionary words in a grid via 8-directional DFS + Trie)
- Rectangle intersection: given coordinates of two rectangles, find the intersecting rectangle's coordinates
- Stack: implement stack using array and using linked list (push/pop)
- Print numbers 1 to N without using a loop (recursion trick)

**Resource**: Striver's SDE sheet (Gaurav's rec) + revisit hard problems after a few days so they stick (Akshay's spaced-repetition tip).

---

## PRIORITY 5 — Quant / Aptitude / Puzzles

Every round has 1–2 puzzles; the "trick" categories repeat.

- Probability: 3 insects/ants on a triangle, random direction — probability of no collision
- Probability/optimization: red/blue balls in two jars, redistribute to maximize probability of drawing red (no jar fully empty)
- Torch and bridge puzzle (classic — minimize total crossing time)
- 3 mislabeled boxes (apples/oranges/mixed) — minimum picks to correctly relabel all
- Silver rod cut into 7, give 1 inch/day for a week — minimum cuts (answer scales in powers of two: 1, 2, 4)
- Cake-cutting puzzles: dividing a cake (with an irregular missing piece) equally with one straight cut — key idea: any straight line through the centroid of a rectangle bisects its area; extend to two rectangles by passing through both centroids
- Base conversion puzzle: numbers that only work out under a different number base (e.g. class-count riddle solved in base 6)
- Factorial/modulo puzzles: trailing zeroes in 100! (count factors of 5), remainder of sums of factorials mod composite numbers (break modulus into coprime factors and use CRT-style reasoning)
- General quant: permutations & combinations, speed & distance — standard formulas, timed practice (this section is often "quite tough" per real transcripts, don't skip it)

**Approach tip** (Sri Sai's experience): interviewers expect *you* to proactively raise edge cases before being prompted — narrate your reasoning out loud, don't just state the final answer.

---

## PRIORITY 6 — CUDA / GPU (Situational — only if your resume mentions it)

Given your projects are AI/ML-oriented rather than systems/CUDA, treat this as optional deep-dive, not core prep — but know the basics in case of a follow-up question:

- What is CUDA — basic execution model: threads, blocks, grids
- Write a simple CUDA kernel: add two arrays by index
- Write CUDA matrix multiplication (and know alternate approaches)
- CUDA reduce/sum-array kernel
- How threads are synchronized in CUDA (`__syncthreads()`)
- Max threads per block (hardware-dependent, typically 1024)
- Be ready to answer: "What does NVIDIA's software team actually do?" — good framing: optimizing operating systems/software stacks to run efficiently on NVIDIA GPUs and hardware

---

## PRIORITY 7 — Interview Process & Meta-Strategy

- **Written round**: ~50 MCQs / 1hr, no negative marking, sectional cutoffs. Sections: C/C++ (pointers, recursion, ++/--), OS (critical section, memory mgmt, deadlock), DS/Algo (complexity-focused, easy), Quant (probability, P&C, speed-distance — tougher than expected)
- **Technical rounds** (2–3): Round order is typically: project discussion first → OS grilled deeply based on your answers → C/DS/Algo live coding/tracing → puzzles woven throughout
- **Discussion style**: expect deep back-and-forth on every answer; identify edge cases proactively; if your reasoning is solid, you may not be asked to write code at all
- **HR round** (if present): why NVIDIA, what is NVIDIA/who is the CEO, future study plans (why work vs pursue PG), any questions for them — have 2–3 genuine questions ready

---

## Suggested Study Order (Time-Boxed)

1. OS (paging, scheduling, semaphores) — write everything by hand, not just read
2. C internals (memory layout, pointers, malloc/alignment, dangling pointers) — write code by hand
3. Your own projects — rehearse "why" answers out loud
4. C++ OOP (vtables, abstract classes, virtual destructors)
5. DSA via Striver's sheet — easy/medium only, revisit after a few days
6. Puzzles — one focused skim session, note the recurring "trick" (powers of two, centroid symmetry, coprime moduli)
7. CUDA — light pass only if you plan to bring up GPU/systems interest
