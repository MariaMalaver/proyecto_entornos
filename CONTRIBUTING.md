Contribution Guidelines

Thank you for contributing to the Order Management project.

This document outlines the branching strategy, commit best practices, and Pull Request guidelines that contributors should follow.

1. Branching strategy

The `main` branch contains the stable version of the project.

New features, documentation changes, bug fixes, and other modifications must be developed in separate branches.

Branch names must follow this structure:

`feature/` for new features.
`fix/` for bug fixes.
`docs/` for documentation changes.
`refactor/` for code refactoring.

Contributors should avoid making changes directly to the `main` branch.

2. Commit conventions

Commits must have clear and descriptive messages.

The project uses the following prefixes:

`feat`: for new features.
`fix`: for bug fixes.
`docs`: for documentation changes.
`refactor`: for code refactoring.
`test`: for test-related changes.
`chore`: for maintenance tasks.

Commit messages must be written in English and briefly describe the purpose of the change.

3. Pull Request Guidelines

All changes intended for the main branch must be submitted via a *Pull Request*.

Before opening a Pull Request, contributors must:

Ensure their branch contains the latest relevant changes.
Verify that the project compiles correctly.
Run the available tests.
Review their own changes.
Ensure that commit messages are clear and descriptive.

The Pull Request description must explain:

What changes were made.
Why the changes were necessary.
Any information relevant to the reviewer.

Pull Requests must be reviewed before being merged into the main branch.

The reviewer must examine the code or documentation, verify that the changes adhere to project conventions, and provide feedback where necessary.

4. Code and Documentation Standards

Contributors must maintain the project's existing structure and coding style. Java source code must include clear Javadoc documentation for classes and methods, where applicable.

Documentation must be written in clear, coherent English.

Changes should focus on the branch's purpose and avoid unnecessary modifications to unrelated files.