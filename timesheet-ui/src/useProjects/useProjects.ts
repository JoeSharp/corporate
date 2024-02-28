import { useQuery } from "@tanstack/react-query";
import { IProject } from "../Project";

interface UseProjects {
  isLoading: boolean;
  error: Error | null;
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
      fetch("/project/").then((res) => res.json()),
  });

  return {
    isLoading,
    error,
    projects,
  };
}

export default useProjects;
