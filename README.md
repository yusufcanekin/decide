# DECIDE — Launch Interceptor Decision Program

A Java implementation of the DECIDE algorithm that determines whether an interceptor should be launched (“YES” / “NO”) based on radar tracking data.

This project follows the DECIDE pipeline described in the course specification: the program evaluates Launch Interceptor Conditions (LIC0–LIC14) to build the **CMV** vector, combines it with the **LCM** matrix to produce **PUM**, applies **PUV** to generate **FUV**, and finally produces the boolean launch decision **LAUNCH** printed as `YES` or `NO`.

---

## Project specification

The full requirements are described in the provided specification PDF:

- `decide.pdf` (course/assignment specification)

---

## How to run the program

### Dependencies

- **Java:** JDK **17**
- **Maven:** required to run tests (`mvn test`)
- **Testing:** JUnit **4.13.2**

### Run (from IntelliJ / IDE)

1. Open the repository as a Maven project.
2. Ensure Project SDK is set to **Java 17**.
3. Run `Main.java`.

Output:

- `YES` if `LAUNCH == true`
- `NO` if `LAUNCH == false`

### Run (from terminal)

From the repository root:

```bash
mvn clean compile
java -cp target/classes Main
````

---

## How to run tests

### Run all tests

```bash
mvn clean test
```

### Run a specific test class

```bash
mvn -Dtest=CMVTest test
```

---

## Java documentation

Generated Javadoc HTML is available at:

* `docs/apidocs/index.html`

---

## High-level architecture (DECIDE pipeline)

* **CMV (Conditions Met Vector):** evaluates LIC0–LIC14 → `boolean[15]`
* **PUM (Preliminary Unlocking Matrix):** combines CMV + LCM using ANDD, ORR, NOTUSED → `boolean[15][15]`
* **FUV (Final Unlocking Vector):** combines PUM + PUV (including `PUV=false` shortcut) → `boolean[15]`
* **DECIDE:** orchestrates CMV → PUM → FUV and prints the final launch decision

---

## Repository structure

Top-level files/folders you may see:
* `docs/apidocs/index.html` — Generated Javadoc HTML
* `src/` — Java source code
* `tests/` or `src/test/` — JUnit unit tests
* `pom.xml` — Maven configuration (Java 17)
* `.github/` — GitHub templates + CI workflow
* `README.md` — documentation
* `LICENSE` — license file

---

## Statement of contributions

We worked in small, reviewable pull requests and kept responsibilities separated by module to reduce integration risk. Each PR was expected to be atomic (one feature/bugfix) and include tests when applicable. We used GitHub reviews to keep merges safe, and Maven tests (locally + CI) to ensure reproducibility across different machines (Windows/macOS/Linux).

### LIC implementation + unit tests (responsibility split)

| Team member | GitHub username | Task                                             |
| ----------: | --------------- | ------------------------------------------------ |
|  **Amanda** | `Amanda-zakir`  | LIC0–LIC2 (implementation + unit tests)          |
|   **Yusuf** | `yusufcanekin`  | LIC3–LIC5 (implementation + unit tests)          |
|   **Jafar** | `sund02`        | LIC6–LIC8 (implementation + unit tests)          |
|    **Dawa** | `Dawacode`      | LIC9, LIC10, LIC14 (implementation + unit tests) |
|   **Edvin** | `Edvin-Livak`   | LIC11–LIC13 (implementation + unit tests)        |

### DECIDE pipeline modules + tests

| Team member | GitHub username | Responsibility                          | Tasks                                                                                                                                                                                                                                                                                                 |
| ----------: | --------------- | --------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|  **Amanda** | `Amanda-zakir`  | CMV + tests                             | Call all 15 LICs, build CMV boolean vector, unit tests for CMV                                                                                                                                                                                                                                        |
|    **Dawa** | `Dawacode`      | PUM + tests                             | Combine CMV + LCM, implement ANDD / ORR / NOTUSED logic, unit tests for PUM                                                                                                                                                                                                                           |
|   **Yusuf** | `yusufcanekin`  | FUV + tests                             | Combine PUM + PUV, handle `PUV = false` shortcut, unit tests for FUV                                                                                                                                                                                                                                  |
|   **Edvin** | `Edvin-Livak`   | DECIDE integration + tests              | Orchestrate CMV → PUM → FUV, final launch decision, end-to-end integration tests                                                                                                                                                                                                                      |
|   **Jafar** | `sund02`        | Quality, infrastructure & project setup | Repository hygiene and automation: `.github/` (issue templates + PR template), `workflows/ci.yml`; added `.gitignore` (ignoring `out/` and `.idea/`); restructured project for Maven (`pom.xml` with JUnit 4, moved sources to `src/main/java`, moved tests to `src/test/java`); README + MIT license |

---

## Way of working (Essence self-evaluation)

We assess our way of working as being in the **Working Well** state. Early on, the project principles and constraints were defined by the course specification, and we agreed on a shared interpretation of the DECIDE pipeline, coding responsibilities, and non-negotiable constraints such as correctness, test coverage, and reproducibility. We also aligned on core tools (Java 17, JUnit, GitHub), and used pull requests as the primary integration mechanism.

A foundational workflow was established through modular ownership (splitting LIC implementation and later pipeline components), unit testing per component, and PR-based reviews. Some friction appeared due to differences in local setups and because the transition to a proper Maven project structure happened later, requiring refactoring of existing code and tests. To reduce integration risk and rework, we kept changes small and reviewable, relied on tests to validate behavior, and resolved merge conflicts through frequent integration and consistent conventions.

Continuous integration was introduced later and configured via GitHub Actions to run `mvn clean test` automatically on every push and pull request across multiple operating systems. This improved confidence in the final system by ensuring the same unit tests pass consistently across environments. At this stage, the workflow supports collaboration naturally: responsibilities are clear, communication is consistent, and the team applies the agreed practices (tests + PR reviews + CI) without heavy process overhead. In hindsight, earlier standardization of the Maven structure and automation would have reduced some of the initial integration friction.

---

## License

This project is licensed under the MIT License — see the LICENSE file.

```
```