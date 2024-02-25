import React from "react";
import useProjects from "../useProjects";

const ProjectList: React.FC = () => {
  const { isLoading, error, projects } = useProjects();

  if (isLoading) return "Loading...";

  if (error) return "An error has occurred: " + (error as any).message;

  return (
    <div>
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
    </div>
  );
};

export default ProjectList;
