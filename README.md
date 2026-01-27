# DECIDE — Launch Interceptor Decision Program


A Java implementation of the DECIDE algorithm that determines whether an interceptor should be launched (“YES” / “NO”) based on radar tracking data.


This project follows the DECIDE pipeline described in the course specification: the program evaluates Launch Interceptor Conditions (LIC0–LIC14) to build the **CMV** vector, combines it with the **LCM** matrix to produce **PUM**, applies **PUV** to generate **FUV**, and finally produces the boolean launch decision **LAUNCH** printed as `YES` or `NO`.


---


## Project specification


The full requirements are described in the provided specification PDF (included in this repository):


- `decide.pdf` (course/assignment specification)


---


## How to run the program


### Dependencies


- **Java:** JDK **17**
- **Maven:** required to run tests (`mvn test`)
- **Testing:** JUnit **4.13.2**




### Run (from IntelliJ / IDE)


1. Open the repository as a project.
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


## High-level architecture (DECIDE pipeline)


* **CMV (Conditions Met Vector):** evaluates LIC0–LIC14 → `boolean[15]`
* **PUM (Preliminary Unlocking Matrix):** combines CMV + LCM using ANDD, ORR, NOTUSED → `boolean[15][15]`
* **FUV (Final Unlocking Vector):** combines PUM + PUV (including `PUV=false` shortcut) → `boolean[15]`
* **DECIDE:** orchestrates CMV → PUM → FUV and prints the final launch decision


---


## Repository structure


Top-level files/folders you may see:


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


* **Amanda:** LIC0–LIC2 (implementation + unit tests)
* **Yusuf:** LIC3–LIC5 (implementation + unit tests)
* **Jafar:** LIC6–LIC8 (implementation + unit tests)
* **Dawa:** LIC9, LIC10, LIC14 (implementation + unit tests)
* **Edvin:** LIC11–LIC13 (implementation + unit tests)


### DECIDE pipeline modules + tests


| Team member | Responsibility                          | Tasks                                                                                                                                                                                                                                                                                                 |
| ----------: | --------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|  **Amanda** | CMV + tests                             | Call all 15 LICs, build CMV boolean vector, unit tests for CMV                                                                                                                                                                                                                                        |
|    **Dawa** | PUM + tests                             | Combine CMV + LCM, implement ANDD / ORR / NOTUSED logic, unit tests for PUM                                                                                                                                                                                                                           |
|   **Yusuf** | FUV + tests                             | Combine PUM + PUV, handle `PUV = false` shortcut, unit tests for FUV                                                                                                                                                                                                                                  |
|   **Edvin** | DECIDE integration + tests              | Orchestrate CMV → PUM → FUV, final launch decision, end-to-end integration tests                                                                                                                                                                                                                      |
|   **Jafar** | Quality, infrastructure & project setup | Repository hygiene and automation: `.github/` (issue templates + PR template), `workflows/ci.yml`; added `.gitignore` (ignoring `out/` and `.idea/`); restructured project for Maven (`pom.xml` with JUnit 4, moved sources to `src/main/java`, moved tests to `src/test/java`); README + MIT license |


---


## Way of working (Essence self-evaluation)


We are in the final stage because the project is functionally complete, validated with unit tests, and integrated through a consistent CMV → PUM → FUV → DECIDE pipeline. The main obstacles during development were merge conflicts and differing local tool setups, which we addressed by using atomic commits, enforcing consistent pull request information (summary/how-to-test), and running tests via Maven locally and in GitHub Actions to ensure repeatable results.


---


## License


This project is licensed under the MIT License — see the LICENSE file.


```
```
