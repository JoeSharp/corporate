import { z } from "zod";
import { useQuery } from "@tanstack/react-query";

import Project from "./Project";

type IProject = z.infer<typeof Project>;

function App() {
  const {
    isLoading,
    error,
    data: projects,
  } = useQuery<IProject[], Error>({
    queryKey: ["repoData"],
    queryFn: () =>
      fetch("http://127.0.0.1:8080/project/").then((res) => res.json()),
  });

  if (isLoading) return "Loading...";

  if (error) return "An error has occurred: " + (error as any).message;

  return (
    <main>
      <h1>Timesheets</h1>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          {projects.map((project) => (
            <tr key={project.id}>
              <td>{project.id}</td>
              <td>{project.name}</td>
              <td>{project.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </main>
  );
}

export default App;
