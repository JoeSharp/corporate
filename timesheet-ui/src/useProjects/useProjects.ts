import { useQuery } from "@tanstack/react-query";
import { IProject } from "../Project";
import { useLoginContext} from '../useLogin/useLogin';

interface UseProjects {
  isLoading: boolean;
  error: Error | null;
  projects: IProject[];
}

function useProjects(): UseProjects {
  const { accessToken } = useLoginContext();

  const {
    isLoading,
    error,
    data: projects = [],
  } = useQuery<IProject[], Error>({
    queryKey: ["repoData"],
    queryFn: () =>
      fetch("/project/", {
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      }).then((res) => res.json()),
  });

  return {
    isLoading,
    error,
    projects,
  };
}

export default useProjects;
