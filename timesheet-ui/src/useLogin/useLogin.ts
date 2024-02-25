import React from "react";
import { useQuery } from "@tanstack/react-query";
import { ILoggedInUser } from '../Project';

interface UseLogin {
  username: string | undefined;
  accessToken: string | undefined;

  login: (data: FormData) => void;
}

function useLogin(): UseLogin {
  const [username, setUsername] = React.useState<string | undefined>(undefined);
  const [accessToken, setAccessToken] = React.useState<string | undefined>(
    undefined
  );

  const {
    refetch,
    isLoading,
    error,
    data: projects = [],
  } = useQuery<ILoggedInUser[], Error>({
    enabled: false,
    queryKey: ["repoData"],
    queryFn: (data: FormData) => {
      console.log('LOGGING IN', data);
      fetch("http://localhost:8080/login", 
          { method: 'POST', body: data} )
        .then((res) => res.json());
    }
  });

  return {
    username,
    accessToken,
    login: refetch
  };
}

export default useLogin;
