# GitHub User Activity CLI

A simple command-line application built with Java that fetches and displays a GitHub user's recent public activity using the GitHub REST API.

## Features

- Fetch recent public activity by GitHub username.
- Display repository creation and deletion events.
- Count and display push events grouped by repository.
- Handle basic HTTP responses, including successful requests and user-not-found errors.

## Usage

Run the application with a GitHub username:

```bash
github-activity <username>
```

Example:

```bash
github-activity torvalds
```

## Project Reference

This project was built as part of the roadmap.sh GitHub User Activity challenge:

[https://roadmap.sh/projects/github-user-activity](https://roadmap.sh/projects/github-user-activity)