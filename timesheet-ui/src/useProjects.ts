import { useQuery } from "@tanstack/react-query";
import { IProject } from "./Project";

interface UseProjects {
  isLoading: boolean;
  error: any;
  projects: IProject[];
}

function useProjects(): UseProjects {
  const {
    isLoading,
    error,
    data: projects = [],
  } = useQuery<IProject[], Error>({
    queryKey: ["repoData"],
    queryFn: () =>
      fetch("http://127.0.0.1:8080/project/").then((res) => res.json()),
  });

  return {
    isLoading,
    error,
    projects,
  };
}

export default useProjects;
